## **QNLI Task**

----

#### **Download dataset**

You can use proxy in the script to download **QNLI** dataset from **GLUE**


    ./pull_data.sh proxy_ip


The default proxy is "https://127.0.0.1:1080"


    ./pull_data.sh


----

#### **Download Bert Models**

Run script


    ./pull_data.sh proxy_ip


or use default proxy "https://127.0.0.1:1080"


    ./pull_data.sh


The script will download all the following pre-trained models to directory `data/.cache`

    bert-base-uncased
    bert-large-uncased

----

#### **Create the environment**

First, make sure you have installed `anaconda` software. Then execute commands:

    conda create -n qnli python=3.6
    source activate qnli
    pip install -r requirements


----

#### **Training and evaluate**

After downloading the dataset and bert pretrained models, modify the hyperparameters in `run/run_qnli_train.sh` to train and save your own model with suffix `.pkl` within the corresponding diretory in `exp`. 

Furthermore, the training dataset is very large, in debug mode, we only read part of the dataset. To activate debug mode, set `debug=True` in function `utils/reader.py/read_dataset`. 

Some of the hyper parameters in `run/run_qnli_train.sh` are shown below:

- `loss(str)`: `bce` or `ce`. Use `Linear(hidden_dim, 1)+sigmoid+BinaryCrossEntropy` or `Linear(hidden_dim, 2)+softmax+CrossEntropy` loss function.
- `label_smoothing(float)`: to avoid over-confident about the classification, smoothing factor. For example, `label_smoothing=0.1`, then the target probability among true and wrong labels is 1-0.1+0.1/2=0.95 and 0.1/2=0.05 instead of 1.0 and 0. By default, `label_smoothing=0.0`, not use it.
- `optim(str)`: `bertadam` or `adam`. If use `bertadam`, we should also specify `warmup` and `schedule` params.
    - `warmup(float)`: warmup ratio introduced in paper *Attention is all you need*, default to $0.1$
    - `schedule(str)`: choices among `warmup_linear`, `warmup_constant`, `warmup_cosine`, default is `warmup_linear`
- `fix_bottom_layers(int)`: when `fix_bottom_layers=x`
    - if `x=-1 or 24`, finetune the params of the whole bert model
    - if `x=0`, we fix the embedding layer of bert model, only allow the rest layers to be finetuned
    - if `0 < x < [bert-base=12, bert-large=24]`, we fix the bottom $x$ layers of bert model plus embedding layer, and allow the layers above $x$ to be trained
    - By default, `x=-1`
- `ngpus(int)`: number of gpus to use in training. This number should be less than or equal to the total number of gpus available
- `deviceId(int)`: select the main device
    - if `deviceId=-1`, we use cpu
    - if `deviceId=0`, auto select one gpu from the available list as the main gpu
    - if `deviceId=x, x>0`, select the $x$-th gpu as the main device in `nn.DataParallel`

The training script will choose the best model on dev dataset, evaluate on test dataset and generate the target `QNLI.tsv` file. By the way, script `sbatch_job.sh` is an example to submit jobs in a cluster using slurm module for reference.


If you already have a trained model, and just want to evaluate on the test dataset, run with


    ./run/run_qnli_test.sh dir_path_to_model`


For example,


    ./run/run_qnli_test.sh exp/task_QNLI__bert-large-uncased/optim_bertadam__warmup_0.1__schedule_warmup_linear__lr_2e-05__l2_0.01__bsize_4__ngpus_4__loss_bce__reduce_mean__drop_0.1__me_4__smooth_0.15__fix_-1/


This will create `QNLI.tsv` file under the same directory of your model path.

----

#### **Ensemble**

We also try to ensemble the results from different models using script `data/ensemble.py`. First, run script

    python3 data/extract.py

This will copy and rename all `QNLI.tsv` files in `exp` directory to directory `data/backup/`. Next, run


    python3 data/ensemble.py


We will get the ensembled result in `data/QNLI.tsv` via majority vote.

----

#### **Make submission**

Move the target file `QNLI.tsv` to the directory `data/submission/` and run script


    ./submit.sh


This will create `submission.zip` file in the root directory for submission.