package rs.ac.bg.etf.pp1;
 
import java.util.ArrayList;
import java.util.List;

import rs.ac.bg.etf.pp1.CounterVisitor.FormParamCounter;
import rs.ac.bg.etf.pp1.CounterVisitor.VarCounter;
import rs.ac.bg.etf.pp1.ast.AddopMinus;
import rs.ac.bg.etf.pp1.ast.AddopPlus;
import rs.ac.bg.etf.pp1.ast.CondFactOne;
import rs.ac.bg.etf.pp1.ast.CondFactRelop;
import rs.ac.bg.etf.pp1.ast.CondTermEnd;
import rs.ac.bg.etf.pp1.ast.CondTermMulti;
import rs.ac.bg.etf.pp1.ast.Condition;
import rs.ac.bg.etf.pp1.ast.ConditionFinal;
import rs.ac.bg.etf.pp1.ast.ConditionFinalAAA;
import rs.ac.bg.etf.pp1.ast.ConditionMulti;
import rs.ac.bg.etf.pp1.ast.ConditionOne;
import rs.ac.bg.etf.pp1.ast.DStatementAssign;
import rs.ac.bg.etf.pp1.ast.DStatementDec;
import rs.ac.bg.etf.pp1.ast.DStatementInc;
import rs.ac.bg.etf.pp1.ast.DStatementParen;
import rs.ac.bg.etf.pp1.ast.DesignatorJustOne; 
import rs.ac.bg.etf.pp1.ast.DesignatorOneArray;
import rs.ac.bg.etf.pp1.ast.DesignatorOneDot;
import rs.ac.bg.etf.pp1.ast.Expr0;
import rs.ac.bg.etf.pp1.ast.ExprCondition;
import rs.ac.bg.etf.pp1.ast.ExprConditionFalse;
import rs.ac.bg.etf.pp1.ast.ExprConditionTrue;
import rs.ac.bg.etf.pp1.ast.ExprTerm;
import rs.ac.bg.etf.pp1.ast.ExprTermList;
import rs.ac.bg.etf.pp1.ast.ExprTermMinus;
import rs.ac.bg.etf.pp1.ast.FactBool;
import rs.ac.bg.etf.pp1.ast.FactChar;
import rs.ac.bg.etf.pp1.ast.FactExpr;
import rs.ac.bg.etf.pp1.ast.FactNew;
import rs.ac.bg.etf.pp1.ast.FactNewArray;
import rs.ac.bg.etf.pp1.ast.FactNum;
import rs.ac.bg.etf.pp1.ast.FactVar;
import rs.ac.bg.etf.pp1.ast.FuncCall;
import rs.ac.bg.etf.pp1.ast.IfElseStatement;
import rs.ac.bg.etf.pp1.ast.IfStatement;
import rs.ac.bg.etf.pp1.ast.MethodDeclaration;
import rs.ac.bg.etf.pp1.ast.MethodTypeName;
import rs.ac.bg.etf.pp1.ast.MulopDiv;
import rs.ac.bg.etf.pp1.ast.MulopMod;
import rs.ac.bg.etf.pp1.ast.MulopMul;
import rs.ac.bg.etf.pp1.ast.RelopEqual;
import rs.ac.bg.etf.pp1.ast.RelopGreater;
import rs.ac.bg.etf.pp1.ast.RelopGreaterEqual;
import rs.ac.bg.etf.pp1.ast.RelopLower;
import rs.ac.bg.etf.pp1.ast.RelopLowerEqual;
import rs.ac.bg.etf.pp1.ast.RelopNotEqual;
import rs.ac.bg.etf.pp1.ast.StatPrint;
import rs.ac.bg.etf.pp1.ast.StatPrintValue;
import rs.ac.bg.etf.pp1.ast.StatRead;
import rs.ac.bg.etf.pp1.ast.StatReturn;
import rs.ac.bg.etf.pp1.ast.StatReturn2;
import rs.ac.bg.etf.pp1.ast.StatementFalse;
import rs.ac.bg.etf.pp1.ast.StatementTrue;
import rs.ac.bg.etf.pp1.ast.SyntaxNode;
import rs.ac.bg.etf.pp1.ast.TermMore;
import rs.ac.bg.etf.pp1.ast.VisitorAdaptor;
import rs.etf.pp1.mj.runtime.Code;
import rs.etf.pp1.symboltable.Tab;
import rs.etf.pp1.symboltable.concepts.Obj;
import rs.etf.pp1.symboltable.concepts.Struct;
import rs.etf.pp1.symboltable.visitors.DumpSymbolTableVisitor;

public class CodeGenerator extends VisitorAdaptor {
//	Snimak si odgledao, malo preleteo kraj.
//	Sad citam pdfove

//	U Code klasi put 0123 oznacava koliko bajtova ucitava
	private int mainPc;
	private Struct boolStruct; 

