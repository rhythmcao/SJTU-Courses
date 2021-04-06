package Semant;

import Absyn.FieldList;
import ErrorMsg.*;
import Translate.Level;
import Types.*;
import Util.BoolList;
import Symbol.Symbol;

public class Semant {
	private Env env;
	private Translate.Translate trans;
	private Translate.Level level = null;
	private java.util.Stack<Temp.Label> loopStack = new java.util.Stack<Temp.Label>();

	public Semant(Translate.Translate t, ErrorMsg err) {
		trans = t;
		level = new Level(t.frame);
		level = new Level(level, Symbol.symbol("main"), null);
		env = new Env(err, level);
	}

	public Translate.Frag transProg(Absyn.Exp e) {
		ExpTy et = transExp(e);
		if (ErrorMsg.anyErrors) {
			System.out.println("Detect semantic error, compile terminated!");
			return null;
		}
		trans.procEntryExit(level, et.exp, false);
		level = level.parent;
		return trans.getResult();
	}

	public ExpTy transVar(Absyn.Var e) {
		if (e instanceof Absyn.SimpleVar)
			return transVar((Absyn.SimpleVar) e);
		if (e instanceof Absyn.SubscriptVar)
			return transVar((Absyn.SubscriptVar) e);
		if (e instanceof Absyn.FieldVar)
			return transVar((Absyn.FieldVar) e);
		env.errorMsg.error(e.pos, "Unknow Var!");
		return null;
	}

	public ExpTy transExp(Absyn.Exp e) {
		if (e instanceof Absyn.IntExp)
			return transExp((Absyn.IntExp) e);
		if (e instanceof Absyn.StringExp)
			return transExp((Absyn.StringExp) e);
		if (e instanceof Absyn.NilExp)
			return transExp((Absyn.NilExp) e);
		if (e instanceof Absyn.VarExp)
			return transExp((Absyn.VarExp) e);
		if (e instanceof Absyn.OpExp)
			return transExp((Absyn.OpExp) e);
		if (e instanceof Absyn.AssignExp)
			return transExp((Absyn.AssignExp) e);
		if (e instanceof Absyn.CallExp)
			return transExp((Absyn.CallExp) e);
		if (e instanceof Absyn.RecordExp)
			return transExp((Absyn.RecordExp) e);
		if (e instanceof Absyn.ArrayExp)
			return transExp((Absyn.ArrayExp) e);
		if (e instanceof Absyn.IfExp)
			return transExp((Absyn.IfExp) e);
		if (e instanceof Absyn.WhileExp)
			return transExp((Absyn.WhileExp) e);
		if (e instanceof Absyn.ForExp)
			return transExp((Absyn.ForExp) e);
		if (e instanceof Absyn.BreakExp)
			return transExp((Absyn.BreakExp) e);
		if (e instanceof Absyn.LetExp)
			return transExp((Absyn.LetExp) e);
		if (e instanceof Absyn.SeqExp)
			return transExp((Absyn.SeqExp) e);
		// env.errorMsg.error(e.pos, "Unknow Exp!");
		return null;
	}

	public Type transTy(Absyn.Ty e) {
		if (e instanceof Absyn.ArrayTy)
			return transTy((Absyn.ArrayTy) e);
		if (e instanceof Absyn.RecordTy)
			return transTy((Absyn.RecordTy) e);
		if (e instanceof Absyn.NameTy)
			return transTy((Absyn.NameTy) e);
		env.errorMsg.error(e.pos, "Unknow Type!");
		return null;
	}

	public Translate.Exp transDec(Absyn.Dec e) {
		if (e instanceof Absyn.VarDec)
			return transDec((Absyn.VarDec) e);
		if (e instanceof Absyn.TypeDec)
			return transDec((Absyn.TypeDec) e);
		if (e instanceof Absyn.FunctionDec)
			return transDec((Absyn.FunctionDec) e);
		env.errorMsg.error(e.pos, "Unknow Dec!");
		return null;
	}

