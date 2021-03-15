package rs.ac.bg.etf.pp1;

import org.apache.log4j.Logger;
import rs.ac.bg.etf.pp1.ast.*;

public class RuleVisitor extends VisitorAdaptor {

	int printCallCount = 0;
	int varDeclCount = 0;

	Logger log = Logger.getLogger(getClass());

	public void visit(VarDeclOneSquare VarDeclOneSquare) {
		countt();
	}

	public void visit(VarDeclOneNoSquare VarDeclOneNoSquare) {
		countt();
	}

	public void visit(StatPrint print) {
		printCallCount++;
	}

	void countt() {
		varDeclCount++;
	}

}