	private class CodeGenerator_PCAdresses_Expr {
		// Ovo postoji kao klasa za krpljene adresa kod skokova

		public int firstInstrTrue = 0;
		public int firstInstrFalse = 0;
		public int afterInst = 0; // sledeca posle ternarnog operatora
		public int tmpAdr = -3; // Privremena adresa koju cu stavljati svuda za
								// skokove pre nego sto namestim na korektnu
		public int whereToPutFirstTrue = 0;
		public int whereToPutFirstFalse = 0;
		public int whereToPutFirstAfter = 0;

		public boolean falseExist = false;

		public void clear() {
			firstInstrFalse = 0;
			firstInstrTrue = 0;
			afterInst = 0;
			whereToPutFirstTrue = 0;
			whereToPutFirstFalse = 0;
			whereToPutFirstAfter = 0;
			falseExist = false;
		}

		public void print() {
			System.out.println("true: " + firstInstrTrue);
			System.out.println("false: " + firstInstrFalse);
			System.out.println("next: " + afterInst);
			System.out.println("WhereTrue: " + whereToPutFirstTrue);
			System.out.println("WhereFalse: " + whereToPutFirstFalse);
			System.out.println("WhereNext: " + whereToPutFirstAfter);
			System.out.println("falseExist: " + falseExist);
		}

		/**
		 * @see Ovo ti je moj fixup koji je lepsi nego njihov,
		 * @see tacno imas gde da upises
		 */
		public void fixup(int whereToWrite, int adrToWrite) {
			Code.put2(whereToWrite, adrToWrite - whereToWrite + 1);
		}
	}

	private CodeGenerator_PCAdresses_Expr adrExpr = new CodeGenerator_PCAdresses_Expr();
	private List<CodeGenerator_PCAdresses_Expr> listAdrIfElse = new ArrayList<>();

	public int getMainPc() {
		return mainPc;
	}

	public CodeGenerator() {
		Obj obj = Tab.find("bool");
		boolean foundType = obj != null || obj != Tab.noObj;
		boolean isType = obj.getKind() == Obj.Type;
//		ovo != null mzoes svuda da sklonis jer kad pogledas kod nikada nece vratiti null ta njihova metoda
		if (!foundType)
			System.out.println("Ne postoji BOOL struktura u sistemu");
		else if (!isType)
			System.out.println("Nisi lepo ubacio BOOL u tabelu jer nije vrste Type");
		else
			boolStruct = obj.getType();
	}

	private void generisanjeORDCHR() {
//		Ne treba za A 
		// NE POSTOJI NACIN DA POZOVES OVE METODE PO GRAMATICI PODELJENOJ ZA A
	}

	@Override
	public void visit(MethodTypeName methodTypeName) {
		if ("main".equalsIgnoreCase(methodTypeName.getMethodName())) {
			mainPc = Code.pc;
		}
		methodTypeName.obj.setAdr(Code.pc);
		SyntaxNode methodNode = methodTypeName.getParent();

		VarCounter varCnt = new VarCounter();
		methodNode.traverseTopDown(varCnt);

		FormParamCounter fpCnt = new FormParamCounter();
		methodNode.traverseTopDown(fpCnt);

		// Generate the entry
		Code.put(Code.enter);
		Code.put(fpCnt.getCount()); // Ovo ce mislim biti isto kao Code.put(0)
		Code.put(fpCnt.getCount() + varCnt.getCount()); // Ovo ce mislim biti isto kao Code.put(1)
														// Broj formalnih parametara sa lokalnim parametrima

		generisanjeORDCHR();
	}

// Izlazak iz MAINa, nisam menjao
	@Override
	public void visit(MethodDeclaration methodDecl) {
		Code.put(Code.exit);
		Code.put(Code.return_);
	}

	public void visit(StatReturn returnExpr) {
		Code.put(Code.exit);
		Code.put(Code.return_);
	}

	// izvorni
	public void visit(StatReturn2 returnNoExpr) {
		Code.put(Code.exit);
		Code.put(Code.return_);
	}

//	Prvo hocu da odradim konstante da znam sta se tu desava
	@Override
	public void visit(FactNum FactNum) {
		Obj con = Tab.insert(Obj.Con, "$", FactNum.struct);
		con.setLevel(0);
		con.setAdr(FactNum.getN1());

		Code.load(con);
	}