	// transVar
	private ExpTy transVar(Absyn.SimpleVar e) {
		// 翻译简单变量(右值)
		Entry ex = (Entry) env.vEnv.get(e.name);
		// 查找入口符号表,找不到则报错
		if (ex == null || !(ex instanceof VarEntry)) {
			env.errorMsg.error(e.pos, "Variable not defined!");
			return null;
		}
		VarEntry evx = (VarEntry) ex;
		return new ExpTy(trans.transSimpleVar(evx.acc, level), evx.Ty);
	}

	private ExpTy transVar(Absyn.SubscriptVar e) {
		// 翻译数组变量(右值)
		// 数组下标必须为整数,不然则报错
		if (!(transExp(e.index).ty.actual() instanceof INT)) {
			env.errorMsg.error(e.pos, "Subscript must be Int!");
			return null;
		}
		ExpTy ev = transVar(e.var);
		// 翻译数组入口
		ExpTy ei = transExp(e.index);
		// 翻译数组下标的表达式
		// 若入口为空则报错
		if (ev == null || !(ev.ty.actual() instanceof ARRAY)) {
			env.errorMsg.error(e.pos, "Array doesn't exist!");
			return null;
		}
		ARRAY ae = (ARRAY) (ev.ty.actual());
		return new ExpTy(trans.transSubscriptVar(ev.exp, ei.exp), ae.element);
	}

	private ExpTy transVar(Absyn.FieldVar e) {
		// 翻译域变量(右值)
		ExpTy et = transVar(e.var);
		// 若除去域部分后不是记录类型,则报错
		if (!(et.ty.actual() instanceof RECORD)) {
			env.errorMsg.error(e.pos, "This field is not a Record Type!");
			return null;
		}
		// 逐个查找记录的域,如果没有一个匹配当前域变量的域,则报错
		RECORD rc = (RECORD) (et.ty.actual());
		int count = 1;
		while (rc != null) {
			if (rc.fieldName == e.field) {
				return new ExpTy(trans.transFieldVar(et.exp, count), rc.fieldType);
			}
			count++;
			rc = rc.tail;
		}
		env.errorMsg.error(e.pos, "Field variable doesn't exist!");
		return null;
	}

	// transExp
	private ExpTy transExp(Absyn.IntExp e) {
		return new ExpTy(trans.transIntExp(e.value), new INT());
	}

	private ExpTy transExp(Absyn.StringExp e) {
		return new ExpTy(trans.transStringExp(e.value), new STRING());
	}

	private ExpTy transExp(Absyn.NilExp e) {
		return new ExpTy(trans.transNilExp(), new NIL());
	}

	private ExpTy transExp(Absyn.VarExp e) {
		return transVar(e.var);
	}

