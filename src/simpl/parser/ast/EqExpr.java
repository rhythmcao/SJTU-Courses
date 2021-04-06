package simpl.parser.ast;

import simpl.typing.ListType;
import simpl.typing.PairType;
import simpl.typing.RefType;
import simpl.typing.Substitution;
import simpl.typing.Type;
import simpl.typing.TypeEnv;
import simpl.typing.TypeError;
import simpl.typing.TypeMismatchError;
import simpl.typing.TypeResult;
import simpl.typing.TypeVar;

public abstract class EqExpr extends BinaryExpr {

    public EqExpr(Expr l, Expr r) {
        super(l, r);
    }

    public TypeResult typecheck(TypeEnv E) throws TypeError {
        TypeResult lRes=l.typecheck(E);
        TypeResult rRes=r.typecheck(lRes.s.compose(E));
        Substitution s=rRes.t.unify(rRes.s.apply(lRes.t));
        if(s.apply(rRes.t).isEqualityType())
            return TypeResult.of(s.compose(rRes.s.compose(lRes.s)),Type.BOOL);
        throw new TypeMismatchError();
    }
}
