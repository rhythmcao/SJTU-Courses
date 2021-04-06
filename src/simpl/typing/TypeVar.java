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
            //返回一个Replace的Substitution, 将所有的TypeVar this替换为TypeVar t
            return Substitution.of(this, t);
        }
        if(t.contains(this)){
            //TypeVar中又包含本身,即类型无解
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
        //先判断是不是需要替换的TypeVar
        if(this.name.equals(a.name))
            return t;
        else return this;
    }
}
