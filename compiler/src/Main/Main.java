package Main;

import Semant.Semant;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import ErrorMsg.ErrorMsg;
import Parse.Parse;

class Main {

	static Frame.Frame frame = new Mips.MipsFrame();

	static void prStmList(Tree.Print print, Tree.StmList stms) {
		for (Tree.StmList l = stms; l != null; l = l.tail)
			print.prStm(l.head);
	}

	static Assem.InstrList codegen(Frame.Frame f, Tree.StmList stms) {
		Assem.InstrList first = null, last = null;
		for (Tree.StmList s = stms; s != null; s = s.tail) {
			Assem.InstrList i = f.codegen(s.head);
			if (last == null) {
				first = last = i;
			} else {
				while (last.tail != null)
					last = last.tail;
				last = last.tail = i;
			}
		}
		return first;
	}

	static void emitProc(java.io.PrintStream out, Translate.ProcFrag f, String ir) throws FileNotFoundException {
		java.io.PrintStream IR = new java.io.PrintStream(
				new java.io.FileOutputStream(ir.substring(0, ir.length() - 3) + "ir"));
		// Temp.TempMap tempmap= new Temp.CombineMap(f.frame, new Temp.DefaultMap());
		//print IR tree
		Tree.Print print = new Tree.Print(IR);
		IR.println("# Before canonicalization: ");
		print.prStm(f.body);
		IR.print("\n# After canonicalization:\n ");
		Tree.StmList stms = Canon.Canon.linearize(f.body);
		prStmList(print, stms);
		IR.print("\n# Basic Blocks: \n");
		Canon.BasicBlocks b = new Canon.BasicBlocks(stms);
		for (Canon.StmListList l = b.blocks; l != null; l = l.tail) {
			IR.println("#");
			prStmList(print, l.head);
		}
		print.prStm(new Tree.LABEL(b.done));
		IR.println("\n# Trace Scheduled: \n");
		Tree.StmList traced = (new Canon.TraceSchedule(b)).stms;
		prStmList(print, traced);
		
		//print MIPS instruciton
		Assem.InstrList instrs = codegen(f.frame, traced);
		instrs = frame.procEntryExit2(instrs);
		Regalloc.RegAlloc regAlloc = new Regalloc.RegAlloc(f.frame, instrs);
		instrs = f.frame.procEntryExit3(instrs);
		Temp.TempMap tempmap = new Temp.CombineMap(f.frame,regAlloc);
		out.println(".text");
		for (Assem.InstrList p = instrs; p != null; p = p.tail)
			out.println(p.head.format(tempmap));
		IR.close();
	}

	public static void main(String args[]) throws java.io.IOException {
		System.out.println("Compile start ... ...");
//		String file = "testcase" + File.separator + "Bad" + File.separator + args[0];
//		if (args.length == 0)
//			throw new RuntimeException("no file");
//		if (!args[0].matches(".+\\.tig"))
//			throw new RuntimeException("Error filename extension");
		String file = "testcase" + File.separator + "Bad"+File.separator+"test10.tig";
		
	    Parse parse=new Parse(file);
	    System.out.println("\n********************************");
	    System.out.println("          Parse  Start!         ");
	    System.out.println("********************************"); 
		new Absyn.Print(System.out).prExp(parse.absyn, 0);
	    System.out.println("\n********************************");
	    System.out.println("        Parse  Finished!        ");
	    System.out.println("********************************");
		java.io.PrintStream abs = new java.io.PrintStream(
				new java.io.FileOutputStream(file.substring(0, file.length() - 3) + "abs"));
		new Absyn.Print(abs).prExp(parse.absyn, 0);
		abs.close();

		System.out.println("Translate start ... ...");
		java.io.PrintStream out = new java.io.PrintStream(
				new java.io.FileOutputStream(file.substring(0, file.length() - 3) + "s"));
		Frame.Frame frame = new Mips.MipsFrame();
		Translate.Translate translate = new Translate.Translate(frame);
		Semant semant = new Semant(translate, parse.errorMsg);
		Translate.Frag frags = semant.transProg(parse.absyn);
		out.println(".globl main");
		
		for (Translate.Frag f = frags; f != null; f = f.next)
			if (f instanceof Translate.ProcFrag)
				emitProc(out, (Translate.ProcFrag) f, file);
			else if (f instanceof Translate.DataFrag)
				out.println(".data\n" + ((Translate.DataFrag) f).data);
		
		
		//add StdFuncLib
		BufferedReader runtime = new BufferedReader(new FileReader("runtime.s"));
		while (runtime.ready())
			out.println(runtime.readLine());
		runtime.close(); //close reader
		if(!ErrorMsg.anyErrors){
			System.out.println("Translate finished!");
			System.out.println("Compile succeed!");	
			System.out.println(".abs, .ir and .s files have been written in \\testcase\\Good");
		}else{
			System.out.println("Translate failed!");
		}	
		out.close();//close printstream
	}
}

class NullOutputStream extends java.io.OutputStream {
	public void write(int b) {
	}
}
