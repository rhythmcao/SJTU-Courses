package simpl.parser.ast;

import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.typing.Substitution;
import simpl.typing.Type;
import simpl.typing.TypeEnv;
import simpl.typing.TypeError;
import simpl.typing.TypeResult;

public class Seq extends BinaryExpr {

    public Seq(Expr l, Expr r) {
        super(l, r);
    }

    public String toString() {
        return "(" + l + " ; " + r + ")";
    }

    public TypeResult typecheck(TypeEnv E) throws TypeError {
        TypeResult lRes=l.typecheck(E);
        Substitution s1=lRes.t.unify(Type.UNIT);
        s1=s1.compose(lRes.s);
        TypeResult rRes=r.typecheck(s1.compose(E));
        return TypeResult.of(rRes.s.compose(s1),rRes.t);
    }

    public Value eval(State s) throws RuntimeError {
        Value v1=l.eval(s);
        Value v2=r.eval(s);
        return v2;
    }
}
