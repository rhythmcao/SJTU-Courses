package simpl.typing;

public abstract class Type {
    
    //判断是不是Equality Type
    public abstract boolean isEqualityType();
    
    //将本类型中所有的TypeVar变量都用类型t来取代, 通过Substitution的apply函数来调用
    public abstract Type replace(TypeVar a, Type t);
    
    //判断本类型的描述中是否包含指定的TypeVar变量tv
    public abstract boolean contains(TypeVar tv);
    
    //unify用来获取Substitution, 表示将本类型用Type t来替换, 只是收集替换信息，具体的替换借助Substitution的apply函数
    public abstract Substitution unify(Type t) throws TypeError;
    
    //预置静态类型常量
    public static final Type INT = new IntType();
    public static final Type BOOL = new BoolType();
    public static final Type UNIT = new UnitType();
}
