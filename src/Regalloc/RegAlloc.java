package Regalloc;

import Assem.InstrList;
import Flowgraph.AssemFlowGraph;
import Frame.Frame;
import Temp.Temp;
import Temp.TempMap;

public class RegAlloc implements TempMap {

	public AssemFlowGraph flowGraph;
    public Liveness interGraph;
    private Color color;

    public RegAlloc(Frame f, InstrList instrs) {
    	flowGraph = new AssemFlowGraph(instrs);//根据汇编指令生成流图
    	interGraph=new Liveness(flowGraph);//活性分析,干扰图
    	color = new Color(interGraph, f, f.registers());//着色法分配寄存器
    	}

    public String tempMap(Temp temp) {
        return color.tempMap(temp);
    }

  

}
