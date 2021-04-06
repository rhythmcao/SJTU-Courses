package simpl.typing;

import simpl.parser.Symbol;

public class TypeVar extends Type {

    private static int tvcnt = 0;

    private boolean equalityType;
    private Symbol name;

    public TypeVar(boolean equalityType) {
        this.equalityType = equalityType;
        name = Symbol.symbol("tv" + ++tvcnt);
    }

    public boolean isEqualityType() {
        return equalityType;
    }

    public Substitution unify(Type t) throws TypeCircularityError {
        if(t instanceof TypeVar){
            //����һ��Replace��Substitution, �����е�TypeVar this�滻ΪTypeVar t
            return Substitution.of(this, t);
        }
        if(t.contains(this)){
            //TypeVar���ְ�������,�������޽�
            throw new TypeCircularityError();
        }else{
            return Substitution.of(this, t);
        }
    }

    public String toString() {
        return "" + name;
    }

    public boolean contains(TypeVar tv) {
        if(this.name.equals(tv.name))
            return true;
        else return false;
    }

    public Type replace(TypeVar a, Type t) {
        //���ж��ǲ�����Ҫ�滻��TypeVar
        if(this.name.equals(a.name))
            return t;
        else return this;
    }
}
