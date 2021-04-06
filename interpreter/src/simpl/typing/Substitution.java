package simpl.typing;

import simpl.parser.Symbol;

public abstract class Substitution {
    //应用本 Substitution 类 于 类型 t, 返回应用之后的类型
    public abstract Type apply(Type t);

    private static final class Identity extends Substitution {
        public Type apply(Type t) {
            //Identity, 即原来是什么类型就返回什么类型
            return t;
        }
    }

    private static final class Replace extends Substitution {
        private TypeVar a;
        private Type t;

        public Replace(TypeVar a, Type t) {
            this.a = a;
            this.t = t;
        }
        
        //将类型 b 中的所有 TypeVar a 替换为 Type b
        public Type apply(Type b) {
            return b.replace(a, t);
        }
    }

    private static final class Compose extends Substitution {
        private Substitution f, g;

        public Compose(Substitution f, Substitution g) {
            this.f = f;
            this.g = g;
        }
        //先应用 Substitution g, 再应用 Substitution f
        public Type apply(Type t) {
            return f.apply(g.apply(t));
        }
    }

    public static final Substitution IDENTITY = new Identity();
    
    //静态函数, 返回TypeVar a -> Type t 的 Substitution
    public static Substitution of(TypeVar a, Type t) {
        return new Replace(a, t);
    }
    
    //将本 Substitution 与 inner 复合, 先应用 inner, 再应用 this
    public Substitution compose(Substitution inner) {
        return new Compose(this, inner);
    }

    public TypeEnv compose(final TypeEnv E) {
        return new TypeEnv() {
            //匿名类, override get函数, get的同时应用本Substitution
            public Type get(Symbol x) {
                return apply(E.get(x));
            }
        };
    }
}
