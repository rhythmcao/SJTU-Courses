package simpl.typing;

import simpl.parser.Symbol;

public abstract class TypeEnv {
    //获取Symbol x的类型
    public abstract Type get(Symbol x);
    //of实质上是在当前的类型环境中添加新的类型绑定x|->t
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
    //静态预置类型环境empty，没有任何类型绑定
    public static final TypeEnv empty = new TypeEnv() {
        public Type get(Symbol x) {
            return null;
        }
    };
}
