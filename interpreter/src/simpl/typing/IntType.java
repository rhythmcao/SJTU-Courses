package simpl.typing;

final class IntType extends Type {

    protected IntType() {
    }

    public boolean isEqualityType() {
        return true;
    }

    public Substitution unify(Type t) throws TypeError {
        if(t instanceof IntType)
            return Substitution.IDENTITY;
        if(t instanceof TypeVar)
            return t.unify(this);
        throw new TypeMismatchError();
    }

    public boolean contains(TypeVar tv) {
        return false;
    }

    public Type replace(TypeVar a, Type t) {
        return Type.INT;
    }

    public String toString() {
        return "int";
    }
}
