package simpl.typing;

import simpl.parser.Symbol;

public abstract class TypeEnv {
    //��ȡSymbol x������
    public abstract Type get(Symbol x);
    //ofʵ�������ڵ�ǰ�����ͻ���������µ����Ͱ�x|->t
    public static TypeEnv of(final TypeEnv E, final Symbol x, final Type t) {
        return new TypeEnv() {
            public Type get(Symbol x1) {
                if (x == x1) return t;
                return E.get(x1);
            }

            public String toString() {
                return x + ":" + t + ";" + E;
            }
        };
    }
    //��̬Ԥ�����ͻ���empty��û���κ����Ͱ�
    public static final TypeEnv empty = new TypeEnv() {
        public Type get(Symbol x) {
            return null;
        }
    };
}
