package simpl.parser.ast;

import simpl.interpreter.RecValue;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.parser.Symbol;
import simpl.typing.Substitution;
import simpl.typing.Type;
import simpl.typing.TypeEnv;
import simpl.typing.TypeError;
import simpl.typing.TypeResult;

public class Name extends Expr {

    public Symbol x;

    public Name(Symbol x) {
        this.x = x;
    }

    public String toString() {
        return "" + x;
    }

    public TypeResult typecheck(TypeEnv E) throws TypeError {
        Type t=E.get(x);
        if(t==null)
            throw new TypeError("Undefined identifier");
        else return TypeResult.of(Substitution.IDENTITY,t);
    }

    public Value eval(State s) throws RuntimeError {
        Value v=s.E.get(x);
        if(v==null)
            throw new RuntimeError("Name \""+x+"\" is not defined");
        if(v instanceof RecValue)
            return ((RecValue)v).e.eval(s);
        return v;
    }
}
