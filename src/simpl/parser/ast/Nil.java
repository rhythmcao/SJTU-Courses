package simpl.parser.ast;

import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.typing.ListType;
import simpl.typing.Substitution;
import simpl.typing.TypeEnv;
import simpl.typing.TypeError;
import simpl.typing.TypeResult;
import simpl.typing.TypeVar;

public class Nil extends Expr {

    public String toString() {
        return "nil";
    }

    public TypeResult typecheck(TypeEnv E) throws TypeError {
        TypeVar tv=new TypeVar(false);
        return TypeResult.of(Substitution.IDENTITY,new ListType(tv));
    }

    public Value eval(State s) throws RuntimeError {
        return Value.NIL;
    }
}
