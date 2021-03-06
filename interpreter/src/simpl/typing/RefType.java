package simpl.typing;

public final class RefType extends Type {

    public Type t;

    public RefType(Type t) {
        this.t = t;
    }

    public boolean isEqualityType() {
        return true;
    }

    public Substitution unify(Type t) throws TypeError {
        if(t instanceof TypeVar)
            return t.unify(this);
        if(t instanceof RefType)
            return this.t.unify(((RefType)t).t);
        throw new TypeMismatchError();
    }

    public boolean contains(TypeVar tv) {
        return t.contains(tv);
    }

    public Type replace(TypeVar a, Type t) {
        return new RefType(this.t.replace(a, t));
    }

    public String toString() {
        return t + " ref";
    }
}
