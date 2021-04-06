package simpl.interpreter.lib;

import simpl.interpreter.ConsValue;
import simpl.interpreter.Env;
import simpl.interpreter.FunValue;
import simpl.interpreter.PairValue;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.parser.Symbol;
import simpl.parser.ast.Expr;
import simpl.typing.TypeEnv;
import simpl.typing.TypeError;
import simpl.typing.TypeResult;

public class hd extends FunValue {

    public hd() {
        super(Env.empty, Symbol.symbol("_list_hd"), new Expr(){
            public TypeResult typecheck(TypeEnv E)throws TypeError{
                return null;
            }
            public Value eval(State s)throws RuntimeError{
                Value v=s.E.get(Symbol.symbol("_list_hd"));
                if(v instanceof ConsValue){
                    return ((ConsValue)v).v1;
                }
                throw new RuntimeError("hd can only be applied on lists");
            }
        });
    }
}