	@Override
	public void visit(FactChar FactChar) {
		// Ovo je izvorni kod sa snimka
		Obj con = Tab.insert(Obj.Con, "$", FactChar.struct);
		con.setLevel(0);
		con.setAdr(FactChar.getC1());

		Code.load(con);
	}

//	Resi bool sad
	@Override
	public void visit(FactBool FactBool) {
		// Ovo je izvorni kod sa snimka
		Obj con = Tab.insert(Obj.Con, "$", FactBool.struct);
		con.setLevel(0);
//		System.out.println("sta ce ovo stampati :" + FactBool.getB1());
//		System.out.println("sta ce ovo stampati :" + (FactBool.getB1().equals("true") ? 1 : 0));
//		System.out.println("sta ce ovo stampati :" + (FactBool.getB1().length()) + "-" + "true".length());
		con.setAdr(FactBool.getB1().equals("true") ? 1 : 0);
		// a sta ako nije ni false? Zar to vec nije reseno u semantickoj? Pa jeste

		Code.load(con);
	}

	public void visit(FactVar FactVar) {
//		Ovo ti ne treba toliko jer ti u Designator ubacis to u kod sto si kucao
//		a ovo mu dodje samo kao neko prosledjivanje
	}

	public void visit(FuncCall FuncCall) {
//		Ovde ti vazi isto kao i dole za DStatementParen
//		tj. ne radis poziv funkcije za A. Ali da radis, bilo bi vrv isto kao to dole
	}

	public void visit(FactNew FactNew) {
//		Pa konju, ne radis dinamicku alokaciju za proste tipove, 
//		ovo ti treba samo za klase, a ti to ne radis
//		Code.put(Code.new_);
//		 Pise ti u pdfu NEW S, s ti oznacava SHORT, zato 2
//		FactNew.struct.get
//		Code.put2(sta);
	}

	public void visit(FactNewArray FactNewArray) {
//		velicina ti je vec na steku [n], to si gurnuo kada si ima nesto tipa:
//		Expr ili NUM_CONST ili tako nesto, svakako bi trebalo da je vec na steku kada
//		si prolazio kroz to
		boolean isChar = (FactNewArray.struct.getElemType() == Tab.charType);
		// System.out.println("PROVERI: vrednost isChar je : " + isChar);

		int b = isChar ? 1 : 0;
//		Ne proveravas bool jer ga isto smestas kao int

		Code.put(Code.newarray);
		Code.put(b);
	}

	public void visit(FactExpr FactExpr) {
//		Za sada mislim da ovo ne treba jer ces verovatno u Expr odraditi
//		sta je potrebno
	}

//	Krecem sad printStmt jer je u izvornom kodu prvo to radio pa da sklonim taj kod

	public void visit(StatPrint StatPrint) {
		if (StatPrint.getExpr().struct == Tab.intType) { 
			Code.loadConst(4); // Ovo je width(), odnosno sirina jednog INTa
			Code.put(Code.print); // Ovo je ispis INTa
		} else if (StatPrint.getExpr().struct == Tab.charType) {

			Code.loadConst(1);
			Code.put(Code.bprint);
		} else if (StatPrint.getExpr().struct == boolStruct) {
//			Code.pc = Code.pc - 1; // skontah da moze i Code.pop
//			int numLetters;
//			String[] boolString = { "false", "truea" };
//			int intValue = Code.get(Code.pc - 1);
//			int intValuePre = Code.get(Code.pc-1);
//			int intValuePosle = Code.get(Code.pc+1);
//			if (intValue == Code.const_1)
//				numLetters = 4;
//			else
//				numLetters = 5;
//
//			int it = 0;
//			while (it < numLetters) {
//				Code.loadConst(boolString[intValue - 15].charAt(it));
//				Code.loadConst(1);
//				Code.put(Code.bprint);
//				it++;
//			}
			Code.loadConst(4);
			Code.put(Code.print);
		} else
			System.out.println("Neka greska se desila");
	}

	@Override
	public void visit(StatPrintValue StatPrintValue) {
		// Kako uopste izgleda ovaj smesni print?
		if (StatPrintValue.getExpr().struct == Tab.intType) {
//			Zasto je int 5? Stavio sam ipak 4 jer mi to ima smisla
			Code.loadConst(4); // Ovo je width(), odnosno sirina jednog INTa
			Code.put(Code.print); // Ovo je ispis INTa
		} else if (StatPrintValue.getExpr().struct == Tab.charType) {
//			Code.pc = Code.pc - 1; // skontah da moze i Code.pop  
//			int numLetters;
//			String[] boolString = { "false", "truea" };
//			int intValue = Code.get(Code.pc);
//			int intValuePre = Code.get(Code.pc-1);
//			int intValuePosle = Code.get(Code.pc+1);
//			if (intValue == Code.const_1)
//				numLetters = 4;
//			else
//				numLetters = 5;
//
//			int it = 0;
//			while (it < numLetters) {
//				Code.loadConst(boolString[intValue - 15].charAt(it));
//				Code.loadConst(1);
//				Code.put(Code.bprint);
//				it++;
//			}
			Code.loadConst(4);
			Code.put(Code.print);
		} else
			System.out.println("Neka greska se desila");

// 		Dodatak za ispisivanje konstante
		Code.loadConst(StatPrintValue.getValue());
		Code.loadConst(4);
		Code.put(Code.print);

	}

//	A sada StatRead, kad sam vec odradio print a to deluje lako

