package Regalloc;

import java.util.HashSet;
import java.util.Set;
import Temp.Temp;


public class NodeInfo {
	
	Set<Temp> in = new HashSet<Temp>(); //来指令前的活性变量
	Set<Temp> out = new HashSet<Temp>(); //出指令后的活性变量 － 即活跃变量
	Set<Temp> use = new HashSet<Temp>(); //某指令使用的变量 - 赋值号右边
	Set<Temp> def = new HashSet<Temp>(); //某指令定义的变量 - 赋值号左边
	
	public NodeInfo(){
	}
}
