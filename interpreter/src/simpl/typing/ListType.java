package simpl.typing;

public final class ListType extends Type {

    public Type t;

    public ListType(Type t) {
        this.t = t;
    }

    public boolean isEqualityType() {
        return true;
    }

    public Substitution unify(Type t) throws TypeError {
        if(t instanceof TypeVar)
            return t.unify(this);
        if(t instanceof ListType)
            return this.t.unify(((ListType)t).t);
        throw new TypeMismatchError();
    }

    public boolean contains(TypeVar tv) {
        return t.contains(tv);
    }

    public Type replace(TypeVar a, Type t) {
        return new ListType(this.t.replace(a,t));
    }

    public String toString() {
        return t + " list";
    }
}
