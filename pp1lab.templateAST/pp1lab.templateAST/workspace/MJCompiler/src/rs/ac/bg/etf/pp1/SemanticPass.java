package rs.ac.bg.etf.pp1;

import org.apache.log4j.Logger;

import rs.ac.bg.etf.pp1.ast.*;
import rs.etf.pp1.symboltable.*;
import rs.etf.pp1.symboltable.concepts.*;

public class SemanticPass extends VisitorAdaptor {

	boolean errorDetected = false;
	int printCallCount = 0;
	Obj currentMethod = null;
	/*
	 * kada ulancavamo lokalne simbole prosledjivacemo metodu u kojoj se trenutno
	 * nalazimo
	 */
	Obj currentClass = null;
	/* Ovo radim analogno sa metodom */
	Struct currentType = Tab.noType;
	/*
	 * Za sada nisam nasao bolji nacin da sacuvam koji je tip necega kada udjem kroz
	 * jer visit posecuje tek na izlazu iz posete
	 */
	String currentTypeName = "";
	/*
	 * Za sada cuvam samo naziv jer nisam siguran kako da dodjem do njega Najbolje
	 * bi bilo da se koristi samo za ispis
	 */
	boolean returnFound = false;
	int nVars = 0;
	int nConst = 0;
	// Brojanje konstanti
	boolean inLoop = false;
	// Ovo cemo koristiti za break i continue;
	Struct boolStruct = new Struct(Struct.Bool);

	Logger log = Logger.getLogger(getClass());

	public SemanticPass() {
		super();
//		Tab.insert(Obj.Type, "bool", boolStruct);
		Tab.insert(Obj.Type, "bool", boolStruct);
	}

	public void report_error(String message, SyntaxNode info) {
		// Ovo nije menjano
		errorDetected = true;
		StringBuilder msg = new StringBuilder(message);
		int line = (info == null) ? 0 : info.getLine();
		if (line != 0)
			msg.append(" na liniji ").append(line);
		log.error(msg.toString());
	}

	public void report_info(String message, SyntaxNode info) {
		// Ovo nije menjano
		StringBuilder msg = new StringBuilder(message);
		int line = (info == null) ? 0 : info.getLine();
		if (line != 0)
			msg.append(" na liniji ").append(line);
		log.info(msg.toString());
	}

	@Override
	public void visit(Program program) {
		report_info("Usao sam u Program", program);
		// Menjano
		nVars = Tab.currentScope.getnVars();
		Tab.chainLocalSymbols(program.getProgName().obj);
		Tab.closeScope();
	}

	@Override
	public void visit(ProgName progName) {
		report_info("Usao sam u ProgName", progName);
		// Menjano
		progName.obj = Tab.insert(Obj.Prog, progName.getPName(), Tab.noType);
		Tab.openScope();
	}

	/*
	 * // public void visit(VarDecl varDecl) { public void visit(VarDeclOneNoSquare
	 * VarDeclOneNoSquare) { report_info("Deklarisana promenljiva "+
	 * VarDeclOneNoSquare.getNameVarOne(), VarDeclOneNoSquare); Obj varNode =
	 * Tab.insert(Obj.Var, VarDeclOneNoSquare.getNameVarOne(), null); // Ovde fali }
	 * 
	 */

// Krecem redom po mjparser.cup, videcemo koliko ce to da traje
// Kako sam zakljucio, visit se posecuje na izlazu iz tog pojavljivanja

