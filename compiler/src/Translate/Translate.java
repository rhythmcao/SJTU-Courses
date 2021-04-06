package Translate;

import Temp.Temp;
import Temp.Label;
import Tree.*;

import java.util.ArrayList;

public class Translate {
	public Frame.Frame frame;
	private int wordSize = 4;
	private Frag frags = null;

	public Translate(Frame.Frame f) {
		frame = f;
	}

	public Frag getResult() {
		return frags;
	}

	public void addFrag(Frag frag) {
		frag.next = frags;
		frags = frag;
	}

	public void procEntryExit(Level level, Exp body, boolean returnValue) {
		Stm b = null;
		if (returnValue)
			// 有返回值
			b = new MOVE(new TEMP(level.frame.RV()), body.unEx());
		else
			// 无返回值，按Nx处理
			b = body.unNx();
		b = level.frame.procEntryExit1(b);
		//加入函数段
		addFrag(new ProcFrag(b, level.frame));
	}

	private static Tree.Exp CONST(int value) {
		// 整形常数包装
		return new Tree.CONST(value);
	}
	
	public Exp Error() {
		return new Ex(CONST(0));
	}
	
	public Exp transNoOp() {
		return new Ex(new CONST(0));
	}

	//Nil, Int, String, Var Exp
	public Exp transNilExp() {
		// 用常数0代替
		return new Ex(new CONST(0));
	}

	public Exp transIntExp(int value) {
		return new Ex(new CONST(value));
	}

	public Exp transStringExp(String value) {
		// 新建一个标号
		Label l = new Label();
		//将字符串添加到段 (DataFrag) 中去,段存放在 translate 的私有段列表中
		addFrag(new DataFrag(l, frame.string(l, value)));
		return new Ex(new NAME(l)); //返回 NAME, 以标号作为标示
	}

	public Exp transVarExp(Exp ex) {
		return ex;
	}
	
	//Op, StringRel, Assign Exp
	public Exp transOpExp(int oper, Exp left, Exp right) {
		// 四则运算
		if (oper >= BINOP.PLUS && oper <= BINOP.DIV)
			return new Ex(new BINOP(oper, left.unEx(), right.unEx()));
		return new RelCx(oper, left, right);
	}

	public Exp transStringRelExp(Level currentL, int oper, Exp left, Exp right) {
		if(oper==Absyn.OpExp.EQ||oper==Absyn.OpExp.NE){
		// 字符串判断相等运算 left oper right
		Tree.Exp comp = currentL.frame.externalCall("stringEqual",
				new Tree.ExpList(left.unEx(), new Tree.ExpList(right.unEx(), null)));
		return new RelCx(oper, new Ex(comp), new Ex(new CONST(1)));}
		else{
		// 字符串比较运算 left oper right
		Tree.Exp comp = currentL.frame.externalCall("stringCompare",
				new Tree.ExpList(left.unEx(), new Tree.ExpList(right.unEx(), null)));
		return new RelCx(oper, new Ex(comp), new Ex(new CONST(0)));}
	}
	
	public Exp transAssignExp(Exp lvalue, Exp ex) {
		// 赋值运算 lvalue=ex
		return new Nx(new MOVE(lvalue.unEx(), ex.unEx()));
	}
	
	//Call, StdCall Exp
	public Exp transCallExp(Level home, Level dest, Label name, ArrayList<Exp> argValue) {
		// 抽取参数
		Tree.ExpList args = null;
		for (int i = argValue.size() - 1; i >= 0; --i)
			args = new Tree.ExpList(((Exp) argValue.get(i)).unEx(), args);
		Level l = home;
		Tree.Exp slnk = new TEMP(l.frame.FP()); // 静态链接
		// 找到Callee直接上层的静态链接
		while (dest.parent != l) {
			slnk = l.staticLink().acc.exp(slnk);
			l = l.parent;
		}
		// 将静态链接作为第一个参数
		args = new Tree.ExpList(slnk, args);
		return new Ex(new CALL(new NAME(name), args));
	}
	
	public Exp transStdCallExp(Level currentL, Label name, java.util.ArrayList<Exp> args_value) {
		Tree.ExpList args = null;
		for (int i = args_value.size() - 1; i >= 0; --i)
			args = new Tree.ExpList(((Exp) args_value.get(i)).unEx(), args);
		return new Ex(currentL.frame.externalCall(name.toString(), args));
	}
	
