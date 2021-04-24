package rs.ac.bg.etf.pp1;

import rs.ac.bg.etf.pp1.CounterVisitor.FormParamCounter;
import rs.ac.bg.etf.pp1.CounterVisitor.VarCounter;
import rs.ac.bg.etf.pp1.ast.AddopMinus;
import rs.ac.bg.etf.pp1.ast.AddopPlus;
import rs.ac.bg.etf.pp1.ast.DStatementAssign;
import rs.ac.bg.etf.pp1.ast.DStatementParen;
import rs.ac.bg.etf.pp1.ast.DesignatorJustOne;
import rs.ac.bg.etf.pp1.ast.DesignatorOneArray;
import rs.ac.bg.etf.pp1.ast.DesignatorOneDot;
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
import rs.ac.bg.etf.pp1.ast.MethodDeclaration;
import rs.ac.bg.etf.pp1.ast.MethodTypeName;
import rs.ac.bg.etf.pp1.ast.MulopDiv;
import rs.ac.bg.etf.pp1.ast.MulopMod;
import rs.ac.bg.etf.pp1.ast.MulopMul;
import rs.ac.bg.etf.pp1.ast.PrintValue;
import rs.ac.bg.etf.pp1.ast.StatPrint;
import rs.ac.bg.etf.pp1.ast.StatPrintValue;
import rs.ac.bg.etf.pp1.ast.StatRead;
import rs.ac.bg.etf.pp1.ast.StatReturn;
import rs.ac.bg.etf.pp1.ast.StatReturn2;
import rs.ac.bg.etf.pp1.ast.SyntaxNode;
import rs.ac.bg.etf.pp1.ast.TermMore;
import rs.ac.bg.etf.pp1.ast.VisitorAdaptor;
import rs.etf.pp1.mj.runtime.Code;
import rs.etf.pp1.symboltable.Tab;
import rs.etf.pp1.symboltable.concepts.Obj;
import rs.etf.pp1.symboltable.concepts.Struct;

public class CodeGenerator extends VisitorAdaptor {
//	Snimak si odgledao, malo preleteo kraj.
//	Sad citam pdfove

//	U Code klasi put 0123 oznacava koliko bajtova ucitava

	private int mainPc;
	private Struct boolStruct;

	public int getMainPc() {
		return mainPc;
	}

	public CodeGenerator() {
		Obj obj = Tab.find("bool");
		boolean foundType = obj != null || obj != Tab.noObj;
		boolean isType = obj.getKind() == obj.Type;
//		ovo != null mzoes svuda da sklonis jer kad pogledas kod nikada nece vratiti null ta njihova metoda
		if (!foundType)
			System.out.println("Ne postoji BOOL struktura u sistemu");
		else if (!isType)
			System.out.println("Nisi lepo ubacio BOOL u tabelu jer nije vrste Type");
		else
			boolStruct = obj.getType();
	}

	private void generisanjeORDCHR() {
//		Provali gde ovo da pozivas (mozes u MethTypeName)
//		ovo ti ne treba za A jer nemas poziv metoda  ! !! ! 

	}

	@Override
	public void visit(MethodTypeName methodTypeName) {
//  Ovo nisam dirao i ostaje tako kako jeste

		if ("main".equalsIgnoreCase(methodTypeName.getMethodName())) {
			mainPc = Code.pc;
		}
		methodTypeName.obj.setAdr(Code.pc);
		// Collect arguments and local variables
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

//		ulazak u MAIN

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
		// Ovo je izvorni kod sa snimka
		// if (FactNum==null )
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
//		 velicina ti je vec na steku [n], to si gurnuo kada si ima nesto tipa:
//		Expr ili NUM_CONST ili tako nesto, svakako bi trebalo da je vec na steku kada
//		si prolazio kroz to
		boolean isChar = (FactNewArray.struct.getElemType() == Tab.charType);
		System.out.println("PROVERI: vrednost isChar je : " + isChar);

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

//	U izvornom snimku imas objasnjenje ovoga

	public void visit(StatPrint StatPrint) {
		if (StatPrint.getExpr().struct == Tab.intType) {
//			Zasto je int 5?
			Code.loadConst(5); // Ovo je width(), odnosno sirina jednog INTa
			Code.put(Code.print); // Ovo je ispis INTa
		} else if (StatPrint.getExpr().struct == Tab.charType) {

			Code.loadConst(1);
			Code.put(Code.bprint);
		} else if (StatPrint.getExpr().struct == boolStruct) {
			Code.pc = Code.pc - 1; // skontah da moze i Code.pop

			int numLetters;
			String[] boolString = { "false", "truea" };
			int intValue = Code.get(Code.pc);
			if (intValue == Code.const_1)
				numLetters = 4;
			else
				numLetters = 5;

			int it = 0;
			while (it < numLetters) {
				Code.loadConst(boolString[intValue - 15].charAt(it));
				Code.loadConst(1);
				Code.put(Code.bprint);
				it++;
			}
//			Code.loadConst(3);
//			Code.put(Code.print);
		} else
			System.out.println("Neka greska se desila");
	}

	@Override
	public void visit(StatPrintValue StatPrintValue) {
		// Kako uopste izgleda ovaj smesni print?
	}

	public void visit(PrintValue PrintValue) {
	}

//	A sada StatRead, kad sam vec odradio print a to deluje lako

	@Override
	public void visit(StatRead StatRead) {
		Struct typeOfDes = StatRead.getDesignator().obj.getType();
//		Ako je element niza onda on to sve sredi u store?
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
		Code.store(DStatementAssign.getDesignator().obj);
		// on u pozadini u mjruntime odradi store zavisno sta stavljas
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

	public void visit(DesignatorOneArray DesignatorOneArray) {
//		Ovo je niz, vidis tamo da na steku treba da bude adr, index
//		pa onda kad se izvrsi ostaje val
//		Prvo ista prica, provera da li je funkcija i tako to
		SyntaxNode parent = DesignatorOneArray.getParent();
		if (DStatementAssign.class != parent.getClass() && DStatementParen.class != parent.getClass()
				&& StatRead.class != parent.getClass()) {
//			Ne zezaj se sa ovim Code.pc pa -1 i tako to sto si hteo,
//			odradi DUP_x1 POP, ubedljivo najlaksi nacin za zamenu mesta, bukvalno su zbog ovoga i dali
//			System.out.println("Pre dupliranja, poslednjih 5 vr na steku su:" + "\n-1:" + Code.buf[Code.pc - 1] + "\n-2"
//					+ Code.buf[Code.pc - 2] + "\n-3" + Code.buf[Code.pc - 3] + "\n-4" + Code.buf[Code.pc - 4] + "\n-5"
//					+ Code.buf[Code.pc - 5]);
			Code.put(Code.dup_x1);
			Code.put(Code.pop);
//			Code.put(Code.aload);
			Code.put((DesignatorOneArray.obj.getType() == Tab.charType) ? Code.baload : Code.aload);
//			I posle ovoga na steku ce se naci vrednost iz niza

		}
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
		else if (isMod)
			System.out.println("Ne postoji mod, sta sad? ");
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
//	public void visit(AddExpr addExpr) {
//		Code.put(Code.add);
//	}

}
