#!/bin/bash
source activate qnli 

bert=(bert-large-uncased)
lr=(1e-5 2e-5 3e-5 4e-5 5e-5)
bsize=(4)
epoch=(3 4)
smooth=(0.05 0.1 0.15 0.2)

for m in ${smooth[@]}; do
    for l in ${lr[@]}; do
        for b in ${bsize[@]}; do
            for e in ${epoch[@]}; do
                sbatch --job-name=QNLI -p gpu -o log/%j.out -e log/%j.err -n 1 --gres=gpu:4 \
                    ./run/run_qnli_train.sh bert-large-uncased $l $b $e 4 $m
            done
        done
    done
done
