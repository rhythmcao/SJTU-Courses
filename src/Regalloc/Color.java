package Regalloc;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;
import Graph.Node;
import Graph.NodeList;
import Temp.Temp;
import Temp.TempList;
import Temp.TempMap;

public class Color implements TempMap {
	// the stack
	LinkedList<Node> selectStack = new LinkedList<Node>();
	TempMap initcoloring;
	
	private void pushNode(Node n) {
		selectStack.addFirst(n);
	}

	private Node popNode() {
		return selectStack.removeFirst();
	}
	
	private boolean isInStack(Node n) {
		return selectStack.contains(n);
	}
	
	public Map<Temp, Temp> map= new HashMap<Temp, Temp>();
	
	public Color(InterferenceGraph interGraph, TempMap init, TempList regs) {
		initcoloring=init;
		int number = 0;
		//遍历每个临时变量结点
		for (NodeList node=interGraph.nodes(); node != null;node = node.tail) {
			++number;
			//得到结点所对应的临时变量
			Temp temp = interGraph.gtemp(node.head);
			//如果temp已经被分配了寄存器
			if (init.tempMap(temp) != null) {
				--number;
				pushNode(node.head); //改结点压入堆栈
				map.put(temp, temp); //放入分配列表map中，它们的寄存器就是本身
				//删除从该结点出发的所有边
				for (NodeList adj = node.head.succ(); adj != null; adj = adj.tail)
					interGraph.rmEdge(node.head, adj.head);
			}
		}
		//剩下number个还没有被分配寄存器的边
		for (int i = 0; i < number; ++i) {
			Node node = null;
			int max = -1;
			//再次遍历每个临时变量结点
			for (NodeList n = interGraph.nodes(); n != null; n = n.tail)
				if (init.tempMap(interGraph.gtemp(n.head)) == null
				&& !isInStack(n.head)) { //没有被分配寄存器且不在堆栈中
					int num = n.head.outDegree(); //出度
					if (max < num && num < regs.size()) {
						//找到一个度最大且小于寄存器数目的结点
						max = num;
						node = n.head;
					}
				}
			if (node == null) { //度大于寄存器数目，溢出
				System.err.println("Color.color() : register spills.");
				break;
			}
			//否则继续推入堆栈并移去从不在堆栈中的结点指向该结点的所有边
			pushNode(node);
			for (NodeList adj = node.pred(); adj != null; adj = adj.tail)
				if (!isInStack(adj.head))
					interGraph.rmEdge(adj.head, node);
		}
		//接下来开始分配number个没有被分配寄存器的临时变量，他们处于栈顶
		for (int i = 0; i < number; ++i) {
			Node node = popNode(); //弹出一个
			Set<Temp> available = new HashSet<Temp>();//可供分配寄存器列表
			TempList aregs=regs;
			while(aregs!=null){
				available.add(aregs.head);
				aregs=aregs.tail;
			}
			for (NodeList adj = node.succ(); adj != null; adj = adj.tail) {
				//从可供分配寄存器列表中移除该结点指向的某个结点所代表的寄存器
				available.remove(map.get(interGraph.gtemp(adj.head)));
			}
			//取剩下的一个作为寄存器
			Temp reg = (Temp) available.iterator().next();
			//加入寄存器分配表
			map.put(interGraph.gtemp(node), reg);
		}
	}

	public String tempMap(Temp t) {
		if (t == null) {
			throw new Error("Attempted to color map a null");
		}
		if (!map.containsKey(t)) {
			throw new Error("No color for the temp " + t);
		}
		return initcoloring.tempMap(map.get(t));
	}
}