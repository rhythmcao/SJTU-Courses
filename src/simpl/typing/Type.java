package simpl.typing;

public abstract class Type {
    
    //�ж��ǲ���Equality Type
    public abstract boolean isEqualityType();
    
    //�������������е�TypeVar������������t��ȡ��, ͨ��Substitution��apply����������
    public abstract Type replace(TypeVar a, Type t);
    
    //�жϱ����͵��������Ƿ����ָ����TypeVar����tv
    public abstract boolean contains(TypeVar tv);
    
    //unify������ȡSubstitution, ��ʾ����������Type t���滻, ֻ���ռ��滻��Ϣ��������滻����Substitution��apply����
    public abstract Substitution unify(Type t) throws TypeError;
    
    //Ԥ�þ�̬���ͳ���
    public static final Type INT = new IntType();
    public static final Type BOOL = new BoolType();
    public static final Type UNIT = new UnitType();
}
