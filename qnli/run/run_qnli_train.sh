#!/bin/bash

bert=$1 # bert-base-uncased, bert-large-uncased
loss=bce
reduction=mean # mean, sum
lr=$2 # 2e-5, 3e-5, 4e-5, 5e-5
l2=0.01
batch_size=$3
dropout=0.1
optim=bertadam
warmup=0.1
schedule=warmup_linear # warmup_linear, warmup_constant, warmup_cosine, none
fix_bottom_layers=-1
init_weights=0.02
max_epoch=$4 # 3, 4
deviceId=0
ngpus=$5
label_smoothing=$6
seed=999

python scripts/qnli_classifier_bert.py --bert $bert --loss $loss --reduction $reduction --label_smoothing $label_smoothing \
    --fix_bottom_layers $fix_bottom_layers --lr $lr --weight_decay $l2 --batch_size $batch_size --dropout $dropout \
    --optim $optim --warmup $warmup --schedule $schedule --init_weights $init_weights \
    --max_epoch $max_epoch --deviceId $deviceId --ngpus $ngpus --seed $seed