	@Override
	public void visit(StatRead StatRead) {
		Struct typeOfDes = StatRead.getDesignator().obj.getType();
//		Ako je element niza onda on to sve sredi u store?
		System.out.println("\n\tKORISTI SE READ !\n");
		if (StatRead.getDesignator().obj.getKind() == 5) {
//			 Ovo sve ovde je postojalo dok nisam skontao da to mogu isto da odradim
//			samo u designatorOne i to je to , aovde samo dodam read komandu
//			{
//				Obj obj = new Obj(Obj.Var, "nebitno", Tab.intType);
//				obj.setLevel(StatRead.getDesignator().obj.getLevel());
//				obj.setAdr(StatRead.getDesignator().obj.getAdr());
//				Code.load(obj);
//			}
//			Code.put(Code.dup_x1);
//			Code.put(Code.pop);  

		}

		if (typeOfDes == Tab.intType) {
			Code.put(Code.read);
			Code.store(StatRead.getDesignator().obj);
		} else if (typeOfDes == Tab.charType) {
			Code.put(Code.bread);
			Code.store(StatRead.getDesignator().obj);
		} else if (typeOfDes == boolStruct) {
			Code.put(Code.read);
			// da li read ili bread? Mislim da je read
			Code.store(StatRead.getDesignator().obj);
		} else
			System.out.println("Ovde je bila neka greska, nije ni char ni int ni bool");
	}

//	Da bih nastavio bilo sta dalje mislim da je najbolje Designator da resim
//	i zbog ovoga gore (ReadStat)
//	a i prva stvar iz izvornog je assignment

	public void visit(DStatementAssign DStatementAssign) {
		if (DStatementAssign.getDesignator().obj.getKind() == 5) {
//			{ 
//				Ovo sve ovde je zakomentarisano dok si u DesignatorOneArray imao onaj 
//				if gde kazes, ako je assign odradi mi sve ovo sto ovde radim,
//				pa covece nema potrebe da to 2 puta radis

//				Obj obj = new Obj(Obj.Var, "nebitno", Tab.intType);
//				obj.setLevel(DStatementAssign.getDesignator().obj.getLevel());
//				obj.setAdr(DStatementAssign.getDesignator().obj.getAdr());
//				Code.load(obj);
//			}
			// Code.put(Code.dup_x2);
			// Code.put(Code.pop);
		}

		Code.store(DStatementAssign.getDesignator().obj);
//		 on u pozadini u mjruntime odradi store zavisno sta stavljas

//		Obj a = DStatementAssign.getDesignator().obj; 
	}

//	Sad je jedino logicno sto mogu da radim designator da bi mi radio DstatemntAssign

	public void visit(DesignatorJustOne DesignatorJustOne) {
		SyntaxNode parent = DesignatorJustOne.getParent();
//		Ovo je iz izvornog koda i kazem ako nije dodela(poziv met ili read poziv) onda samo pokupi vrednost 
//		te prom i stavi na stek a ako jeste onda je druga prica
		if (DStatementAssign.class != parent.getClass() && DStatementParen.class != parent.getClass()
				&& StatRead.class != parent.getClass()) {
			Code.load(DesignatorJustOne.obj);
		}
	}

	public void visit(DesignatorOneDot DesignatorOneDot) {
//		Ovo je za klase, ovo nikad nece da se desi ako radis za A
	}

	private void checkIfAllocatedArray() {
		// Na steku je je 10(indeks) 2(niz)
		Code.put(Code.dup);
		// Sada je 10 2 2
		Code.loadConst(0);
		// Sada je 10 2 2 0
		Code.putFalseJump(0, 0); // 5 je kod not eq
		// znaci ako je razlicito od nule znaci postoji neka adresa
		// i onda ne bacaj gresku
		Code.put(Code.trap);
		Code.put(101); // Ovo je valjda kod greske koji ne razumem
		Code.fixup(Code.pc - 4);// Eksperimentalno pomocu disasm sam odredio kolika je razlika
	}

	private void checkElemIndex() {
		// Ovo ovde ce da sluzi da proveris da li je indeks dobar.
		// Ako nije onda greska nekako
		// Na steku je 10(indeks) 2(niz)
		Code.put(Code.dup2);
		// Sada je 10 2 10 2
		Code.put(Code.arraylength);
		// Sada je 10 2 10 3(duzina)
		Code.putFalseJump(5, 0); // 5 je kod za lower
		Code.put(Code.trap);
		Code.put(102); // Ovo je valjda kod greske koji ne razumem
		Code.fixup(Code.pc - 4);// Eksperimentalno pomocu disasm sam odredio kolika je razlika

	}

