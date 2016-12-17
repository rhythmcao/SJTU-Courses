package simpl.parser.ast;

import simpl.interpreter.IntValue;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;

public class Mod extends ArithExpr {

    public Mod(Expr l, Expr r) {
        super(l, r);
    }

    public String toString() {
        return "(" + l + " % " + r + ")";
    }

    public Value eval(State s) throws RuntimeError {
        IntValue iv1=(IntValue)l.eval(s);
        IntValue iv2=(IntValue)r.eval(s);
        if(iv2.equals(Value.ZERO))
            throw new RuntimeError("Integer division by zero or modulo by zero.");
        return new IntValue(iv1.n%iv2.n);
    }
}