	// combine2Stm, combine2Exp
	public Exp combine2Stm(Exp e1, Exp e2) {
		if (e1 == null)
			return new Nx(e2.unNx());
		else if (e2 == null)
			return new Nx(e1.unNx());
		else
			return new Nx(new SEQ(e1.unNx(), e2.unNx()));
	}

	public Exp combine2Exp(Exp e1, Exp e2) {
		if (e1 == null)
			return new Ex(e2.unEx());
		else
			return new Ex(new ESEQ(e1.unNx(), e2.unEx()));
	}
	
	// Record, Array Exp
	public Exp transRecordExp(Level home, ArrayList<Exp> field) {
		Temp addr = new Temp();
		// 调用外部函数 allocRecord 为记录在 frame 上分配空间,
		// 并得存储空间首地址
		// allocRecord 执行如下的类 C 代码,注意它只负责分配空间
		// 初始化操作需要我们来完成
		// # int *allocRecord(int size)
		// # {int i;
		// # int *p, *a;
		// # p = a = (int *)malloc(size);
		// # for(i=0;i<size;i+=sizeof(int)) *p++ = 0;
		// # return a;
		// # }
		// 注意如果记录为空,也要用 1 个 word,否则每个域为一个 word,按顺序存放
		Tree.Exp alloc = home.frame.externalCall("allocRecord",
				new Tree.ExpList(new CONST((field.size() == 0 ? 1 : field.size()) * home.frame.wordSize()), null));
		Stm init = new EXPSTM(new CONST(0));// 初始化指令
		for (int i = field.size() - 1; i >= 0; i--) {
			// 为记录中每个域生成 MOVE 指令,将值复制到帧中的相应区域
			Tree.Exp offset = new BINOP(BINOP.PLUS, new TEMP(addr), new CONST(i * home.frame.wordSize()));
			Tree.Exp v = field.get(i).unEx();
			init = new SEQ(new MOVE(new MEM(offset), v), init);
		}
		// 返回记录的首地址
		return new Ex(new ESEQ(new SEQ(new MOVE(new TEMP(addr), alloc), init), new TEMP(addr)));
	}	
	
	public Exp transArrayExp(Level home, Exp init, Exp size) {
		//调用外部函数 initArray 为数组在 frame 上分配存储空间,并得到
		//存储空间首地址
		//initArray 执行如下的类 C 代码,需要提供数组大小与初始值
		//# int *initArray(int size, int init)
		//# {int i;
		//# int *a = (int *)malloc(size*sizeof(int));
		//# for(i=0;i<size;i++) a[i]=init;
		//# return a;
		//# }
		Tree.Exp alloc = home.frame.externalCall("initArray",
				new Tree.ExpList(size.unEx(), new Tree.ExpList(init.unEx(), null)));
		return new Ex(alloc);
	}

	// if, while, for, break Exp
	public Exp transIfThenElseExp(Exp test, Exp e_then, Exp e_else) {
		return new IfExp(test, e_then, e_else);
	}
	
	public Exp transWhileExp(Exp test, Exp body, Label done) {
		return new WhileExp(test, body, done);
	}

	public Exp transForExp(Level home, Access var, Exp low, Exp high, Exp body, Label done) {
		return new ForExp(home, var, low, high, body, done);
	}

	public Exp transBreakExp(Label done) {
		return new Nx(new JUMP(done));
	}
	
	// SimpleVar, SubscriptVar, FieldVar Exp
	public Exp transSimpleVar(Access access, Level home) {
		Tree.Exp res = new TEMP(home.frame.FP());
		Level l = home;
		while (l != access.home) {
			res = l.staticLink().acc.exp(res);
			l = l.parent;
		}
		return new Ex(access.acc.exp(res));
	}

	public Exp transSubscriptVar(Exp var, Exp idx) {
		Tree.Exp arr_addr = var.unEx();  //arr_addr 数组首地址
		//arr_off 偏移量, 等于下标乘以字长
		Tree.Exp arr_off = new BINOP(BINOP.MUL, idx.unEx(), new CONST(wordSize));
		return new Ex(new MEM(new BINOP(BINOP.PLUS, arr_addr, arr_off)));
	}

	public Exp transFieldVar(Exp var, int num) {
		Tree.Exp rec_addr = var.unEx(); //记录首地址
		//偏移量 (每个记录项目占一个 wordsize)
		Tree.Exp rec_off = new CONST(num * wordSize);
		return new Ex(new MEM(new BINOP(BINOP.PLUS, rec_addr, rec_off)));
	}	

}