	public void visit(DesignatorOneArray DesignatorOneArray) {
		SyntaxNode parent = DesignatorOneArray.getParent(); 
		// Ovo je dodato jer kod inc nemas levu stranu kao sto je niz[3]= ...
		// pa zato moras da odradis dup da sacuvas indeks 3, da ga dupliras i posle na
		// cudan nacin iskoristis
		if (DStatementInc.class == parent.getClass() || DStatementDec.class == parent.getClass())
			Code.put(Code.dup);
		{ 
//			Ovde ce obezbediti da se adresa niza pojavi lepo
			Obj obj = new Obj(Obj.Var, "nebitno", Tab.intType);
			obj.setLevel(DesignatorOneArray.obj.getLevel());
			obj.setAdr(DesignatorOneArray.obj.getAdr());
			Code.load(obj);
		}  
		checkIfAllocatedArray();
		checkElemIndex();
		
		Code.put(Code.dup_x1);	// zamena mesta indeksu i adresi niza 
		Code.put(Code.pop);  
		
		// Ako ovo nije designator iz assign ili read onda ucitaj koja je to vrednost
		// ako jeste onda kad dodje do ovih klasa ono ce ucitati nekaok	
		if (DStatementAssign.class != parent.getClass() && StatRead.class != parent.getClass())
			Code.put((DesignatorOneArray.obj.getType() == Tab.charType) ? Code.baload : Code.aload);
//			I posle ovoga na steku ce se naci vrednost iz niza

//		}
	}

	public void visit(DStatementParen DStatementParen) {
		// ovo je poziv funkcije i to ti ne treba za A? Ali aj napisacu, ne deluje
		// toliko strasno. Vidimo da treba gurnuti CALL pa adresu (ili razliku do
		// adrese)?
		// Nesto tipa Code.put(Code.call);
		// Code.put(dStatParen.getDesign.obj.getAdr()-Code.pc)?
		// i to pazi, druga stvar je mozda put2(...) jer imamo Code.buf od 8000
		// pozicija, tako
		// da moze da se ti jedan bajt nije dovoljan
	}

	public void visit(TermMore TermMore) {
//		Za termOne je obicno prosledjivanje i nema potrebe za generisanjem koda
//		Znaci on je vec bio u TermMore ili u Factor i stavio je taj na stek
//		i bio je i u drugom delu TermMore i to je stavio na stek i sada
//		ti na izlazu iz TermMore dodajes operaciju koja treba da se desi
		boolean isMull = (TermMore.getMulop().getClass() == MulopMul.class);
		boolean isDiv = (TermMore.getMulop().getClass() == MulopDiv.class);
		boolean isMod = (TermMore.getMulop().getClass() == MulopMod.class);
		if (isMull)
			Code.put(Code.mul);
		else if (isDiv)
			Code.put(Code.div);
		else if (isMod) {
			// System.out.println("Ne postoji mod, sta sad? ");
			// Ako ti nije jasno sta si radio, ti onda skoci na
			// Implementing the modulo operator as a function in C
			// na StackOverflow i vidi formulu
			Code.put(Code.dup2);
			Code.put(Code.div);
			Code.put(Code.mul);
			Code.put(Code.sub);
		}
	}

	public void visit(ExprTermList ExprTermList) {
//		Kao i sa termMore gore, trebalo bi da ti se vec
//		obe vrednosti nalaze na steku, sada ti samo da pozoves potrebnu operaciju 
//		i to bi trebalo da bude to
		boolean isPlus = (ExprTermList.getAddop().getClass() == AddopPlus.class);
		boolean isMinus = (ExprTermList.getAddop().getClass() == AddopMinus.class);
		if (isPlus)
			Code.put(Code.add);
		else if (isMinus)
			Code.put(Code.sub);
		else
			System.out.println("Greska neka");
	}

	public void visit(ExprTermMinus ExprTermMinus) {
//		Kada udjes u pdf vidis da za ovo ne treba nista specijalno
//		Vec se nalazi na steku vrednost, samo treba da je negiras
		Code.put(Code.neg);
	}

