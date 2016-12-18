package simpl.typing;

import simpl.parser.Symbol;

public abstract class Substitution {
    //Ӧ�ñ� Substitution �� �� ���� t, ����Ӧ��֮�������
    public abstract Type apply(Type t);

    private static final class Identity extends Substitution {
        public Type apply(Type t) {
            //Identity, ��ԭ����ʲô���;ͷ���ʲô����
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
        
        //������ b �е����� TypeVar a �滻Ϊ Type b
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
        //��Ӧ�� Substitution g, ��Ӧ�� Substitution f
        public Type apply(Type t) {
            return f.apply(g.apply(t));
        }
    }

    public static final Substitution IDENTITY = new Identity();
    
    //��̬����, ����TypeVar a -> Type t �� Substitution
    public static Substitution of(TypeVar a, Type t) {
        return new Replace(a, t);
    }
    
    //���� Substitution �� inner ����, ��Ӧ�� inner, ��Ӧ�� this
    public Substitution compose(Substitution inner) {
        return new Compose(this, inner);
    }

    public TypeEnv compose(final TypeEnv E) {
        return new TypeEnv() {
            //������, override get����, get��ͬʱӦ�ñ�Substitution
            public Type get(Symbol x) {
                return apply(E.get(x));
            }
        };
    }
}
