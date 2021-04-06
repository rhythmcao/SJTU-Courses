package Regalloc;

import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Set;
import Graph.Graph;
import Graph.Node;
import Graph.NodeList;
import Temp.Temp;
import Temp.TempList;

public class Liveness extends InterferenceGraph {
	//flowgraph
	Flowgraph.FlowGraph flowGraph;
	MoveList movelist = null;
	
	Hashtable<Temp, TempNode> temp2node = new Hashtable<Temp, TempNode>();
	java.util.HashMap<Node, NodeInfo> nodeinfo =new java.util.HashMap<Node, NodeInfo>();
	java.util.HashMap<Node, TempList> liveMap =new java.util.HashMap<Node, TempList>();
	
	public Liveness(Flowgraph.FlowGraph flowGraph) {
    	this.flowGraph = flowGraph;
    	initNodeInfo(); //初始化
    	calculateLiveness(); //计算活性变量
    	buildGraph(); //生成干扰图
    	}
	
    public void initNodeInfo(){
		NodeList node=flowGraph.nodes();
		TempList use=null;
		TempList def=null;

		for(;node != null; node=node.tail) {
			NodeInfo info=new NodeInfo();
			nodeinfo.put(node.head, info );
			use=flowGraph.use(node.head);
			def=flowGraph.def(node.head);
			for(;use != null; use=use.tail) {
				info.use.add(use.head);
			}
			for(;def != null; def=def.tail) {
				info.def.add(def.head);
			}

		}
	}

	// 计算活性 live-in 和 live-out
	// 数据流等式:
	// in[n]=use[n] U (out[n]-def[n])
	// out[n]=U (in[s]) , for each s in succ[n]
	// 反复迭代,直到不变
    void calculateLiveness() {
    	boolean done = false;
    	do {
    	done = true;
    	for(NodeList node=flowGraph.nodes();node != null; node=node.tail) {
    	//遍历流图所有指令
    		NodeInfo inf = (NodeInfo) nodeinfo.get(node.head);//更新前的活性信息
    		//等式1
    		Set<Temp> in1 = new HashSet<Temp>(inf.out);
    		in1.removeAll(inf.def);
    		in1.addAll(inf.use);
    		if (!in1.equals(inf.in)) done = false; //测试是否完成
    		inf.in = in1; //更新in
    		//等式2
    		Set<Temp> out1 = new HashSet<Temp>();
    		for (NodeList succ = node.head.succ(); succ != null; succ =
    		succ.tail) {
    		NodeInfo i = (NodeInfo) nodeinfo.get(succ.head);
    		out1.addAll(i.in);
    		}
    		if (!out1.equals(inf.out)) done = false; //测试是否完成
    		inf.out = out1; //更新out
    		}
    		} while (!done);
    		//生成liveMap
    		for (NodeList node = flowGraph.nodes();node != null;node = node.tail) {
    		TempList list = null;
    		//得到活性信息中活跃变量的迭代器
    		Iterator<Temp> i = ((NodeInfo) nodeinfo.get(node.head)).out.iterator();
    		while (i.hasNext()) list = new TempList((Temp) i.next(), list);
    		if (list != null) liveMap.put(node.head, list);
    		}
    }

    //生成干扰图
    void buildGraph() {
    	Set<Temp> temps = new HashSet<Temp>(); //包含流图中所有有关的变量(使用的和定义的)
    	//生成temps
    	for (NodeList node = flowGraph.nodes(); node != null; node = node.tail)
    	{
    		for (TempList t = flowGraph.use(node.head); t != null; t = t.tail)
    			temps.add(t.head);
    		for (TempList t = flowGraph.def(node.head); t != null; t = t.tail)
    			temps.add(t.head);
    	}
    	//生成tnode
    	Iterator<Temp> i = temps.iterator();
    	while (i.hasNext()) 
    		newNode((Temp) i.next());
    	//生成干扰图
    	for (NodeList node = flowGraph.nodes(); node != null; node = node.tail)
    		//遍历流图中每一条指令
    		for (TempList t = flowGraph.def(node.head); t != null; t = t.tail)
    			//遍历指令中定义的所有变量
    			for (TempList t1 = (TempList) liveMap.get(node.head); t1 != null;
    					t1 = t1.tail)
    				//遍历指令的所有活跃变量
    				//应用以上规则 a,b,尝试添加边
    				if (t.head != t1.head //防止自回路
    				&& !(flowGraph.isMove(node.head)
    						&& flowGraph.use(node.head).head == t1.head)) {
    					addEdge(tnode(t.head), tnode(t1.head));
    					addEdge(tnode(t1.head), tnode(t.head)); //无向图双向加边
    				}
    }
    
    public Node newNode(Temp t) {
        TempNode n = new TempNode(this, t);
        temp2node.put(t, n);
        return n;
    }
    
    public Node tnode(Temp temp) {
        Node n = (Node) temp2node.get(temp);
        if (n == null) // make new nodes on demand
        {
            return newNode(temp);
        } else {
            return n;
        }
    }
    
	public MoveList moves() {
        return movelist;
}

	public Temp gtemp(Node node) {
		if (!(node instanceof TempNode)) {
			throw new Error("Node " + node.toString()
                	+ " not a member of graph.");
		} else {
			return ((TempNode) node).temp;
		}
	}
}
    
    // Sub-class to associate a temporary with a Node.
    class TempNode extends Node {

        Temp temp;

        TempNode(Graph g, Temp t) {
            super(g);
            temp = t;
        }

    public String toString() {
        return temp.toString(); // +"("+super.toString()+")";
        }

    }