	public void visit(ExprTerm ExprTerm) {
		// Ovde nista ne radis, samo se u semantici odradi prosledjivanje na gore
	}

//////// Dodao ovaj deoooooooooooooo	
	public void visit(ConditionFinalAAA ConditionFinalAAA) {
		// Ovde nista ne radis, samo se u semantici odradi prosledjivanje na gore

//		E konju jedan. jmp if not equal radi sa dve vrednosti na steku, moras da stavis pored expr 
//		i drugu vrednost, tj. nulu, zato ti baca gresku 1794
		Code.loadConst(0);

//		Sada ovde kazes, ako nije true onda skoci na FALSE a tu adresu jos ne znas

		CodeGenerator_PCAdresses_Expr e = new CodeGenerator_PCAdresses_Expr();

		e.whereToPutFirstFalse = Code.pc + 1; // Jer prvo ide JEQ pa onda adresa
		Code.putFalseJump(Code.ne, e.tmpAdr);
//		E mislim da im ovo lepo  ne radi, logicno mi da stavis, if equal 0 skoci
//		tj. if false skoci ali ako stavis Code.eq onda ne radi lepo, pa zato stavljam
//		Code.ne 
		e.firstInstrTrue = Code.pc;

		listAdrIfElse.add(e);
	}

	public void visit(ConditionMulti ConditionMulti) {
		Code.put(Code.add);
		// PROVERI OVO OR je + a AND je *
	}

	@Override
	public void visit(ConditionOne ConditionOne) {

	}

	public void visit(CondTermEnd CondTermEnd) {
		// Ovde nista ne radis, samo se u semantici odradi prosledjivanje na gore
	}

	public void visit(CondTermMulti CondTermMulti) {
		Code.put(Code.mul);
		// PROVERI OVO OR je + a AND je *
	}

	@Override
	public void visit(StatementTrue StatementTrue) {
//		CodeGenerator_PCAdresses_Expr e= listAdrIfElse.get(listAdrIfElse.size()-1);
//		e.afterInst		= Code.pc;
//		e.firstInstrFalse= Code.pc;

		CodeGenerator_PCAdresses_Expr e = listAdrIfElse.get(listAdrIfElse.size() - 1);

		e.whereToPutFirstAfter = Code.pc + 1;
		Code.putJump(e.tmpAdr); // skok na sledecu instrukciju
		e.firstInstrFalse = Code.pc;

	}

	@Override
	public void visit(StatementFalse StatementFalse) {
//		Izlaz iz drugogIzraza koji bi trebalo da je na steku
//		System.out.println("ExprFalse" + Code.pc);
//		Posle ovoga Pc ima vrednost prve instrukcije posle ternarnog operatora, tj. 
//		to obicno biva STore ako je bila jednakost ili tako nesto
//		Svakako je to mesto gde skaces iz TRUEexpr

//		Ovde nemas gde da skaces, nastavljas dalje.
		CodeGenerator_PCAdresses_Expr e = listAdrIfElse.get(listAdrIfElse.size() - 1);
		e.afterInst = Code.pc;

		e.falseExist = true;

	}

	@Override
	public void visit(IfStatement IfStatement) {
//		Izlazak iz ternarnog, na steku bi trebalo da se nalazi vrednost za dodelu
//		System.out.println("Expro0" + Code.pc);
		CodeGenerator_PCAdresses_Expr e = listAdrIfElse.get(listAdrIfElse.size() - 1);
		listAdrIfElse.remove(e);

//		adrExpr.print();
		e.fixup(e.whereToPutFirstAfter, e.firstInstrFalse);
		e.fixup(e.whereToPutFirstFalse, e.firstInstrFalse);
		e.clear();
	}

	@Override
	public void visit(IfElseStatement IfElseStatement) {
//		Izlazak iz ternarnog, na steku bi trebalo da se nalazi vrednost za dodelu
//		System.out.println("Expro0" + Code.pc);
		CodeGenerator_PCAdresses_Expr e = listAdrIfElse.get(listAdrIfElse.size() - 1);
		listAdrIfElse.remove(e);
//		adrExpr.print();
		e.fixup(e.whereToPutFirstAfter, e.afterInst);
		e.fixup(e.whereToPutFirstFalse, e.firstInstrFalse);
		e.clear();
	}

//	Vracam se da odradim ono sto nisam odradio malo pre, a to je 
//	DStatementInc i DStatementDec

