#coding=utf8
from utils.constants import *
from pytorch_pretrained_bert import BertTokenizer

class Example():

    _tokenizer = None

    def __init__(self, first, second, label=None):
        super(Example, self).__init__()
        self.first = first.strip()
        self.second = second.strip()
        self.label = label
        self.pair = self._bert_input_wrapper()
        assert Example._tokenizer
        self.word_pieces = self._bert_tokenize()
        self.length = len(self.word_pieces)
        self.truncated = self.length > 131 # 512
        # if self.truncated:
            # truncate the input sequence to be at most 512 in length
            # first_word_pieces = Example._tokenizer.tokenize(self.first)
            # second_word_pieces = Example._tokenizer.tokenize(self.second)
            # tails = self.length - 512
            # first_tails = int(tails * float(len(first_word_pieces)/(len(first_word_pieces) + len(second_word_pieces))))
            # self.word_pieces = [BERT_CLS] + first_word_pieces[:len(first_word_pieces) - first_tails] \
                # + [BERT_SEP] + second_word_pieces[:len(second_word_pieces) - (tails - first_tails)] + [BERT_SEP]
            # assert len(self.word_pieces) == 512
        self.word_ids, self.segment_ids = self._bert_tokens_to_ids()

    @classmethod
    def set_tokenizer(cls, name):
        cls._tokenizer = BertTokenizer.from_pretrained(BERT_VOCAB[name], do_lower_case=('uncased' in name))

    def _bert_input_wrapper(self):
        return BERT_CLS + ' ' + self.first + ' ' + BERT_SEP + ' ' + self.second + ' ' + BERT_SEP

    def _bert_tokenize(self, text=None):
        if text is None:
            return Example._tokenizer.tokenize(self.pair)
        else:
            return Example._tokenizer.tokenize(text)

    def _bert_tokens_to_ids(self):
        ids = Example._tokenizer.convert_tokens_to_ids(self.word_pieces)
        first_sep = self.word_pieces.index(BERT_SEP)
        assert first_sep > 0
        segment_ids = [0] * (first_sep + 1) + [1] * (len(self.word_pieces) - first_sep - 1)
        return ids, segment_ids
    
    def __str__(self):
        elements = []
        elements.append('Raw: ' + self.pair)
        elements.append('Bert: ' + str(list(zip(self.word_pieces, self.word_ids, self.segment_ids))))
        elements.append('Length: ' + str(self.length))
        elements.append('Truncated: ' + str(self.truncated))
        if self.label is not None:
            elements.append('Label: ' + ('entailment' if self.label == 1 else 'not_entailment'))
        return '\n'.join(elements)