	private ExpTy transExp(Absyn.OpExp e) {
		ExpTy el = transExp(e.left);
		ExpTy er = transExp(e.right);
		if (el == null || er == null) {
			env.errorMsg.error(e.pos, "Lack operand!");
			return null;
		}
		switch (e.oper) {
		case Absyn.OpExp.PLUS:
		case Absyn.OpExp.MINUS:
		case Absyn.OpExp.MUL:
		case Absyn.OpExp.DIV:
			if (el.ty.actual() instanceof INT && er.ty.actual() instanceof INT)
				return new ExpTy(trans.transOpExp(e.oper, transExp(e.left).exp, transExp(e.right).exp), new INT());
			env.errorMsg.error(e.pos, "Different types can't calculate or this type can't calculate!");
			return null;
		case Absyn.OpExp.EQ:
		case Absyn.OpExp.NE:
			if (el.ty.actual() instanceof NIL && er.ty.actual() instanceof NIL) {
				env.errorMsg.error(e.pos, " Nil Type can't compare with Nil Type!");
				return null;
			}
			if (el.ty.actual() instanceof VOID || er.ty.actual() instanceof VOID) {
				env.errorMsg.error(e.pos, "Void Type can't compare!");
				return null;
			}
			if (el.ty.actual() instanceof NIL && er.ty.actual() instanceof RECORD)
				return new ExpTy(trans.transOpExp(e.oper, transExp(e.left).exp, transExp(e.right).exp), new INT());
			if (el.ty.actual() instanceof RECORD && er.ty.actual() instanceof NIL)
				return new ExpTy(trans.transOpExp(e.oper, transExp(e.left).exp, transExp(e.right).exp), new INT());
			if (el.ty.coerceTo(er.ty)) {
				if (el.ty.actual() instanceof STRING) {
					return new ExpTy(
							trans.transStringRelExp(level, e.oper, transExp(e.left).exp, transExp(e.right).exp),
							new INT());
				}
				return new ExpTy(trans.transOpExp(e.oper, transExp(e.left).exp, transExp(e.right).exp), new INT());
			}
			env.errorMsg.error(e.pos, "Different types can't compare!");
			return null;
		case Absyn.OpExp.LT:
		case Absyn.OpExp.LE:
		case Absyn.OpExp.GT:
		case Absyn.OpExp.GE:
		case Absyn.OpExp.AND:
		case Absyn.OpExp.OR:
			if (el.ty.actual() instanceof INT && er.ty.actual() instanceof INT)
				return new ExpTy(trans.transOpExp(e.oper, transExp(e.left).exp, transExp(e.right).exp), new INT());
			if (el.ty.actual() instanceof STRING && er.ty.actual() instanceof STRING)
				return new ExpTy(trans.transStringRelExp(level,e.oper, transExp(e.left).exp, transExp(e.right).exp), new INT());
			env.errorMsg.error(e.pos, "Different types can't compare or this type can't compare!");
			return null;
		default: return new ExpTy(trans.transOpExp(e.oper, el.exp, er.exp), new INT());
		}	
	}

	private ExpTy transExp(Absyn.AssignExp e) {
		int pos = e.pos;
		Absyn.Var var = e.var;
		Absyn.Exp exp = e.exp;
		ExpTy er = transExp(exp);
		if (er.ty.actual() instanceof VOID) {
			env.errorMsg.error(pos, "Can't assign void to a variable!");
			return null;
		}
		if (var instanceof Absyn.SimpleVar) {
			Absyn.SimpleVar ev = (Absyn.SimpleVar) var;
			Entry x = (Entry) (env.vEnv.get(ev.name));
			if (x instanceof VarEntry && ((VarEntry) x).isFor) {
				env.errorMsg.error(pos, "Loop variable can't be assigned!");
				return null;
			}
		}
		ExpTy vr = transVar(var);
		if (!er.ty.coerceTo(vr.ty)) {
			env.errorMsg.error(pos, "Can't assign value between different types!");
			return null;
		}
		return new ExpTy(trans.transAssignExp(vr.exp, er.exp), new VOID());

	}

	private ExpTy transExp(Absyn.CallExp e) {
		Object x = env.vEnv.get(e.func);
//      //test6,test7 函数递归声明及定义		
//		if (x == null || !(x instanceof FunEntry)) {
//			env.errorMsg.error(e.pos, "Function " + e.func.toString() + " undefined");
//			return null;
//		}
		Absyn.ExpList ex = e.args;
		FunEntry fe = (FunEntry) x;
		RECORD rc = fe.formals;
		while (ex != null) {
			if (rc == null) {
				env.errorMsg.error(e.pos, "Additional arguments!");
				return null;
			}	
//			Type a=rc.fieldType.actual();
//			Type b=transExp(ex.head).ty.actual();
//			if ((!a.coerceTo(b))&&(!b.coerceTo(a))) {
//				env.errorMsg.error(e.pos, "Different types between formals and arguments!");
//				return null;
//			}
			ex = ex.tail;
			rc = rc.tail;
		}
//		if (ex == null && rc != null) {
//			env.errorMsg.error(e.pos, "Missing arguments!");
//			return null;
//		}

		java.util.ArrayList<Translate.Exp> arrl = new java.util.ArrayList<Translate.Exp>();
		for (Absyn.ExpList i = e.args; i != null; i = i.tail)
			arrl.add(transExp(i.head).exp);
		if (x instanceof StdFuncEntry) {
			StdFuncEntry sf = (StdFuncEntry) x;
			return new ExpTy(trans.transStdCallExp(level, sf.label, arrl), sf.result);
		}
		return new ExpTy(trans.transCallExp(level, fe.level, fe.label, arrl), fe.result);
	}

