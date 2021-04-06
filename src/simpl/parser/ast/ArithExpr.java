package simpl.parser.ast;

import simpl.typing.Substitution;
import simpl.typing.Type;
import simpl.typing.TypeEnv;
import simpl.typing.TypeError;
import simpl.typing.TypeResult;

public abstract class ArithExpr extends BinaryExpr {

    public ArithExpr(Expr l, Expr r) {
        super(l, r);
    }

    public TypeResult typecheck(TypeEnv E) throws TypeError {
        TypeResult lRes=l.typecheck(E);
        Substitution s0=lRes.s;
        Substitution s1=lRes.t.unify(Type.INT);
        
        TypeResult rRes=r.typecheck(s1.compose(s0.compose(E)));
        Substitution s2=rRes.s;
        Substitution s3=rRes.t.unify(Type.INT);
        return TypeResult.of(s3.compose(s2.compose(s1.compose(s0))),Type.INT);
    }
}
