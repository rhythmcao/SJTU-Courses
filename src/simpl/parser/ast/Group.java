package simpl.parser.ast;

import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.typing.TypeEnv;
import simpl.typing.TypeError;
import simpl.typing.TypeResult;

public class Group extends UnaryExpr {

    public Group(Expr e) {
        super(e);
    }

    public String toString() {
        return "" + e;
    }

    public TypeResult typecheck(TypeEnv E) throws TypeError {
        return e.typecheck(E);
    }

    public Value eval(State s) throws RuntimeError {
        return e.eval(s);
    }
}
