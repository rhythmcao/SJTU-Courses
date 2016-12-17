package Regalloc;

import Graph.Graph;
import Graph.Node;
import Temp.Temp;


abstract public class InterferenceGraph extends Graph {
   abstract public Node tnode(Temp temp);
   abstract public Temp gtemp(Node node);
   abstract public MoveList moves();
   public int spillCost(Node node) {return 1;}
}