	// Tip //
	//////////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public void visit(Type type) {
		// report_info("Usao sam u Type, i to:"+ type.getTypeName(), type);
		// Menjano
		currentType = Tab.noType;
		// Proveri validnost ove linije gore
		Obj typeNode = Tab.find(type.getTypeName());
		if (typeNode == Tab.noObj) {
			report_error("Nije pronadjen tip " + type.getTypeName() + " u tabeli simbola! ", null);
			type.struct = Tab.noType;
		} else {
			if (Obj.Type == typeNode.getKind()) {
				type.struct = typeNode.getType();
				currentType = typeNode.getType();
			} else {
				report_error("Greska: Ime " + type.getTypeName() + " ne predstavlja tip!", type);
				type.struct = Tab.noType;
			}
		}
	}

	// Konstante //
	//////////////////////////////////////////////////////////////////////////////////////////////

	@Override
	public void visit(ConstDeclaration ConstDeclaration) {
		/*
		 * if (currentType==ConstDeclaration.getConstType().getType().struct)
		 * report_info("Isti su tipovi", ConstDeclaration); Lakse je da radis sa
		 * currentType nego sa ovim da se jebavas jer kada udjes dublje tesko ces
		 * izvaditi tip te konstante
		 */

		/*
		 * Ovde mogu da resetujem Type jer je to na izlazu, ali za sada nebitno
		 */
	}

	// Uporno pokusavam da spojim sve u jedan ali zbog parsera mora iz tri dela da
	// se to odradi
	@Override
	public void visit(ConstDeclOneElementNumber ConstDeclOneElementNumber) {
		// report_info("Usao sam u ConstDeclOneElementNumber",
		// ConstDeclOneElementNumber);
		//
		boolean missMatch = (Struct.Int != currentType.getKind());
		boolean alreadyExist = (Tab.find(ConstDeclOneElementNumber.getNumIdent()) != Tab.noObj);

		if (!missMatch) {
			if (!alreadyExist) {
				Obj numm = Tab.insert(Obj.Con, ConstDeclOneElementNumber.getNumIdent(), currentType);
				numm.setAdr(ConstDeclOneElementNumber.getVal());
				numm.setLevel(0);
				nConst++;
			} else {
				report_error("Greska: " + ConstDeclOneElementNumber.getLine() + ": "
						+ ConstDeclOneElementNumber.getNumIdent() + " VEC POSTOJI u tabeli simbola", null);
			}
		} else {
			report_error("Greska: " + ConstDeclOneElementNumber.getLine() + ": nije CHAR", null);
		}
	}

	@Override
	public void visit(ConstDeclOneElementBool ConstDeclOneElementBool) {
		// report_info("Usao sam u ConstDeclOneElementBool", ConstDeclOneElementBool);
		//
		boolean missMatch = (Struct.Char != currentType.getKind());
		boolean alreadyExist = (Tab.find(ConstDeclOneElementBool.getBoolIdent()) != Tab.noObj);
		int boolValue = -1;

		if (!missMatch) {
			if (!alreadyExist) {
				{
					if (ConstDeclOneElementBool.getVal().equals("true"))
						boolValue = 1;
					else if (ConstDeclOneElementBool.getVal().equals("false"))
						boolValue = 0;
				}
				if (boolValue == -1) {
					report_error("Greska: " + ConstDeclOneElementBool.getLine() + ": "
							+ ConstDeclOneElementBool.getBoolIdent() + " Nit je true nit false", null);
				} else {
					Obj booll = Tab.insert(Obj.Con, ConstDeclOneElementBool.getBoolIdent(), currentType);
					booll.setAdr(boolValue);
					booll.setLevel(0);
					nConst++;
				}
			} else {
				report_error("Greska: " + ConstDeclOneElementBool.getLine() + ": "
						+ ConstDeclOneElementBool.getBoolIdent() + " VEC POSTOJI u tabeli simbola", null);
			}
		} else {
			report_error("Greska: " + ConstDeclOneElementBool.getLine() + ": tip nije BOOL", null);
		}
	}

	@Override
	public void visit(ConstDeclOneElementChar ConstDeclOneElementChar) {
		// report_info("Usao sam u ConstDeclOneElementChar", ConstDeclOneElementChar);
		//
		boolean missMatch = (Struct.Char != currentType.getKind());
		boolean alreadyExist = (Tab.find(ConstDeclOneElementChar.getCharIdent()) != Tab.noObj);

		if (!missMatch) {
			if (!alreadyExist) {
				Obj charr = Tab.insert(Obj.Con, ConstDeclOneElementChar.getCharIdent(), currentType);
				charr.setAdr(ConstDeclOneElementChar.getVal());
				charr.setLevel(0);
				nConst++;
			} else {
				report_error("Greska: " + ConstDeclOneElementChar.getLine() + ": "
						+ ConstDeclOneElementChar.getCharIdent() + " VEC POSTOJI u tabeli simbola",
						ConstDeclOneElementChar);
			}
		} else {
			report_error("Greska: " + ConstDeclOneElementChar.getLine() + ": nije CHAR", ConstDeclOneElementChar);
		}
	}

	// Varijable //
	//////////////////////////////////////////////////////////////////////////////////////////////

	@Override
	public void visit(VarDeclaration VarDeclaration) {
		// currentType =
	}

	// nije niz
	@Override
	public void visit(VarDeclOneNoSquare VarDeclOneNoSquare) {
		// report_info("Usao sam u VarDeclOneNoSquare", VarDeclOneNoSquare);
		//
		boolean alreadyExistGlobal = (Tab.find(VarDeclOneNoSquare.getNameVarOne()) != Tab.noObj);
		boolean alreadyExistInThisScope = (Tab.currentScope.findSymbol(VarDeclOneNoSquare.getNameVarOne()) != null);
		// && (Tab.find(VarDeclOneNoSquare.getNameVarOne()).getLevel() == 0);

		/*
		 * { if (alreadyExistGlobal != alreadyExistInThisScope)
		 * report_info("alreadyExistGlobal != alreadyExistInThisScope",
		 * VarDeclOneNoSquare); else
		 * report_info("alreadyExistGlobal == alreadyExistInThisScope",
		 * VarDeclOneNoSquare); }
		 */

		if (alreadyExistGlobal && currentMethod == null)
			report_error("Greska: " + VarDeclOneNoSquare.getLine() + ": " + VarDeclOneNoSquare.getNameVarOne()
					+ " VEC je deklarisano u tabeli simbola. U ovom Scopeu ", null);
		else {

			Tab.insert(Obj.Var, VarDeclOneNoSquare.getNameVarOne(), currentType);
			/*
			 * Obj insertObj = Tab.insert(insideClass && currentMethod == null ? Obj.Fld :
			 * Obj.Var, ident, currentType); tmp.setLevel(level);
			 */

		}
	}

	// niz
	@Override
	public void visit(VarDeclOneSquare VarDeclOneSquare) {
		// report_info("Usao sam u VarDeclOneSquare", VarDeclOneSquare);
		//
		boolean alreadyExistGlobal = (Tab.find(VarDeclOneSquare.getNameVarOneArray()) != Tab.noObj);
		boolean alreadyExistInThisScope = (Tab.currentScope.findSymbol(VarDeclOneSquare.getNameVarOneArray()) != null);
		// && (Tab.find(VarDeclOneNoSquare.getNameVarOne()).getLevel() == 0);

		/*
		 * { if (alreadyExistGlobal != alreadyExistInThisScope)
		 * report_info("alreadyExistGlobal != alreadyExistInThisScope",
		 * VarDeclOneSquare); else
		 * report_info("alreadyExistGlobal == alreadyExistInThisScope",
		 * VarDeclOneSquare); }
		 */

		if (alreadyExistInThisScope && currentMethod == null)
			report_error("Greska: " + VarDeclOneSquare.getLine() + ": " + VarDeclOneSquare.getNameVarOneArray()
					+ " VEC je deklarisano u tabeli simbola. U ovom Scopeu ", null);
		else {

			Tab.insert(Obj.Var, VarDeclOneSquare.getNameVarOneArray(), new Struct(Struct.Array, currentType));
			/*
			 * Obj insertObj = Tab.insert(insideClass && currentMethod == null ? Obj.Fld :
			 * Obj.Var, ident, currentType); tmp.setLevel(level);
			 */

		}
	}

	// // Metoda //
	//////////////////////////////////////////////////////////////////////////////////////////////

	@Override
	public void visit(RetVoid1 RetVoid1) {
		RetVoid1.struct = Tab.noType;
	}

	@Override
	public void visit(RetType1 RetType1) {
		RetType1.struct = RetType1.getType().struct;
	}

	@Override
	public void visit(MethodTypeName MethodTypeName) {
		report_info("Usao sam u MethodTypeName, tj. pocetak metode", MethodTypeName);
		//
		boolean alreadyExists = Tab.find(MethodTypeName.getMethodName()) != Tab.noObj;
		if (alreadyExists) {
			report_error("Greska: " + MethodTypeName.getLine() + ": " + MethodTypeName.getMethodName()
					+ " Metoda vec postoji ili mozda prom sa istim imenom ", null);
		} else {
			// returnFound = (MethodTypeName.getRetType().struct != Tab.noType);
			// Edit: Proveri liniju iznad. Ovo iznad je kad nadjes return a ne da li treba
			// da postoji
			currentMethod = Tab.insert(Obj.Meth, MethodTypeName.getMethodName(), MethodTypeName.getRetType().struct);
			MethodTypeName.obj = currentMethod;
			Tab.openScope();
		}
		//
		report_info("Obradjuje se funkcija " + MethodTypeName.getMethodName(), MethodTypeName);
		report_info("Povratni tip funkcije je : " + currentMethod.getType().toString(), MethodTypeName);
	}

	@Override
	public void visit(MethodDeclaration MethodDeclaration) {
		if (!returnFound && currentMethod.getType() != Tab.noType) {
			report_error("Greska: " + MethodDeclaration.getLine() + ": " + currentMethod.getName()
					+ "  funkcija nema return a treba da ima", null);
		}

		Tab.chainLocalSymbols(currentMethod);
		Tab.closeScope();

		returnFound = false;
		currentMethod = null;
	}

	// Ovo nije gotovo
	@Override
	public void visit(StatReturn2 StatReturn2) {
		returnFound = true;
//		Struct currMethType = currentMethod.getType();
//		if (!currMethType.compatibleWith(returnExpr.getExpr().struct)) {
//			report_error("Greska na liniji " + returnExpr.getLine() + " : "
//					+ "tip izraza u return naredbi ne slaze se sa tipom povratne vrednosti funkcije "
//					+ currentMethod.getName(), null);
//		}
	}

	@Override
	public void visit(StatReturn StatReturn) {
		returnFound = true;
	}

	// Klasa //
	//////////////////////////////////////////////////////////////////////////////////////////////
	// Za sada kako mi deluje, to nije potrebno jos. To je za C
	// Param funkcije //
	//////////////////////////////////////////////////////////////////////////////////////////////
	// Za sada nemam to, jer mi deluje da ne treba za A. Cini mi se da je to za B

	// Statement //
	//////////////////////////////////////////////////////////////////////////////////////////////

	@Override
	public void visit(StatWhile StatWhile) {
		// Ovo ti je za B ili C
		inLoop = false;
	}

	@Override
	public void visit(DoWhile DoWhile) {
		// Ovo ti je za B ili C
		inLoop = true;
	}

	@Override
	public void visit(StatBreak StatBreak) {
		// Ovo ti je za B ili C
		if (!inLoop)
			report_error("Greska: " + StatBreak.getLine() + ": " + " Continue je pozvano van petlje ", null);
	}

	@Override
	public void visit(StatContinue StatContinue) {
		// Ovo ti je za B ili C
		if (!inLoop)
			report_error("Greska: " + StatContinue.getLine() + ": " + " Continue je pozvano van petlje ", null);
	}