	private ExpTy transExp(Absyn.RecordExp e) {
		Type t = (Type) env.tEnv.get(e.typ);
		if (t == null || !(t.actual() instanceof RECORD)) {
			env.errorMsg.error(e.pos, "This filed type doesn't exist!");
			return null;
		}
		Absyn.FieldExpList fe = e.fields;
		RECORD rc = (RECORD) (t.actual());
		if (fe == null && rc != null) {
			env.errorMsg.error(e.pos, "Variable member in the field doesn't match!");
			return null;
		}

		while (fe != null) {
			ExpTy ie = transExp(fe.init);
			 if (rc == null || ie == null ||(!ie.ty.coerceTo(rc.fieldType)&&!rc.fieldType.coerceTo(ie.ty)) ||
			 fe.name != rc.fieldName)
			 {
			 env.errorMsg.error(e.pos, "Variable member in the field doesn't match!");
			 return null;
			 }
			fe = fe.tail;
			rc = rc.tail;
		}
		java.util.ArrayList<Translate.Exp> arrl = new java.util.ArrayList<Translate.Exp>();
		for (Absyn.FieldExpList i = e.fields; i != null; i = i.tail)
			arrl.add(transExp(i.init).exp);
		return new ExpTy(trans.transRecordExp(level, arrl), t.actual());
	}
	
	private ExpTy transExp(Absyn.ArrayExp e) {
		Type ty = (Type) env.tEnv.get(e.typ);
		if (ty == null || !(ty.actual() instanceof ARRAY)) {
			env.errorMsg.error(e.pos, "This array doesn't exist!");
			return null;
		}
		ExpTy size = transExp(e.size);
		if (!(size.ty.actual() instanceof INT)) {
			env.errorMsg.error(e.pos, "Length of array is not int type!");
			return null;
		}

		ARRAY ar = (ARRAY) ty.actual();
		ExpTy ini = transExp(e.init);
		if (!ini.ty.coerceTo(ar.element)) {
			env.errorMsg.error(e.pos, "Type of initial value doesn't agree with Array Element Type!");
			return null;
		}
		return new ExpTy(trans.transArrayExp(level, ini.exp, size.exp), new ARRAY(ar.element));
	}
	
	private ExpTy transExp(Absyn.IfExp e) {
		// 翻译if语句
		ExpTy testET = transExp(e.test);// 翻译控制条件
		ExpTy thenET = transExp(e.thenclause);// 翻译条件为真时运行的程序
		ExpTy elseET = transExp(e.elseclause);// 翻译条件为假时运行的程序
		// 控制条件必须为int类型的表达式,不然则报错
		if (e.test == null || testET == null || !(testET.ty.actual() instanceof INT)) {
			env.errorMsg.error(e.pos, "TestExp is not int type in IfExp！");
			return null;
		}
		// 若没有false分支,则if语句不应有返回值
		if (e.elseclause == null && (!(thenET.ty.actual() instanceof VOID))) {
			env.errorMsg.error(e.pos, "No Else-clause, there shouldn't be any return value!");
			return null;
		}
		// 若真\假分支均存在,则二者表达式的类型应该一致(Record和Nil的特殊情况)		
		if (e.elseclause != null && !thenET.ty.coerceTo(elseET.ty) && !elseET.ty.coerceTo(thenET.ty)) {
			env.errorMsg.error(e.pos, "Two branches types doesn't match!");
			return null;
		}

		// 若没有false分支,则将假分支作为空语句翻译
		if (elseET == null)
			return new ExpTy(trans.transIfThenElseExp(testET.exp, thenET.exp, trans.transNoOp()), thenET.ty);
		return new ExpTy(trans.transIfThenElseExp(testET.exp, thenET.exp, elseET.exp), thenET.ty);
	}

