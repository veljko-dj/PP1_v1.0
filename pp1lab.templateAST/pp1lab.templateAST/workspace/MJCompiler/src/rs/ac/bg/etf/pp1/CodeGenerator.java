package rs.ac.bg.etf.pp1;

import rs.ac.bg.etf.pp1.ast.StatPrintValue;
import rs.ac.bg.etf.pp1.ast.VisitorAdaptor;
import rs.etf.pp1.mj.runtime.Code;
import rs.etf.pp1.symboltable.Tab;
import rs.etf.pp1.symboltable.concepts.Obj;

public class CodeGenerator extends VisitorAdaptor {

	private int mainPc;

	public int getMainPc() {
		return mainPc;
	}
	
	@Override
	public void visit(StatPrintValue StatPrintValue) {
		if(StatPrintValue.getExpr().struct == Tab.intType){
			Code.loadConst(5);
			Code.put(Code.print);
		}else{
			Code.loadConst(1);
			Code.put(Code.bprint);
		}
	}
	
	public void visit(rs.ac.bg.etf.pp1.ast.ConstDeclOneElementNumber ConstDeclOneElementNumber){
		Obj con = Tab.insert(Obj.Con, "$", ConstDeclOneElementNumber.struct);
		con.setLevel(0);
		con.setAdr(ConstDeclOneElementNumber.getVal()); 
		
		Code.load(con);
	}
	
	
}