//			Statement := DesignatorStatement ";".
//			DesignatorStatement := Designator "=" Expr.
//			DesignatorStatement := Designator "++".
//			DesignatorStatement := Designator "--".
//			Statement := DesignatorStatement ";".
//			Statement := "read" "(" Designator ")" ";".
//			Statement := "print" "(" Expr [“,” numConst] ")" ";".
//			Expr := ["‐"] Term {Addop Term} | CondFact "?" Expr ":" Expr.
//			Term := Factor {Mulop Factor}.
//			Factor := Designator | numConst | charConst | "(" Expr ")" | boolConst | "new" Type "[" Expr "]".
//			Designator := ident [ "[" Expr "]" ].
//			Addop := "+" | "‐" .
//			Mulop := "*" | "/" | "%".

	// Sad krecem od najmanjih elemenata, tek sam sad saznao za ovo

	@Override
	public void visit(MulopMod MulopMod) {
		// Nema semanticku analizu
	}

	@Override
	public void visit(MulopDiv MulopDiv) {
		// Nema semanticku analizu
	}

	@Override
	public void visit(MulopMul MulopMul) {
		// Nema semanticku analizu
	}

	// Designator //
	//////////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public void visit(DesignatorJustOne DesignatorJustOne) {
		Obj obj = Tab.find(DesignatorJustOne.getDestName()); 
		
		if (obj == Tab.noObj) {
			report_error("Greska: " + DesignatorJustOne.getDestName() + " nije deklarisano! ", DesignatorJustOne);
			DesignatorJustOne.obj = obj;
			// Trebalo bi da sve bude regularno jer je obj sada noObj, i to vrste Obj.Var a tipa Obj.noType
		} else {
			if (obj.getKind() == Obj.Meth) {
				if (currentMethod == null) {
					// Ovo mozda nije potrebno za A
					// Treba da se pozove funkija
					// Ali ovo nisam siguran da ce moci ovako da radi, moraces da proveris da li je
					// lepo pozvana,
					// da ne bude metoda1; bez ()
					report_info("Globalna metoda  " + DesignatorJustOne.getDestName() + "se koristi na liniji "
							+ DesignatorJustOne.getLine(), DesignatorJustOne);
					// Ovde ide visitor onaj
				}
			} else if (obj.getKind() == Obj.Con) {
				report_info(
						"Konstanta  " + DesignatorJustOne.getDestName() + " na liniji " + DesignatorJustOne.getLine(),
						DesignatorJustOne);
				// Ovde ide visitor onaj
			} else if (obj.getKind() == obj.Var) {
				report_info(
						"Varijabla  " + DesignatorJustOne.getDestName() + " na liniji " + DesignatorJustOne.getLine(),
						DesignatorJustOne);
				// Ovde ide visitor onaj
			}
			DesignatorJustOne.obj = obj;
		}
	}

	@Override
	public void visit(DesignatorOneDot DesignatorOneDot) {
		// Klase nisi implementirao
		// Ako sam lepo skontao za sada ovo nije potrebno za A
	}

	@Override
	public void visit(DesignatorOneArray DesignatorOneArray) {
		Struct exprStruct = DesignatorOneArray.getExpr().struct;
		Obj designNode = Tab.find(DesignatorOneArray.getDestName());

		if (designNode == Tab.noObj) {
			report_error("Ne postoji " + DesignatorOneArray.getDestName() + " u tabeli simbola! ", DesignatorOneArray);
		} else if (designNode.getKind() != Struct.Array) {
			report_error("Promenljiva " + DesignatorOneArray.getDestName() + " nije tipa niza! ", DesignatorOneArray);
		} else if (!exprStruct.assignableTo(Tab.intType)) {
			report_error(" Izraz nije tipa int unutar zagrada " + DesignatorOneArray.getDestName(), DesignatorOneArray);
		} else {
			report_info(
					"Niz  " + DesignatorOneArray.getDestName() + "se koristi na liniji " + DesignatorOneArray.getLine(),
					DesignatorOneArray);
		}

	}