	public void visit(DStatementInc DStatementInc) {
//		Ovde je bilo bas problema
//		Prvo, ako je globalna stvar onda ne mozes da odradis 
//		Code.inc jer Code.inc radi samo sa lokalnim stvarima, 
//		Znaci to si morao da detektujes, jebi ga, hteo si lepo da odradis na pocetku
//		Ali svakako i ako je niz imas problem sto niz nisi posecivao dva puta, jednom 
//		za load jednom za store kao sto je slucaj kod niz[3]=niz[3]+1;
//		Zato si u designOneSquare morao da ide Code.dup da bi duplirao indeks 3 
//		Pa to onda ovde da iskoristis za formiranje steka za STORE i to ej to

		if (DStatementInc.getDesignator().obj.getLevel() == 1 && DStatementInc.getDesignator().obj.getKind() != 5) {
			Code.put(Code.pop);
			// Ovaj pop ide jer je vrednost ispod inc vec stavljena, pa ga sklonim da bi to
			// inc postavio
			// ovo gore je debug pokazao
			Code.put(Code.inc);
			Code.put(DStatementInc.getDesignator().obj.getAdr());
			Code.put(1);// increment
		} else {

			Code.loadConst(1);
			Code.put(Code.add);
			if (DStatementInc.getDesignator().obj.getKind() == 5) {
				// Ovo postoji ovde ovako jer sam u DesignOneSquare ili kako vec
				// rekao ako je njegov roditelj DstatInc ili Dec onda odradi DUP
				// pa ces u ovom momentu na steku imati:
				// indeks u nizu : vrednost koja treba da se postavi
				// npr niz[7]++; (niz[7] je bio 45 npr)
				// na steku je 7 46
				{ // Pravim privremeno da ucita lepo to, ovo je sve kopirano gore iz
					// assign gde upisujem vrednosti
					Obj obj = new Obj(Obj.Var, "nebitno", Tab.intType);
					obj.setLevel(DStatementInc.getDesignator().obj.getLevel());
					obj.setAdr(DStatementInc.getDesignator().obj.getAdr());
					Code.load(obj);
				}
				Code.put(Code.dup_x2);
				Code.put(Code.pop);
			}
			Code.store(DStatementInc.getDesignator().obj);
		}
	}

	public void visit(DStatementDec DStatementDec) {
//		Ovde je bilo bas problema
//		Prvo, ako je globalna stvar onda ne mozes da odradis 
//		Code.inc jer Code.inc radi samo sa lokalnim stvarima, 
//		Znaci to si morao da detektujes, jebi ga, hteo si lepo da odradis na pocetku
//		Ali svakako i ako je niz imas problem sto niz nisi posecivao dva puta, jednom 
//		za load jednom za store kao sto je slucaj kod niz[3]=niz[3]+1;
//		Zato si u designOneSquare morao da ide Code.dup da bi duplirao indeks 3 
//		Pa to onda ovde da iskoristis za formiranje steka za STORE i to ej to

		if (DStatementDec.getDesignator().obj.getLevel() == 1 && DStatementDec.getDesignator().obj.getKind() != 5) {
			Code.put(Code.pop);
			// Ovaj pop ide jer je vrednost ispod inc vec stavljena, pa ga sklonim da bi to
			// inc postavio
			// ovo gore je debug pokazao
			Code.put(Code.inc);
			// ekstremno glup nacin realizacije inc (njihove realizacije), pogledaj
			// mikrojava.pdf
			Code.put(DStatementDec.getDesignator().obj.getAdr());
			Code.put(-1);// increment
		} else {
			Code.loadConst(-1);
			Code.put(Code.add);
			if (DStatementDec.getDesignator().obj.getKind() == 5) {
				// Ovo postoji ovde ovako jer sam u DesignOneSquare ili kako vec
				// rekao ako je njegov roditelj DstatInc ili Dec onda odradi DUP
				// pa ces u ovom momentu na steku imati:
				// indeks u nizu : vrednost koja treba da se postavi
				// npr niz[7]++; (niz[7] je bio 45 npr)
				// na steku je 7 46
				{ // Pravim privremeno da ucita lepo to, ovo je sve kopirano gore iz
					// assign gde upisujem vrednosti
					Obj obj = new Obj(Obj.Var, "nebitno", Tab.intType);
					obj.setLevel(DStatementDec.getDesignator().obj.getLevel());
					obj.setAdr(DStatementDec.getDesignator().obj.getAdr());
					Code.load(obj);
				}
				Code.put(Code.dup_x2);
				Code.put(Code.pop);
			}
			Code.store(DStatementDec.getDesignator().obj);
		}
	}

//	E na zalost je doslo vreme da se radi if 
//	Hej za A nivo ti ne treba IF, samo ternarni operator                                                                                                  

//	Ovo radim posle gotovog projekta, radi malo dodavanja
	public void visit(CondFactOne CondFactOne) {
//		Ovo jos nije potrebno ali radim jer zelim da izmenim malo parser
//		Da moze i true i false da ima uternarnom  
	}

