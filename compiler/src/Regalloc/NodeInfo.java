package Regalloc;

import java.util.HashSet;
import java.util.Set;
import Temp.Temp;


public class NodeInfo {
	
	Set<Temp> in = new HashSet<Temp>(); //��ָ��ǰ�Ļ��Ա���
	Set<Temp> out = new HashSet<Temp>(); //��ָ���Ļ��Ա��� �� ����Ծ����
	Set<Temp> use = new HashSet<Temp>(); //ĳָ��ʹ�õı��� - ��ֵ���ұ�
	Set<Temp> def = new HashSet<Temp>(); //ĳָ���ı��� - ��ֵ�����
	
	public NodeInfo(){
	}
}
