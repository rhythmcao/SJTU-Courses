# coding=utf8
import time, os, gc, torch
import numpy as np
from utils.writer import write_results
from utils.batch import to_device

class Solver():

    def __init__(self, model, optimizer, exp_path, logger, device=None):
        """
            model: main model to train and evaluate
            optimizer: optimizer for training
            exp_path: export path to write test results, best model
            logger: training logger to record history
            device: torch.device()
        """
        super(Solver, self).__init__()
        self.model = model
        self.optimizer = optimizer
        self.exp_path = exp_path
        self.logger = logger
        self.device = device
    
    def train_and_decode(self, *args, **kargs):
        '''
            Training and test mode, pick the best model on dev dataset and 
            evaluate on test set.
        '''
        raise NotImplementedError

    def decode(self, *args, **kargs):
        '''
            Only evaluate on dataset and write down results
        '''
        raise NotImplementedError

class QNLISolver(Solver):

    def __init__(self, *args, **kargs):
        super(QNLISolver, self).__init__(*args, **kargs)
        self.history = {
            "best_iter": 0, "dev_acc": 0.,
            "dev_loss": 1e12, "train_losses": []
        }

    def train_and_decode(self, train_loader, dev_loader, test_loader, max_epoch, reduction='mean'):
        eval_iter = 0
        for epoch in range(max_epoch):
            start_time = time.time()
            epoch_loss = 0.0
            for i, data_batch in enumerate(train_loader):
                self.model.train()
                self.optimizer['all'].zero_grad()
                word_ids, segment_ids, masks, labels = to_device(data_batch, self.device)
                loss = self.model(word_ids, segment_ids, masks, labels)
                if reduction == 'mean':
                    loss = torch.mean(loss)
                else:
                    loss = torch.sum(loss)
                loss.backward()
                self.optimizer['all'].step()
                epoch_loss += loss.item()
                torch.cuda.empty_cache()
                gc.collect()

                eval_iter += 1
                if eval_iter % 100 == 0:
                    start_time = time.time()
                    dev_acc, dev_loss = self.decode(dev_loader, labeled=True, add_loss=True)
                    self.logger.info('Dev Evaluation:\tIter : %d\tTime : %.4fs\tLoss : %.5f\tAcc : %.4f' \
                            % (eval_iter, time.time() - start_time, dev_loss, dev_acc))
                    if dev_acc > self.history['dev_acc'] or (dev_acc == self.history['dev_acc'] and dev_loss < self.history['dev_loss']):
                        self.history['dev_acc'] = dev_acc
                        self.history['dev_loss'] = dev_loss
                        self.history['best_iter'] = eval_iter
                        self.model.save_model(os.path.join(self.exp_path, 'model.pkl'))
                        self.logger.warn('NEW BEST:\tIter : %d\tBest Valid Loss/Acc : %.4f/%.4f' % (eval_iter, dev_loss, dev_acc))
            self.history['train_losses'].append(epoch_loss)
            self.logger.info('Training:\tEpoch : %d\tTime : %.4fs\t Loss: %.5f' \
                                % (epoch, time.time() - start_time, epoch_loss))

            start_time = time.time()
            dev_acc, dev_loss = self.decode(dev_loader, labeled=True, add_loss=True)
            self.logger.info('Dev Evaluation:\tIter : %d\tTime : %.4fs\tLoss : %.5f\tAcc : %.4f' \
                                % (eval_iter, time.time() - start_time, dev_loss, dev_acc))
            if dev_acc > self.history['dev_acc'] or (dev_acc == self.history['dev_acc'] and dev_loss < self.history['dev_loss']):
                self.history['dev_acc'] = dev_acc
                self.history['dev_loss'] = dev_loss
                self.history['best_iter'] = eval_iter
                self.model.save_model(os.path.join(self.exp_path, 'model.pkl'))
                self.logger.warn('NEW BEST:\tIter : %d\tBest Valid Loss/Acc : %.4f/%.4f' % (eval_iter, dev_loss, dev_acc))
            torch.cuda.empty_cache()
            gc.collect()

        self.model.load_model(os.path.join(self.exp_path, 'model.pkl'))
        self.logger.error('FINAL BEST RESULT: \tIter : %d\tBest Valid (Loss: %.5f Acc : %.4f)' 
                % (self.history['best_iter'], self.history['dev_loss'], self.history['dev_acc']))
        test_file = os.path.join(self.exp_path, 'QNLI.tsv')
        test_results = self.decode(test_loader, labeled=False, add_loss=False)
        self.logger.info('Start writing test predictions to file %s ...' % (test_file))
        write_results(test_results, test_file)

    def decode(self, data_loader, labeled=True, add_loss=False, reduction='mean'):
        correct, predictions, total_loss = 0, [], 0.
        for data_zip in data_loader:
            self.model.eval()
            if labeled:
                word_ids, segment_ids, masks, labels = to_device(data_zip, self.device)
            else:
                word_ids, segment_ids, masks = to_device(data_zip, self.device)
            with torch.no_grad():
                predict_labels = self.model.predict(word_ids, segment_ids, masks).cpu()
                if labeled:
                    correct += int(np.sum(predict_labels.numpy()==labels.cpu().numpy()))
                predictions.extend(predict_labels.tolist())
                if add_loss:
                    assert labeled
                    self.model.train()
                    loss = self.model(word_ids, segment_ids, masks, labels).cpu()
                    if reduction == 'mean':
                        loss = torch.mean(loss).item()
                        total_loss += loss * word_ids.size(0)
                    else:
                        loss = torch.sum(loss).item()
                        total_loss += loss
            torch.cuda.empty_cache()
            gc.collect()

        if labeled:
            total_size = len(data_loader.dataset)
            predictions = float(correct)/total_size
        if add_loss:
            return predictions, total_loss
        return predictions




