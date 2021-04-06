package Frame;

import Assem.InstrList;
import Temp.Label;
import Temp.Temp;
import Temp.TempList;
import Temp.TempMap;
import Tree.Stm;
import Util.BoolList;

public abstract class Frame implements TempMap {
	abstract public Frame newFrame(Label name, BoolList formals);//newFrame(name and escape info)
    public Label name; //name
    public AccessList formals=null; //local variables list
    abstract public Access allocLocal(boolean escape);//alloc new local variable(whether escape)
    abstract public Tree.Exp externalCall(String func, Tree.ExpList args);//external func
    abstract public Temp FP();//Frame pointer
    abstract public Temp SP();//Stack pointer
    abstract public Temp RA();//Return address
    abstract public Temp RV();//Return value
    abstract public TempList registers(); //registers list
    abstract public Stm procEntryExit1(Stm body);//add extra func call instruction
    abstract public InstrList procEntryExit2(InstrList body);//same
    abstract public InstrList procEntryExit3(InstrList body);//same
    abstract public String string(Label lab, String lit);
    abstract public InstrList codegen(Stm stm);//generate MIPS instruction
    abstract public int wordSize();// return wordsize(typically 4 bytes)
    abstract public TempList colors();
}