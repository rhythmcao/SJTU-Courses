package simpl.parser.ast;

import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.typing.Type;
import simpl.typing.TypeEnv;
import simpl.typing.TypeError;
import simpl.typing.TypeResult;

public class Unit extends Expr {

    public String toString() {
        return "()";
    }

    public TypeResult typecheck(TypeEnv E) throws TypeError {
        return TypeResult.of(Type.UNIT);
    }

    public Value eval(State s) throws RuntimeError {
        return Value.UNIT;
    }
}
