package rs.ac.bg.etf.pp1;

import rs.etf.pp1.symboltable.concepts.Obj;
import rs.etf.pp1.symboltable.concepts.Struct;
import rs.etf.pp1.symboltable.visitors.DumpSymbolTableVisitor;

public class My_DumpSymbolTableVisitor_Impl extends DumpSymbolTableVisitor {

	@Override
	public void visitStructNode(Struct structToVisit) {
		// Eksperimentalno -> ctrl find je pronadjena ova metoda
		// Samo u ovoj metodi se spominju tipovi, pa shodno tome aj da dodam bool
		if (structToVisit.getKind() == Struct.Bool) {
			output.append("BOOL");
		} else if (structToVisit.getKind() == Struct.Array && structToVisit.getElemType().getKind() == Struct.Bool) {
			output.append("Arr of BOOL");
		} else
			///
			super.visitStructNode(structToVisit);
	}

	@Override
	public void visitObjNode(Obj objToVisit) {
		if (objToVisit.getName() != "MAX")
			super.visitObjNode(objToVisit);
	}
}
