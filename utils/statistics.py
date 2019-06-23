#coding=utf8
import os, sys
sys.path.append(os.path.abspath(os.path.dirname(os.path.dirname(__file__))))
from utils.reader import read_dataset
import numpy as np
from utils.example import Example
import matplotlib
import matplotlib.pyplot as plt 

Example.set_tokenizer('bert-base-uncased')

train = read_dataset('train')
dev = read_dataset('dev')
test = read_dataset('test')

train_lens = [len(each.word_pieces) for each in train]
dev_lens = [len(each.word_pieces) for each in dev]
test_lens = [len(each.word_pieces) for each in test]

def draw_lens_distribution():
    matplotlib.rcParams['axes.unicode_minus'] = False
    plt.figure(12)
    plt.subplot(221)
    plt.hist(dev_lens, bins=40, facecolor="blue", edgecolor="black", alpha=0.7)
    plt.ylabel("Frequencies")
    plt.title("Devset lens distribution")
    plt.tight_layout()
    plt.subplot(222)
    plt.hist(test_lens, bins=40, facecolor="blue", edgecolor="black", alpha=0.7)
    plt.title("Testset lens distribution")
    plt.tight_layout()
    plt.subplot(212)
    plt.hist(train_lens, bins=40, facecolor="blue", edgecolor="black", alpha=0.7)
    plt.xlabel("Lengths")
    plt.ylabel("Frequencies")
    plt.title("Trainset lens distribution")
    plt.tight_layout()
    plt.show()

print('Train dataset size: %d\tMax lens: %d\tMin lens: %d\tAvg lens: %.1f' %
    (len(train), max(train_lens), min(train_lens), np.mean(train_lens, dtype=np.float)))
print('Dev dataset size: %d\tMax lens: %d\tMin lens: %d\tAvg lens: %.1f' %
    (len(dev), max(dev_lens), min(dev_lens), np.mean(dev_lens, dtype=np.float)))
print('Test dataset size: %d\tMax lens: %d\tMin lens: %d\tAvg lens: %.1f' %
    (len(test), max(test_lens), min(test_lens), np.mean(test_lens, dtype=np.float)))
draw_lens_distribution()
