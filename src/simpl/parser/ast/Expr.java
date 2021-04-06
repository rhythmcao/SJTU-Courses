package simpl.parser.ast;

import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.typing.TypeEnv;
import simpl.typing.TypeError;
import simpl.typing.TypeResult;

public abstract class Expr {
    //返回类型结果
    public abstract TypeResult typecheck(TypeEnv E) throws TypeError;

    /**
     * relies on side effect
     * 
     * @param s
     * @return
     * @throws RuntimeError
     */
    //返回表达式的值
    public abstract Value eval(State s) throws RuntimeError;
}
