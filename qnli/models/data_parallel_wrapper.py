#coding=utf8
import torch
import torch.nn as nn

class MyDataParallel(nn.DataParallel):

    def predict(self, *args, **kargs):
        return self.module.predict(*args, **kargs)

    def init_weights(self, init_range):
        return self.module.init_weights(init_range)

    def set_bert_grad(self, *args, **kargs):
        self.module.set_bert_grad(*args, **kargs)

    def load_model(self, load_dir):
        self.module.load_model(load_dir)

    def save_model(self, save_dir):
        self.module.save_model(save_dir)

