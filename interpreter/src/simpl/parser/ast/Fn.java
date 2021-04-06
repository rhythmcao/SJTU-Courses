package simpl.parser.ast;

import simpl.interpreter.FunValue;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.parser.Symbol;
import simpl.typing.ArrowType;
import simpl.typing.Type;
import simpl.typing.TypeEnv;
import simpl.typing.TypeError;
import simpl.typing.TypeResult;
import simpl.typing.TypeVar;

public class Fn extends Expr {

    public Symbol x;
    public Expr e;

    public Fn(Symbol x, Expr e) {
        this.x = x;
        this.e = e;
    }

    public String toString() {
        return "(fn " + x + "." + e + ")";
    }

    public TypeResult typecheck(TypeEnv E) throws TypeError {
        TypeVar tv=new TypeVar(false);
        TypeResult Res=e.typecheck(TypeEnv.of(E, x, tv));
        return TypeResult.of(Res.s,new ArrowType(Res.s.apply(tv),Res.t));
    }

    public Value eval(State s) throws RuntimeError {
        return new FunValue(s.E.clone(),x,e);
    }
}