	public void visit(CondFactRelop CondFactRelop) {
//		Kao i sa termMore gore, trebalo bi da ti se vec
//		obe vrednosti nalaze na steku, sada ti samo da pozoves potrebnu operaciju 
//		i to bi trebalo da bude to
//		Fora je sto treba da pogledas koja prva pada i za svaku mogucu od 6 
//		logickih uslova da smislis kako odraditi to u MJ i da taj kod ispises
//		a ne da radis neke skokove ili tako neke uslove
		boolean isEqual = (CondFactRelop.getRelop().getClass() == RelopEqual.class);
		boolean isNotEqual = (CondFactRelop.getRelop().getClass() == RelopNotEqual.class);
		boolean isGreater = (CondFactRelop.getRelop().getClass() == RelopGreater.class);
		boolean isGreaterEqual = (CondFactRelop.getRelop().getClass() == RelopGreaterEqual.class);
		boolean isLower = (CondFactRelop.getRelop().getClass() == RelopLower.class);
		boolean isLowerEqual = (CondFactRelop.getRelop().getClass() == RelopLowerEqual.class);
//		inverse[]={ne, eq, ge, gt, le, lt}; Prekopirano iz Code.class
//		jumpFalse ne radi kako ti zelis! Ne koristi Code.eq jer nije lepo sortirano
//		Koristi ovo gore
		if (isEqual)
			Code.putFalseJump(1, 0); // put(jeq) put put 100 101 102
//			Code.loadConst(0);				// put					103 
//			Code.putJump(0);				// put(jmp) put put		104 105 106
//			Code.fixup(Code.pc-6);			//							  		PC = 107
//			Code.loadConst(1);				// put					107 
//			Code.fixup(Code.pc-3);//							  				PC = 108
		else if (isNotEqual)
			Code.putFalseJump(0, 0);
		else if (isGreater)
			Code.putFalseJump(3, 0);
		else if (isGreaterEqual)
			Code.putFalseJump(2, 0);
		else if (isLower)
			Code.putFalseJump(5, 0);
		else if (isLowerEqual)
			Code.putFalseJump(4, 0);
		else
			System.out.println("Greska neka");

		Code.loadConst(0);
		Code.putJump(0);
		Code.fixup(Code.pc - 6);
		Code.loadConst(1);
		Code.fixup(Code.pc - 3);

	}

	public void visit(Expr0 Expr0) {
//		Izlazak iz ternarnog, na steku bi trebalo da se nalazi vrednost za dodelu
//		System.out.println("Expro0" + Code.pc);

//		adrExpr.print();
		adrExpr.fixup(adrExpr.whereToPutFirstAfter, adrExpr.afterInst);
		adrExpr.fixup(adrExpr.whereToPutFirstFalse, adrExpr.firstInstrFalse);
		adrExpr.clear();
	}

	public void visit(ExprCondition ExprCondition) {
//		Izlaz iz uslova koji bi trebalo da je na steku
//		System.out.println("ExprCond" + Code.pc);
//		Ovde je na kraju pc vrednost prve instrukcije za TRUEexpr
//		Ovde treba samo da nastavis dalje da kompajliras, ali i verovatno 
//		da sacuvas tu adresu negde

//		E konju jedan. jmp if not equal radi sa dve vrednosti na steku, moras da stavis pored expr 
//		i drugu vrednost, tj. nulu, zato ti baca gresku 1794
		Code.loadConst(0);

//		Sada ovde kazes, ako nije true onda skoci na FALSE a tu adresu jos ne znas

		adrExpr.whereToPutFirstFalse = Code.pc + 1; // Jer prvo ide JEQ pa onda adresa
		Code.putFalseJump(Code.ne, adrExpr.tmpAdr);
//		E mislim da im ovo lepo  ne radi, logicno mi da stavis, if equal 0 skoci
//		tj. if false skoci ali ako stavis Code.eq onda ne radi lepo, pa zato stavljam
//		Code.ne 
		adrExpr.firstInstrTrue = Code.pc; 
	}

	public void visit(ExprConditionTrue ExprConditionTrue) {
//		Izlaz iz prvogIzraza koji bi trebalo da je na steku
//		System.out.println("ExprTrue" + Code.pc);
//		Ovde je prva adresa posle ovoga adresa FALSEExpr 
//		NJu svakako moras da sacuvas i nekako da je vratis gore u slucaju da se 
//		radi o FALSE uslovu
//		Takodje ako si dosao do ovde, ovde svakako moras da radis skok JMP na 
//		prvu instrukciju posle ternarnog operatora

		adrExpr.whereToPutFirstAfter = Code.pc + 1;
		Code.putJump(adrExpr.tmpAdr); // skok na sledecu instrukciju
		adrExpr.firstInstrFalse = Code.pc;
	}
  
	public void visit(ExprConditionFalse ExprConditionFalse) {
//		Izlaz iz drugogIzraza koji bi trebalo da je na steku
//		System.out.println("ExprFalse" + Code.pc);
//		Posle ovoga Pc ima vrednost prve instrukcije posle ternarnog operatora, tj. 
//		to obicno biva STore ako je bila jednakost ili tako nesto
//		Svakako je to mesto gde skaces iz TRUEexpr

//		Ovde nemas gde da skaces, nastavljas dalje.
		adrExpr.afterInst

				= Code.pc;
	}

}