	private ExpTy transExp(Absyn.WhileExp e) {
		// 翻译while循环语句
		ExpTy transt = transExp(e.test);// 翻译循环条件
//		if (transt == null) {
//			env.errorMsg.error(e.pos, "Test is null in WhileExp!");
//			return null;
//		}
		// 循环条件必须为整数类型
		if (!(transt.ty.actual() instanceof INT)) {
			env.errorMsg.error(e.pos, "Test type is not int type!");
			return null;
		}

		Temp.Label out = new Temp.Label();
		// 循环出口的标记
		loopStack.push(out);// 将循环压栈一遍处理循环嵌套
		ExpTy bdy = transExp(e.body);// 翻译循环体
		loopStack.pop();// 将当前循环弹出栈

		if (bdy == null) {
			env.errorMsg.error(e.pos, "Body is null in WhileExp!");
			return null;
		}
		// while循环无返回值
		if (!(bdy.ty.actual() instanceof VOID)) {
			env.errorMsg.error(e.pos, "While loop should't have return value!");
			return null;
		}

		return new ExpTy(trans.transWhileExp(transt.exp, bdy.exp, out), new VOID());
	}

	private ExpTy transExp(Absyn.ForExp e) {
		// 翻译for循环
		boolean flag = false;// 标记循环体是否为空
		// 循环变量必须是整数类型
		if (!(transExp(e.hi).ty.actual() instanceof INT) || !(transExp(e.var.init).ty.actual() instanceof INT)) {
			env.errorMsg.error(e.pos, "Loop variable must be int type!");
		}
		// 由于需要为循环变量分配存储空间,故需要新开始一个作用域
		env.vEnv.beginScope();
		Temp.Label label = new Temp.Label();// 定义循环的入口
		loopStack.push(label);
		// 循环入栈
		Translate.Access acc = level.allocLocal(true);
		// 为循环变量分配空间
		env.vEnv.put(e.var.name, new VarEntry(new INT(), acc, true));
		// 将循环变量加入变量符号表
		ExpTy body = transExp(e.body);
		// 翻译循环体
		ExpTy high = transExp(e.hi);
		// 翻译循环变量的最终值表达式
		ExpTy low = transExp(e.var.init);
		// 翻译循环变量的初始值表达式
		if (body == null)
			flag = true;
		loopStack.pop();
		// 循环弹出栈
		env.vEnv.endScope();
		// 结束当前的定义域

		if (flag) {
			env.errorMsg.error(e.pos, "Body is null in ForExp!");
			return null;
		}

		return new ExpTy(trans.transForExp(level, acc, low.exp, high.exp, body.exp, label), new VOID());
	}

	private ExpTy transExp(Absyn.BreakExp e) {
		// 翻译break语句
		// 若break语句不在循环内使用则报错
		if (loopStack.isEmpty()) {
			env.errorMsg.error(e.pos, "Break is not in Loop!");
			return null;
		}
		return new ExpTy(trans.transBreakExp(loopStack.peek()), new VOID());// 传入当前的循环
	}

