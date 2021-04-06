#coding=utf8
import torch
import torch.nn as nn
import torch.nn.functional as F
from pytorch_pretrained_bert import BertModel
from utils.constants import *
from utils.loss import *

class BertBinaryClassification(nn.Module):

    def __init__(self, name, dropout=0.1, bce=True, 
        label_smoothing=0.0, bert_grad=False, fix_bottom_layers=-1, device=None):
        super(BertBinaryClassification, self).__init__()
        self.name = name
        self.bert = BertModel.from_pretrained(BERT_MODEL[name])
        self.dropout = nn.Dropout(p=dropout)
        self.bce = bce
        self.label_smoothing = label_smoothing
        self.classifier = nn.Linear(BERT_HIDDEN_SIZE[name], (1 if self.bce else 2))
        if self.label_smoothing > 0:
            self.loss_function = LabelSmoothingBCE(smoothing=label_smoothing, reduction='none')
        elif self.bce:
            self.loss_function = set_bceloss(reduction='none')
        else:
            self.loss_function = set_nllloss(reduction='none')
        self.device = device
        self.set_bert_grad(bert_grad, fix_bottom_layers)

    def init_weights(self, initrange=0.02):
        self.classifier.weight.data.normal_(mean=0.0, std=initrange)
        self.classifier.bias.data.zero_()

    def forward(self, word_ids, segment_ids, masks, label):
        _, pooled_output = self.bert(word_ids, segment_ids, masks, output_all_encoded_layers=False)
        pooled_output = self.dropout(pooled_output)
        scores = self.classifier(pooled_output)
        if self.bce:
            logprob = torch.sigmoid(scores).contiguous().view(-1) # bsize
            if not (self.label_smoothing > 0):
                label = label.float()
        else:
            logprob = F.log_softmax(scores, dim=-1) # bsize, 2
        loss = self.loss_function(logprob, label)
        return loss

    def predict(self, word_ids, segment_ids, masks):
        _, pooled_output = self.bert(word_ids, segment_ids, masks, output_all_encoded_layers=False)
        pooled_output = self.dropout(pooled_output)
        scores = self.classifier(pooled_output)       
        if self.bce:
            prob = torch.sigmoid(scores).contiguous().view(-1) # bsize
            neg_prob = 1 - prob
            prob = torch.cat([neg_prob.unsqueeze(dim=1), prob.unsqueeze(dim=1)], dim=1)
            logprob = torch.log(prob)
        else:
            logprob = F.log_softmax(scores, dim=-1) # bsize, 2
        labels = torch.argmax(logprob, dim=1)
        return labels

    def set_bert_grad(self, requires_grad=True, fix_bottom_layers=-1):
        if requires_grad:
            assert fix_bottom_layers <= BERT_NUM_LAYERS[self.name]
            if fix_bottom_layers > 0:
                fix_layers_idxs = [str(idx) for idx in range(int(fix_bottom_layers))]
            for n, p in self.bert.named_parameters():
                if fix_bottom_layers < 0: # do not fix any bottom layers
                    p.requires_grad = True
                elif fix_bottom_layers == 0: # only fix embedding layers
                    p.requires_grad = False if 'embeddings' in n else True
                else: # fix the embedding layer and bottom layers
                    p.requires_grad = False if 'embeddings' in n or any(idx in n for idx in fix_layers_idxs) else True
        else:
            for p in self.bert.parameters():
                p.requires_grad = False         

    def load_model(self, load_dir):
        if self.device.type == 'cuda':
            self.load_state_dict(torch.load(open(load_dir, 'rb'), map_location=lambda storage, loc: storage))
        else:
            self.load_state_dict(torch.load(open(load_dir, 'rb'), map_location=lambda storage, loc: storage))

    def save_model(self, save_dir):
        torch.save(self.state_dict(), open(save_dir, 'wb'))



