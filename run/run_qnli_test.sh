#!/bin/bash

read_model_path=$1
bert=bert-large-uncased # bert-base-uncased, bert-large-uncased
loss=bce
reduction=mean # mean, sum, it doesn't matter in test mode
label_smoothing=0.15 # it doesn't matter in test mode
batch_size=64
dropout=0.1
deviceId=0
seed=999

python scripts/qnli_classifier_bert.py --testing --read_model_path $read_model_path --bert $bert \
    --loss $loss --reduction $reduction --label_smoothing $label_smoothing \
    --batch_size $batch_size --dropout $dropout --deviceId $deviceId --seed $seed