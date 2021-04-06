package simpl.interpreter.lib;

import simpl.interpreter.ConsValue;
import simpl.interpreter.Env;
import simpl.interpreter.FunValue;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.parser.Symbol;
import simpl.parser.ast.Expr;
import simpl.typing.TypeEnv;
import simpl.typing.TypeError;
import simpl.typing.TypeResult;

public class tl extends FunValue {

    public tl() {
        super(Env.empty, Symbol.symbol("_list_tl"), new Expr(){
            public TypeResult typecheck(TypeEnv E)throws TypeError{
                return null;
            }
            public Value eval(State s)throws RuntimeError{
                Value v=s.E.get(Symbol.symbol("_list_tl"));
                if(v instanceof ConsValue){
                    return ((ConsValue)v).v2;
                }
                throw new RuntimeError("tl can only be applied on lists");
            }
        });
    }
}
