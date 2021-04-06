package simpl.parser.ast;

import com.sun.xml.internal.ws.assembler.jaxws.ValidationTubeFactory;

import simpl.interpreter.BoolValue;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.typing.Substitution;
import simpl.typing.Type;
import simpl.typing.TypeEnv;
import simpl.typing.TypeError;
import simpl.typing.TypeResult;

public class OrElse extends BinaryExpr {

    public OrElse(Expr l, Expr r) {
        super(l, r);
    }

    public String toString() {
        return "(" + l + " orelse " + r + ")";
    }

    public TypeResult typecheck(TypeEnv E) throws TypeError {
        TypeResult lRes=l.typecheck(E);
        Substitution s1=lRes.t.unify(Type.BOOL);
        s1=s1.compose(lRes.s);
        TypeResult rRes=r.typecheck(s1.compose(E));
        Substitution s2=rRes.t.unify(Type.BOOL);
        s2=s2.compose(s1);
        return TypeResult.of(s2,Type.BOOL);
    }

    public Value eval(State s) throws RuntimeError {
        BoolValue lv=(BoolValue)l.eval(s);
        if(lv.equals(Value.TRUE))
            return Value.TRUE;
        else return r.eval(s);
        
    }
}