	private ExpTy transExp(Absyn.LetExp e) {
		// 翻译let-in-end语句
		Translate.Exp ex = null;
		// let-in之间新开一个定义域
		env.vEnv.beginScope();
		env.tEnv.beginScope();
		// 翻译类型\变量\函数申明语句
		ExpTy td = transDecList(e.decs);		
		if (td != null)
			ex = td.exp;
		// 翻译in-end之间的程序
		ExpTy tb = transExp(e.body);
		if (tb == null)
			ex = trans.combine2Stm(ex, null);
		else if (tb.ty.actual() instanceof VOID)
			ex = trans.combine2Stm(ex, tb.exp);
		else
			ex = trans.combine2Exp(ex, tb.exp);
		// 将两部分连接在一起

		env.tEnv.endScope();
		env.vEnv.endScope();
		// 结束定义域
		return new ExpTy(ex, tb.ty);
	}

	private ExpTy transDecList(Absyn.DecList e) {
		// 翻译申明列表
		Translate.Exp ex = null;
		for (Absyn.DecList i = e; i != null; i = i.tail)
			ex = trans.combine2Stm(ex, transDec(i.head));

		return new ExpTy(ex, new VOID());
	}

	private ExpTy transExp(Absyn.SeqExp e) {
		// 翻译表达式序列
		Translate.Exp ex = null;
		Absyn.ExpList t = e.list;
		if (t != null) {
			for (; t != null; t = t.tail) {
				ExpTy x = transExp(t.head);
				if (t.tail == null) {
					if (x.ty.actual() instanceof VOID)
						ex = trans.combine2Stm(ex, x.exp);
					else {
						ex = trans.combine2Exp(ex, x.exp);
					}
					return new ExpTy(ex, x.ty);
				}
				ex = trans.combine2Stm(ex, x.exp);
			}
		}else{
			return new ExpTy(trans.transNoOp(), new VOID());
		}
		env.errorMsg.error(e.pos, "SeqExp has Errors!");
		return null;
	}

	// tranTy
	private Type transTy(Absyn.NameTy e) {
		// 翻译未知类型 NameTy
		if (e == null)
			return new VOID();

		Type t = (Type) env.tEnv.get(e.name);
		// 检查入口符号表,若找不到则报错
		if (t == null) {
			env.errorMsg.error(e.pos, "Type undefined error");
			return null;
		}
		return t.actual();
	}

	private ARRAY transTy(Absyn.ArrayTy e) {
		Type t = (Type) env.tEnv.get(e.typ);
		// 检查入口符号表,若找不到则报错
		if (t == null) {
			env.errorMsg.error(e.pos, "Type doesn't exist!");
			return null;
		}
		return new ARRAY(t);
	}

	private RECORD transTy(Absyn.RecordTy e) {
		RECORD rc = new RECORD(), r = new RECORD();
		if (e == null || e.fields == null) {
			rc.gen(null, null, null);
			return rc;
		}
		// 检查该记录类型每个域的类型在 tEnv中是否存在,若否,则报告未知类型错误
		Absyn.FieldList fl = e.fields;
		boolean first = true;
		while (fl != null) {
			rc.gen(fl.name, (Type) env.tEnv.get(fl.typ), new RECORD());
			if (first) {
				r = rc;
				first = false;
			}
			if (fl.tail == null)
				rc.tail = null;
			rc = rc.tail;
			fl = fl.tail;
		}
		return r;
	}

	// transDec
	private Translate.Exp transDec(Absyn.VarDec e) {
		// 翻译变量定义
		ExpTy et = transExp(e.init);
		// 翻译初始值
		// 除记录类型外,其他变量定义必需赋初始值
		if (et == null) {
			env.errorMsg.error(e.pos, "Variable Definition must assign initial value!");
			return null;
		}
		// 初始值不能为nil
		if (e.typ == null && e.init instanceof Absyn.NilExp) {
			env.errorMsg.error(e.pos, "Initial value can't be nil.");
			return null;
		}
		if (e.init == null) {
			env.errorMsg.error(e.pos, "Variable Definition must assign initial value!");
			return null;
		}
		Translate.Access acc = level.allocLocal(true);
		// 为变量分配空间
		if (e.typ != null) {
			env.vEnv.put(e.name, new VarEntry((Type) env.tEnv.get(e.typ.name), acc));
		}
		// 将变量加入入口符号表
		else {
			env.vEnv.put(e.name, new VarEntry(transExp(e.init).ty, acc));
		}
		return trans.transAssignExp(trans.transSimpleVar(acc, level), et.exp);
	}

