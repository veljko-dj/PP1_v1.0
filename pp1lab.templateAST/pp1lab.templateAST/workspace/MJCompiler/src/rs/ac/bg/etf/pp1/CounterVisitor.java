package rs.ac.bg.etf.pp1;

import rs.ac.bg.etf.pp1.CounterVisitor.FormParamCounter;
import rs.ac.bg.etf.pp1.CounterVisitor.VarCounter;
import rs.ac.bg.etf.pp1.ast.ConstDeclList_nadovezivanje;
import rs.ac.bg.etf.pp1.ast.ConstDeclList_single;
import rs.ac.bg.etf.pp1.ast.FormalParamDecl;
import rs.ac.bg.etf.pp1.ast.IfStatement;
import rs.ac.bg.etf.pp1.ast.IfStmt;
import rs.ac.bg.etf.pp1.ast.MethodTypeName;
import rs.ac.bg.etf.pp1.ast.NonIfStatement;
import rs.ac.bg.etf.pp1.ast.SyntaxNode;
import rs.ac.bg.etf.pp1.ast.VarDecListOneElement;
import rs.ac.bg.etf.pp1.ast.VarDecl;
import rs.ac.bg.etf.pp1.ast.VarDeclOne;
import rs.ac.bg.etf.pp1.ast.VarDeclOneNoSquare;
import rs.ac.bg.etf.pp1.ast.VarDeclOneSquare;
import rs.ac.bg.etf.pp1.ast.VarDeclarations;
import rs.ac.bg.etf.pp1.ast.VisitorAdaptor;
import rs.etf.pp1.mj.runtime.Code;

public class CounterVisitor extends VisitorAdaptor {

	protected int count;

	public int getCount() {
		return count;
	}

	public static class FormParamCounter extends CounterVisitor {

		public void visit(FormalParamDecl formParamDecl) {
			count++;
		}
	}

	public static class VarCounter extends CounterVisitor {
		// ove dve metode su naknadno dodate. Nisu nesto spec proveravane
		@Override
		public void visit(VarDeclOneNoSquare VarDeclOne) {
			count++;
		}

		@Override
		public void visit(VarDeclOneSquare VarDeclOne) {
			count++;
		}
		public void visit(VarDecl varDecl) {
			count++;
		}
	}

	/////////////////////
	public boolean zaustaviSe = false;

	public int globVar = 0;
	public int globVarArray = 0;
	public int lokVar = 0;
	public int lokVarArray = 0;
	public int globCon = 0;
	public int stmt = 0;

	public CounterVisitor() {
	}

	public CounterVisitor(boolean zaust) {
		zaustaviSe = zaust;
	}

	@Override
	public void visit(ConstDeclList_single ConstDeclList_single) {
		globCon++;
	}

	@Override
	public void visit(ConstDeclList_nadovezivanje ConstDeclList_nadovezivanje) {
		globCon++;
	}

	@Override
	public void visit(VarDeclOneNoSquare VarDeclOne) {
		globVar++;
	}

	@Override
	public void visit(VarDeclOneSquare VarDeclOne) {
		globVarArray++;
	}

	@Override
	public void visit(MethodTypeName methodTypeName) {
		if (!zaustaviSe) {
			SyntaxNode methodNode = methodTypeName.getParent();
			CounterVisitor lok = new CounterVisitor(true);
			methodNode.traverseTopDown(lok);

			this.lokVar = lok.globVar;
			this.lokVarArray = lok.globVarArray;
		}
	}

	@Override
	public void visit(NonIfStatement NonIfStatement) { 
		stmt++;
	}
	@Override
	public void visit(IfStmt IfStmt) { 
		stmt++;
	}

	public void printStatistics() {
		System.out.println("Globalnih konst ima :" + globCon);
		System.out.println("Globalnih prom ima :" + (globVar - lokVar));
		System.out.println("Globalnih prom tipa niz ima :" + (globVarArray - lokVarArray));
		System.out.println("Lokalnih prom ima :" + lokVar);
		System.out.println("Lokalnih prom tipa niz ima :" + lokVarArray);
		System.out.println("instrukcija ima :" + stmt);
	}
}
