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
    	flowGraph = new AssemFlowGraph(instrs);//���ݻ��ָ��������ͼ
    	interGraph=new Liveness(flowGraph);//���Է���,����ͼ
    	color = new Color(interGraph, f, f.registers());//��ɫ������Ĵ���
    	}

    public String tempMap(Temp temp) {
        return color.tempMap(temp);
    }

  

}
