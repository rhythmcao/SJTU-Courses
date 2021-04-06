#coding=utf8
import os, sys
import numpy as np
sys.path.append(os.path.dirname(os.path.dirname(__file__)))
from utils.writer import write_results

directory = os.path.join(os.path.dirname(os.path.abspath(__file__)), 'backup')

files = os.listdir(directory)

outfile = os.path.join(os.path.dirname(os.path.abspath(__file__)), 'QNLI.tsv')

results_list = []

def read_results(file_path):
    results = []
    with open(file_path, 'r') as infile:
        infile.readline()
        for line in infile:
            line = line.strip()
            idx, label = line.split('\t')
            label = 1 if label == 'entailment' else 0
            results.append(label)
    return np.array(results, dtype=np.int)
    
for each in files:
    file_path = os.path.join(directory, each)
    results = read_results(file_path)
    results_list.append(results.reshape(-1))
results = np.mean(np.stack(results_list, axis=0), axis=0, dtype=np.float)

write_results(results, outfile)