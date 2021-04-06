package simpl.parser.ast;

import simpl.interpreter.BoolValue;
import simpl.interpreter.IntValue;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;

public class Greater extends RelExpr {

    public Greater(Expr l, Expr r) {
        super(l, r);
    }

    public String toString() {
        return "(" + l + " > " + r + ")";
    }

    public Value eval(State s) throws RuntimeError {
        IntValue iv1=(IntValue)l.eval(s);
        IntValue iv2=(IntValue)r.eval(s);
        return new BoolValue(iv1.n>iv2.n);
    }
}
