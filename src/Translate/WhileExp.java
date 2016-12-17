package Translate;

import Temp.Label;
import Tree.*;


public class WhileExp extends Exp {
    Exp test = null; //��������
    Exp body = null; //ѭ����
    Label done = null; //��ɳ���
    WhileExp(Exp test, Exp body, Label done) {
        this.test = test;
        this.body = body;
        this.done = done;
    }
  //whileû�з���ֵ
    Tree.Exp unEx() {
        System.err.println("WhileExp.unEx()");
        return null;
    }

  //LABEL BEGIN:
  //if (test) goto T else goto DONE
  //LABEL T:
  //body
  //goto BEGIN
  //LABEL DONE:
    Stm unNx() {
        Label begin = new Label();
        Label t = new Label();
        return new SEQ(new LABEL(begin),
                new SEQ(test.unCx(t, done),
                        new SEQ(new LABEL(t),
                                new SEQ(body.unNx(),
                                        new SEQ(new JUMP(begin),
                                                new LABEL(done))))));
    }
    
  //whileֻ��һ������
    Stm unCx(Label t, Label f) {
        System.err.println("WhileExp.unCx()");
        return null;
    }
}