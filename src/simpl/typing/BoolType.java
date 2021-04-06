package simpl.typing;

final class BoolType extends Type {

    protected BoolType() {
    }

    public boolean isEqualityType() {
        return true;
    }

    public Substitution unify(Type t) throws TypeError {
        if(t instanceof BoolType)
            return Substitution.IDENTITY;
        if(t instanceof TypeVar)
            return t.unify(this);
        throw new TypeMismatchError();
    }

    public boolean contains(TypeVar tv) {
        return false;
    }

    public Type replace(TypeVar a, Type t) {
        return Type.BOOL;
    }

    public String toString() {
        return "bool";
    }
}