//	Factor :=Designator | numConst | charConst | "(" Expr ")" | boolConst | "new" Type "[" Expr "]".

	// Factor //
	//////////////////////////////////////////////////////////////////////////////////////////////

	@Override
	public void visit(FactVar FactVar) {
		FactVar.struct = FactVar.getDesignator().obj.getType();
	}

	@Override
	public void visit(FactNum FactNum) {
		FactNum.struct = Tab.intType;
	}

	@Override
	public void visit(FactChar FactChar) {
		FactChar.struct = Tab.charType;
	}

	@Override
	public void visit(FactBool FactBool) {
		FactBool.struct = boolStruct;
	}

	@Override
	public void visit(FactExpr FactExpr) {
		FactExpr.struct = FactExpr.getExpr().struct;
	}

	@Override
	public void visit(FactNew FactNew) {
		FactNew.struct = FactNew.getType().struct;
		// Ja bih ovde dodao tip, mada imas problem, kad udje u Type on ce da ti baci
		// error
		// Moras negde pre toga da odradis
	}

	@Override
	public void visit(FactNewArray FactNewArray) {
		boolean validInt = (FactNewArray.getExpr().struct == Tab.intType);
		boolean validInt2 = (FactNewArray.getExpr().struct.Int == Struct.Int);
		if (validInt != validInt2)
			report_error("Greska: Ovo je stvarno glupo validInt != validInt2 ", FactNewArray);

		if (!validInt)
			report_error("Greska : Indeks niza mora biti tipa int ", FactNewArray);
		else
//			FactNewArray.struct = FactNewArray.getType().struct;
//			Ovo nije radilo gore
			FactNewArray.struct = new Struct(Struct.Array, FactNewArray.getType().struct);
		// Sve se plasim da ovo nece da radi lepo mada ja nemam klase i definisanje
		// novih stvari za A nivo
		// Sto mnogo olaksava
	}

