package Semant;

import Translate.Exp;
import Types.Type;

public class ExpTy {
	Exp exp; // ������ʽ
	Type ty; // ��������

	ExpTy(Exp exp, Type ty) {
		this.exp = exp;
		this.ty = ty;
	}
}