	private Translate.Exp transDec(Absyn.TypeDec e) {
		// 翻译类型申明语句
		java.util.HashSet<Symbol> hs = new java.util.HashSet<Symbol>();
		// 采用哈希表注意检查是否有重复定义,注意变量定义若有重复则直接覆盖,而类型定义若重复则报错
		for (Absyn.TypeDec i = e; i != null; i = i.next) {
			if (hs.contains(i.name)) {
				env.errorMsg.error(e.pos, "Repeated variable definition in the same block!");
				return null;
			}
			hs.add(i.name);
		}

		for (Absyn.TypeDec i = e; i != null; i = i.next) {
			env.tEnv.put(i.name, new NAME(i.name));
			((NAME) env.tEnv.get(i.name)).bind(transTy(i.ty));
			NAME field = (NAME) env.tEnv.get(i.name);
			if (field.isLoop() == true) {
				env.errorMsg.error(i.pos, "Name Type definition in a loop!");
				return null;
			}
		}
		// 将类型放入类型符号表
		for (Absyn.TypeDec i = e; i != null; i = i.next)
			env.tEnv.put(i.name, transTy(i.ty));
		return trans.transNoOp();
	}

	private Translate.Exp transDec(Absyn.FunctionDec e) {
		// 翻译函数申明
		java.util.HashSet<Symbol> hs = new java.util.HashSet<Symbol>();
		ExpTy et = null;
		// 检查重复申明,分为普通函数与标准库函数
		for (Absyn.FunctionDec i = e; i != null; i = i.next) {
			if (hs.contains(i.name)) {
				env.errorMsg.error(e.pos, "Repeated function definiton in the same block!");
				return null;
			}
			if (env.stdFuncSet.contains(i.name)) {
				env.errorMsg.error(e.pos, "Same name in StdLib. Override StdFuncLib!");
				return null;
			}

			Absyn.RecordTy rt = new Absyn.RecordTy(i.pos, i.params);
			RECORD r = transTy(rt);
			if (r == null) {
				env.errorMsg.error(e.pos, "Record is empty!");
				return null;
			}
			// 后检查参数列表,与记录类型RecordTy的检查完全相同,得到 RECORD 类型的形参列表
			BoolList bl = null;
			for (FieldList f = i.params; f != null; f = f.tail) {
				bl = new BoolList(true, bl);
			}
			level = new Level(level, i.name, bl);
			env.vEnv.put(i.name, new FunEntry(level, new Temp.Label(i.name), r, transTy(i.result)));
			env.vEnv.beginScope();
			Translate.AccessList al = level.formals.tail;
			for (RECORD j = r; j != null; j = j.tail) {
				if (j.fieldName != null) {
					env.vEnv.put(j.fieldName, new VarEntry(j.fieldType, al.head));
					al = al.tail;
				}
			}
			et = transExp(i.body);

			// 翻译函数体
			if (et == null) {
				env.vEnv.endScope();
				env.errorMsg.error(e.pos, "Body is empty.");
				return null;
			}
			// 着检查函数返回值,如果没有返回值则设置成 void
			// 判断是否为void,若不为void则要将返回值存入$v0寄存器
			if (!(et.ty.actual() instanceof VOID))
				trans.procEntryExit(level, et.exp, true);
			else
				trans.procEntryExit(level, et.exp, false);

			env.vEnv.endScope();
			level = level.parent;
			// 回到原来的层
			hs.add(i.name);
		}
		return trans.transNoOp();
	}
}
