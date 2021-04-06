package Flowgraph;

import java.util.Dictionary;
import java.util.Hashtable;

import Assem.Instr;
import Assem.InstrList;
import Assem.LABEL;
import Assem.Targets;
import Graph.Node;
import Graph.NodeList;
import Temp.Label;
import Temp.LabelList;
import Temp.TempList;

public class AssemFlowGraph extends FlowGraph {
	java.util.Map<Node, Instr> represent = new java.util.HashMap<Node, Instr>();

	//construct flow graph according to MIPS instruction
	public AssemFlowGraph(InstrList instrs) {
		Dictionary<Label, Node> labels = new Hashtable<Label, Node>(); // Label Table
		//add nodes and labels
		for (InstrList i = instrs; i != null; i = i.tail) {
			Node node = newNode();
			represent.put(node, i.head);
			if (i.head instanceof LABEL)
				labels.put(((LABEL) i.head).label, node);
		}
		//add edges
		for (NodeList node = nodes(); node != null; node = node.tail) {
			Targets next = instr(node.head).jumps(); //jump label table
			if (next == null) { //no jump, add an edge from current node to the next node
				if (node.tail != null)
					addEdge(node.head, node.tail.head);
			} else //add edge from current node to all the jump nodes
				for (LabelList l = next.labels; l != null; l = l.tail)
					addEdge(node.head, (Node) labels.get(l.head));
		}
	}

	//return MIPS instruction of node n
	public Instr instr(Node n) {
		return (Instr) represent.get(n);
	}

	//return variables that node defines
	public TempList def(Node node) {
		return instr(node).def();
	}

	//return variables that node uses
	public TempList use(Node node) {
		return instr(node).use();
	}

	//determine whether it is a move instruction
	public boolean isMove(Node node) {
		Instr instr = instr(node);
		return instr.assem.startsWith("move");
	}
}