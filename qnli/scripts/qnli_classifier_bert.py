#coding=utf8
import os, sys, argparse, time, json
from torch.utils.data import DataLoader

sys.path.append(os.path.dirname(os.path.dirname(__file__)))

from utils.dataset import QNLIDataset
from utils.batch import *
from utils.solver.solver_qnli import QNLISolver
from models.bert_wrapper import BertBinaryClassification
from models.data_parallel_wrapper import MyDataParallel
from utils.writer import set_logger, write_results
from utils.seed import set_random_seed
from utils.gpu import set_torch_device
from utils.example import Example
from utils.hyperparam import set_hyperparam_path
from utils.optim import *

parser = argparse.ArgumentParser()
parser.add_argument('--testing', action='store_true', help='default is training and decode')
parser.add_argument('--read_model_path', type=str, help='directory to the saved model')
parser.add_argument('--bert', default='bert-large-uncased', choices=['bert-base-uncased', 'bert-base-cased', 'bert-large-uncased', 'bert-large-cased'])
parser.add_argument('--loss', choices=['bce', 'ce'], default='bce')
parser.add_argument('--lr', default=2e-5, type=float)
parser.add_argument('--weight_decay', default=0.01, type=float)
parser.add_argument('--batch_size', default=16, type=int)
parser.add_argument('--dropout', default=0.1, type=float)
parser.add_argument('--fix_bottom_layers', default=-1, type=int, help='fix bottom layers of bert, -1 means relax the entire bert model, 0 means fix embedding layer')
parser.add_argument('--optim', choices=['adam', 'bertadam'], default='bertadam')
parser.add_argument('--reduction', default='mean', choices=['mean', 'sum'])
parser.add_argument('--label_smoothing', default=0.0, type=float)
parser.add_argument('--warmup', default=0.1, type=float, help='warmup ratio')
parser.add_argument('--schedule', default='warmup_linear', choices=['warmup_linear', 'warmup_constant', 'warmup_cosine', 'none'])
parser.add_argument('--init_weights', default=0.02, type=float)
parser.add_argument('--max_epoch', default=100, type=int)
parser.add_argument('--deviceId', type=int, default=-1, help='train model on ith gpu. -1:cpu, 0:auto_select')
parser.add_argument('--ngpus', type=int, default=1, help='use multi gpus to train model')
parser.add_argument('--seed', type=int, default=999)
args = parser.parse_args()
assert (not args.testing) or args.read_model_path
if args.testing:
    exp_path = args.read_model_path
else:
    exp_path = set_hyperparam_path(args)
if not os.path.exists(exp_path):
    os.makedirs(exp_path)
logger = set_logger(exp_path, testing=args.testing)
device, deviceIdList = set_torch_device(args.deviceId, args.ngpus)
set_random_seed(args.seed, device=device.type)

logger.info("Parameters:" + str(json.dumps(vars(args), indent=4)))
logger.info("Experiment path: %s" % (exp_path))
logger.info("Read dataset starts at %s" % (time.asctime(time.localtime(time.time()))))

start_time = time.time()
Example.set_tokenizer(args.bert) # set bert tokenizer
if not args.testing:
    train_loader = DataLoader(QNLIDataset('train'), batch_size=args.batch_size * args.ngpus, shuffle=True, collate_fn=collate_fn_labeled, num_workers=4)
    dev_loader = DataLoader(QNLIDataset('dev'), batch_size=args.batch_size * args.ngpus, shuffle=False, collate_fn=collate_fn_labeled, num_workers=4)
    logger.info('Training set size %s; Dev set size %s' % (len(train_loader.dataset), len(dev_loader.dataset)))
test_loader = DataLoader(QNLIDataset('test'), batch_size=args.batch_size * args.ngpus, shuffle=False, collate_fn=collate_fn_unlabeled, num_workers=4)
logger.info('Test set size %s' % (len(test_loader.dataset)))
logger.info('Processing dataset costs %.4fs' % (time.time() - start_time))

model = BertBinaryClassification(
    args.bert, dropout=args.dropout, bce=(args.loss == 'bce'), bert_grad=True, fix_bottom_layers=args.fix_bottom_layers,
    label_smoothing=args.label_smoothing, device=device
)
if args.ngpus > 1:
    model = MyDataParallel(model, deviceIdList, device.index)
    model = model.to(device)
    module = model.module
else:
    model = model.to(device)
    module = model
if args.testing:
    module.load_model(os.path.join(args.read_model_path, 'model.pkl'))
    test_solver = QNLISolver(model, None, exp_path, logger, device)
    logger.info("*" * 50)
    logger.info("Testing starts at %s" % (time.asctime(time.localtime(time.time()))))
    predictions = test_solver.decode(test_loader, labeled=False, add_loss=False)
    test_file = os.path.join(args.read_model_path, 'QNLI.tsv')
    logger.info("Start to write predictions to file %s" % (test_file))
    write_results(predictions, test_file)
else:
    module.init_weights(args.init_weights)
    if args.optim == 'bertadam':
        t_total = int((len(train_loader.dataset) + (args.batch_size * args.ngpus) - 1) / (args.batch_size * args.ngpus)) * args.max_epoch
        bert_optimizer = set_bertadam_optimizer(module.bert, args.lr, t_total, warmup=args.warmup, schedule=args.schedule, weight_decay=args.weight_decay)
        clsfy_optimizer = set_bertadam_optimizer(module.classifier, args.lr, t_total, warmup=args.warmup, schedule=args.schedule, weight_decay=args.weight_decay)
        all_optimizer = set_bertadam_optimizer(module, args.lr, t_total, warmup=args.warmup, schedule=args.schedule, weight_decay=args.weight_decay)
    else:
        bert_optimizer = set_adam_optimizer(module.bert, args.lr, args.weight_decay)
        clsfy_optimizer = set_adam_optimizer(module.classifier, args.lr, args.weight_decay)
        all_optimizer = set_adam_optimizer(module, args.lr, args.weight_decay)
    optimizer = {
        'bert': bert_optimizer,
        'clsfy': clsfy_optimizer,
        'all': all_optimizer
    }
    train_solver = QNLISolver(model, optimizer, exp_path, logger, device)
    logger.info("*" * 50)
    logger.info("Training starts at %s" % (time.asctime(time.localtime(time.time()))))
    train_solver.train_and_decode(train_loader, dev_loader, test_loader, args.max_epoch, args.reduction)
