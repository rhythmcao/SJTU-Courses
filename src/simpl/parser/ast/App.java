package simpl.parser.ast;

import simpl.interpreter.Env;
import simpl.interpreter.FunValue;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.typing.ArrowType;
import simpl.typing.Substitution;
import simpl.typing.TypeEnv;
import simpl.typing.TypeError;
import simpl.typing.TypeMismatchError;
import simpl.typing.TypeResult;
import simpl.typing.TypeVar;

public class App extends BinaryExpr {

    public App(Expr l, Expr r) {
        super(l, r);
    }

    public String toString() {
        return "(" + l + " " + r + ")";
    }

    public TypeResult typecheck(TypeEnv E) throws TypeError {
        TypeResult lRes = l.typecheck(E);
        TypeResult rRes = r.typecheck(lRes.s.compose(E));
        ArrowType at = new ArrowType(new TypeVar(true), new TypeVar(true));
        Substitution s0 = rRes.s.compose(lRes.s);
        Substitution s1 = s0.apply(lRes.t).unify(s0.apply(at));
        s0 = s0.compose(s1);
        try{
            Substitution s2 = s0.apply(rRes.t).unify(s0.apply(at.t1));
            s0 = s0.compose(s2);
        }catch (TypeMismatchError e){
        }

        return TypeResult.of(s0,s0.apply(at.t2));
    }

    public Value eval(State s) throws RuntimeError {
        FunValue lval=(FunValue)l.eval(s);
        Value rval=r.eval(s);
        return lval.e.eval(State.of(new Env(lval.E,lval.x,rval), s.M, s.p));
    }
}