//	Term := Factor {Mulop Factor}.

	// Term //
	//////////////////////////////////////////////////////////////////////////////////////////////

	@Override
	public void visit(TermOne TermOne) {
		TermOne.struct = TermOne.getFactor().struct;
	}

	@Override
	public void visit(TermMore TermMore) {
		// Pise da moraju biti tipa int
		Struct term1 = TermMore.getTerm().struct;
		Struct term2 = TermMore.getFactor().struct;
		TermMore.struct = Tab.noType; // Ovo stavljam za svaki slucaj
		// mada bi trebalo da je vec tako, mozda je null, ali i to inace ispitujem

		if (term1 == null || term1 == Tab.noType || term2 == null || term2 == Tab.noType) {
			report_error("Greska: Term si zajebao negde, dosao je prazan ", TermMore);
		} else {
			if (term1 != Tab.intType)
				report_error("Greska: Term nije tipa int ", TermMore);
			else if (term2 != Tab.intType)
				report_error("Greska: Factor nije tipa int ", TermMore);
			else {
				// oba su tipa int i sve je okej
				TermMore.struct = term1;
			}
		}
	}

//	Expr := ["‐"] Term {Addop Term} | CondFact "?" Expr ":" Expr. 
	// Expr //
	//////////////////////////////////////////////////////////////////////////////////////////////

	@Override
	public void visit(Expr1 Expr1) {
		// Ovo je prosledjivanje na manji Expr
		Expr1.struct = Expr1.getExprManjiProstiji().struct;
	}

	@Override
	public void visit(ExprConditionFalse ExprConditionFalse) {
		ExprConditionFalse.struct = ExprConditionFalse.getExprManjiProstiji().struct;
	}

	@Override
	public void visit(ExprConditionTrue ExprConditionTrue) {
		ExprConditionTrue.struct = ExprConditionTrue.getExprManjiProstiji().struct;
	}

	@Override
	public void visit(ExprCondition ExprCondition) {
		ExprCondition.struct = ExprCondition.getExprManjiProstiji().struct;
	}

	@Override
	public void visit(Expr0 Expr0) {
		// Komplikovanija verzija
		// Drugi i treći izraz moraju biti istog tipa.
		boolean sameType = (Expr0.getExprConditionFalse().struct.getKind() == Expr0.getExprConditionTrue().struct
				.getKind());

		if (!sameType)
			report_error("Greska: ExprTrue nije istog tipa kao ExprFalse ", Expr0);
		else {
			// sve je okej, sta sad?
		}

	}

	@Override
	public void visit(ExprTerm ExprTerm) {
		// Term mora biti tipa int.
		ExprTerm.struct = Tab.noType; // Za svaki slucaj
		boolean validTerm = (ExprTerm.getTerm().struct.getKind() == Tab.intType.Int);
		if (!validTerm)
			report_error("Greska: ExprTerm nije tipa int", ExprTerm);
		else {
			ExprTerm.struct = ExprTerm.getTerm().struct;
		}
	}

	@Override
	public void visit(ExprTermMinus ExprTerm) {
		// Term mora biti tipa int.
		ExprTerm.struct = Tab.noType; // Za svaki slucaj
		boolean validTerm = (ExprTerm.getTerm().struct.getKind() == Tab.intType.Int);
		if (!validTerm)
			report_error("Greska: ExprTerm nije tipa int", ExprTerm);
		else {
			ExprTerm.struct = ExprTerm.getTerm().struct;
		}
	}

	@Override
	public void visit(ExprTermList ExprTermList) {
		// Expr i Term moraju biti tipa int. U svakom slučaju, tipovi za Expr i Term
		// moraju biti komatibilni.
		boolean sameType = (ExprTermList.getExprManjiProstiji().struct.getKind() == ExprTermList.getTerm().struct
				.getKind());
		boolean validType = (ExprTermList.getTerm().struct.getKind() == Tab.intType.Int);
		// A sta je sa
		// nizovima????????????????????????????????????????????????????????????????

		if (!sameType) {
			report_error("Greska: ExprTermList nije istog tipa (a i mora biti int)", ExprTermList);
		} else if (!validType) {
			report_error("Greska: ExprTermList nije tipa int", ExprTermList);
		} else {
			// Sve okej.
			ExprTermList.struct = ExprTermList.getTerm().struct;
		}
	}

	// Statement / Read / Print //
	//////////////////////////////////////////////////////////////////////////////////////////////

