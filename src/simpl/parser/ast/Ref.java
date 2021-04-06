package simpl.parser.ast;

import simpl.interpreter.Int;
import simpl.interpreter.RefValue;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.typing.RefType;
import simpl.typing.TypeEnv;
import simpl.typing.TypeError;
import simpl.typing.TypeResult;
import sun.security.timestamp.TSRequest;

public class Ref extends UnaryExpr {

    public Ref(Expr e) {
        super(e);
    }

    public String toString() {
        return "(ref " + e + ")";
    }

    public TypeResult typecheck(TypeEnv E) throws TypeError {
        TypeResult Res=e.typecheck(E);
        return TypeResult.of(Res.s,new RefType(Res.t));
    }

    public Value eval(State s) throws RuntimeError {
        Value v=e.eval(s);
        Int p=s.p;
        s.p.set(p.get()+1);
        s.M.put(p.get(), v);
        return new RefValue(p.get());
    }
}
