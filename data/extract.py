#coding=utf8
import sys, os
sys.path.append(os.path.dirname(os.path.dirname(__file__)))
indir = os.path.join(os.path.dirname(os.path.abspath(__file__)), 'exp')
outdir = os.path.join(os.path.dirname(os.path.abspath(__file__)), 'backup')
count = 0
choices = os.listdir(indir)
for c in choices:
    files = os.listdir(os.path.join(indir, c))
    for f in files:
        f = os.path.join(indir, c, f, 'QNLI.tsv')
        if os.path.exists(f):
            count += 1
            outfile = os.path.join(outdir, str(count) + '.tsv')
            os.system('cp ' + str(f) + ' ' + str(outfile))