//	Statement := "read" "(" Designator ")" ";".
//	Statement := "print" "(" Expr [“,” numConst] ")" ";".
	@Override
	public void visit(StatRead StatRead) {
		Obj desObj = StatRead.getDesignator().obj;
		boolean validKindOfDesignator = (desObj.getKind() == Obj.Var || desObj.getKind() == Obj.Fld
				|| desObj.getKind() == Obj.Elem);
		boolean validTypeOfDesignator = (desObj.equals(boolStruct) || desObj.equals(Tab.charType)
				|| desObj.equals(Tab.intType));

		if (desObj == null || desObj == Tab.noObj) {
			report_error("Greska: " + StatRead.getLine() + ": " + "Desginator je null ", StatRead);

			// U designatoru proveri da li postoji u tabeli simbola

			if (!validKindOfDesignator) {
				report_error("Greska: " + StatRead.getLine() + ": " + " Designator nije odgovarajuceg vrste,"
						+ " nije VAR FIELD ili ELEM ", StatRead);
			} else {

			}
			if (!validTypeOfDesignator) {
				report_error("Greska: " + StatRead.getLine() + ": " + " Designator nije odgovarajuceg tipa,"
						+ " nije CHAR || BOOL || INT ", StatRead);
			}
		}
		report_info("Usao si u ReadStm ", StatRead);
	}

	@Override
	public void visit(PrintValue PrintValue) {
//		Ovde za sada ne znam sta da radim
//		PrintValue.get
	}

	@Override
	public void visit(StatPrintValue StatPrintValue) {
//		Ovo sam pokupio od onog Pedje 
		Struct expression = StatPrintValue.getExpr().struct;
		if (expression == null || expression == Tab.nullType) {
			report_error("Greska: " + StatPrintValue.getLine() + ": " + " PrintValue je null ", null);
		} else if (!(expression.equals(Tab.intType) || expression.equals(Tab.charType)
				|| expression.equals(boolStruct)))
			report_error("Greska: " + StatPrintValue.getLine() + ": " + " Print nije ispisiv ", null);
		else {
			// ovde se nesto desava
			// i to ovde treba da se pokupe i vrednosti iz konstanti
			printCallCount++;
			// Ovde bi mozda trebalo da pitas da li je boolType pa ako jeste onda da
		}
	}

	@Override
	public void visit(StatPrint StatPrint) {
//	Ovo ne radi jer expr nema strukturu izgleda, valjalo bi da je izpropagiram do vrha
//	Ovo sam pokupio od onog Pedje 
		Struct expression = StatPrint.getExpr().struct;
		if (expression == null || expression == Tab.nullType) {
			report_error("Greska: " + StatPrint.getLine() + ": " + " Print je null ", null);
		} else if (!(expression.equals(Tab.intType) || expression.equals(Tab.charType)
				|| expression.equals(boolStruct)))
			report_error("Greska: " + StatPrint.getLine() + ": " + " Print nije ispisiv ", null);
		else {
			// ovde se nesto desava
			printCallCount++;
		}
	}

	// Statement / DesignatorStatement //
	//////////////////////////////////////////////////////////////////////////////////////////////
	// Statement := DesignatorStatement ";".
	@Override
	public void visit(StatDesign StatDesign) {
		// Ovde za sada nema nikakve potrebe da bilo sta prosledjujemo
		// Nema svrhu
	}

	// DesignatorStatement := Designator "=" Expr.
	// DesignatorStatement := Designator "++".
	// DesignatorStatement := Designator "--".

	@Override
	public void visit(DStatementAssign DStatementAssign) {
		// Designator mora označavati promenljivu, element niza ili polje unutar
		// objekta.
		// Tip neterminala Expr mora biti kompatibilan pri dodeli sa tipom neterminala
		// Designator
		boolean validKind = (DStatementAssign.getDesignator().obj.getKind() == Obj.Var
				|| DStatementAssign.getDesignator().obj.getKind() == Obj.Elem
				|| DStatementAssign.getDesignator().obj.getKind() == Obj.Fld);
		boolean validType = (DStatementAssign.getExpr().struct
				.assignableTo(DStatementAssign.getDesignator().obj.getType()));
		// ili obrnuto????????????????????????????????????????????????
		// Nije obrnuto, videh u Izvornom kodu

		if (!validKind)
			report_error("Greska: " + " Tip mora bit Var, Elem, Fld ", DStatementAssign);
		else if (!validType)
			report_error("Greska: " + " Tip za dodelu nije odgovaajuci, nisu kompetabilne leva i desna strana = ",
					DStatementAssign);
		else {
			// sve je okej
			report_info("Dodela je okej", DStatementAssign);
		}
	}

	@Override
	public void visit(DStatementInc DStatementInc) {
		// Designator mora označavati promenljivu, element niza ili polje objekta
		// unutrašnje klase.
		// Designator mora biti tipa int.
		boolean validKind = (DStatementInc.getDesignator().obj.getKind() == Obj.Var
				|| DStatementInc.getDesignator().obj.getKind() == Obj.Elem
				|| DStatementInc.getDesignator().obj.getKind() == Obj.Fld);
		// public static final int Con = 0, Var = 1, Type = 2, Meth = 3, Fld = 4,
		// Elem=5, Prog = 6;
		boolean validType = (DStatementInc.getDesignator().obj.getType().getKind() == Struct.Int);
		// Kad nisi siguran samo udjes u Struct i vidis sta radi koja metoda i cemu
		// sluzi Kind
		if (!validKind)
			report_error("Greska: " + " Increment mora da bude na varijabli, ementu niza ili polju klase ",
					DStatementInc);
		else if (!validType)
			report_error("Greska: " + " Increment mora da bude na tipu INT ", DStatementInc);
		else {
			// sve je okej
			report_info("Log_info: increment je okej", DStatementInc);
		}
	}

	@Override
	public void visit(DStatementDec DStatementDec) {
		// Designator mora označavati promenljivu, element niza ili polje objekta
		// unutrašnje klase.
		// Designator mora biti tipa int.
		boolean validKind = (DStatementDec.getDesignator().obj.getKind() == Obj.Var
				|| DStatementDec.getDesignator().obj.getKind() == Obj.Elem
				|| DStatementDec.getDesignator().obj.getKind() == Obj.Fld);
		// public static final int Con = 0, Var = 1, Type = 2, Meth = 3, Fld = 4,
		// Elem=5, Prog = 6;
		boolean validType = (DStatementDec.getDesignator().obj.getType().getKind() == Struct.Int);
		// Kad nisi siguran samo udjes u Struct i vidis sta radi koja metoda i cemu
		// sluzi Kind
		if (!validKind)
			report_error("Greska: " + " DeIncrement mora da bude na varijabli, ementu niza ili polju klase ",
					DStatementDec);
		else if (!validType)
			report_error("Greska: " + " DeIncrement mora da bude na tipu INT ", DStatementDec);
		else {
			// sve je okej
			report_info("Log_info: Decrement", DStatementDec);
		}
	}

	///////////////////////////////////////////////////////////////////////////////////////////////
	public boolean passed() {
		return !errorDetected;
	}

}
