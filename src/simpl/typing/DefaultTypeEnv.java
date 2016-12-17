package simpl.typing;

import simpl.parser.Symbol;

public class DefaultTypeEnv extends TypeEnv {

    private TypeEnv E;

    public DefaultTypeEnv() {
        Symbol fst=Symbol.symbol("fst");
        Symbol snd=Symbol.symbol("snd");
        Symbol hd=Symbol.symbol("hd");
        Symbol tl=Symbol.symbol("tl");
        Symbol iszero=Symbol.symbol("iszero");
        Symbol pred=Symbol.symbol("pred");
        Symbol succ=Symbol.symbol("succ");
        
        //pair和list的类型尚不确定，借助TypeVar
        TypeVar t1=new TypeVar(false);
        ArrowType fst_t=new ArrowType(new PairType(t1,new TypeVar(false)),t1);
        E=TypeEnv.of(TypeEnv.empty, fst, fst_t);
        
        TypeVar t2=new TypeVar(false);
        ArrowType snd_t=new ArrowType(new PairType(t2,new TypeVar(false)),t2);
        E=TypeEnv.of(E, snd, snd_t);
        
        TypeVar t3=new TypeVar(false);
        ArrowType hd_t=new ArrowType(new ListType(t3),t3);
        E=TypeEnv.of(E, hd, hd_t);
        
        TypeVar t4=new TypeVar(false);
        ArrowType tl_t=new ArrowType(new ListType(t4),t4);
        E=TypeEnv.of(E, tl, tl_t);
        
        ArrowType iszero_t=new ArrowType(Type.INT, Type.BOOL);
        ArrowType pred_t=new ArrowType(Type.INT, Type.INT);
        ArrowType succ_t=new ArrowType(Type.INT, Type.INT);
        E=TypeEnv.of(E, iszero, iszero_t);     
        E=TypeEnv.of(E, pred, pred_t);     
        E=TypeEnv.of(E, succ, succ_t);             
    }

    public Type get(Symbol x) {
        return E.get(x);
    }
}
