package rs.ac.bg.etf.pp1;

import rs.ac.bg.etf.pp1.CounterVisitor.FormParamCounter;
import rs.ac.bg.etf.pp1.CounterVisitor.VarCounter;
import rs.ac.bg.etf.pp1.ast.AddopMinus;
import rs.ac.bg.etf.pp1.ast.AddopPlus;
import rs.ac.bg.etf.pp1.ast.CondFactOne;
import rs.ac.bg.etf.pp1.ast.CondFactRelop;
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
import rs.ac.bg.etf.pp1.ast.MethodDeclaration;
import rs.ac.bg.etf.pp1.ast.MethodTypeName;
import rs.ac.bg.etf.pp1.ast.MulopDiv;
import rs.ac.bg.etf.pp1.ast.MulopMod;
import rs.ac.bg.etf.pp1.ast.MulopMul;
import rs.ac.bg.etf.pp1.ast.PrintValue;
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

	private class CodeGenerator_PCAdresses_Expr {

		public int firstInstrTrue = 0;
		public int firstInstrFalse = 0;
		public int nextInst = 0; // sledeca posle ternarnog operatora
		public int tmpAdr = -3; // Privremena adresa koju cu stavljati svuda za
								// skokove pre nego sto namestim na korektnu
		public int whereToPutFirstTrue = 0;
		public int whereToPutFirstFalse = 0;
		public int whereToPutFirstNext = 0;

		public void clear() {
			firstInstrFalse = 0;
			firstInstrTrue = 0;
			nextInst = 0;
			whereToPutFirstTrue = 0;
			whereToPutFirstFalse = 0;
			whereToPutFirstNext = 0;
		}

		public void print() {
			System.out.println("true: " + firstInstrTrue);
			System.out.println("false: " + firstInstrFalse);
			System.out.println("next: " + nextInst);
			System.out.println("WhereTrue: " + whereToPutFirstTrue);
			System.out.println("WhereFalse: " + whereToPutFirstFalse);
			System.out.println("WhereNext: " + whereToPutFirstNext);
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
//		Zasto ovde nisam prvo na stek stavio ono sto treba da bude
//		Mislim da je odgovor jer kad udjes u return nesto on ode u expr
//		i tamo stavi to sto treba na stek
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

//	U izvornom snimku imas objasnjenje ovoga

	public void visit(StatPrint StatPrint) {
		if (StatPrint.getExpr().struct == Tab.intType) {
//			Zasto je int 5? Stavio sam ipak 4 jer mi to ima smisla
			Code.loadConst(4); // Ovo je width(), odnosno sirina jednog INTa
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
		System.out.println("tip za read stmt" + typeOfDes.getKind());
		if (StatRead.getDesignator().obj.getKind() == 5) {
//			System.out.println("Dodela elementu niza");
//			 kreiram objekat koji ce biti konstanta koja oznacava adresu niza mog
			{
				Obj obj = new Obj(Obj.Var, "nebitno", Tab.intType);
				obj.setLevel(StatRead.getDesignator().obj.getLevel());
				obj.setAdr(StatRead.getDesignator().obj.getAdr());
				Code.load(obj);
			}
			Code.put(Code.dup_x1);
			Code.put(Code.pop);
//			Zasto dup_x1? Zato sto trenutno imas na steku indeks, pa stavljas adresu
//			i zarotiras i onda imas adr, index i pomocu read ce leci lepo VALUE

//			Dobro si se setio ovoga, ovo je bas napravljeno zbog ovog glupog poziva
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
//			System.out.println("Dodela elementu niza");
//			 kreiram objekat koji ce biti konstanta koja oznacava adresu niza mog
			{
				Obj obj = new Obj(Obj.Var, "nebitno", Tab.intType);
				obj.setLevel(DStatementAssign.getDesignator().obj.getLevel());
				obj.setAdr(DStatementAssign.getDesignator().obj.getAdr());
				Code.load(obj);
			}
			Code.put(Code.dup_x2);
			Code.put(Code.pop);

//			Zasto bas x2? Zato sto je vrednost prvo stavljen indeks na stek,
//			pa zatim stavljena vrednost jer dolazis iz expr
//			i onda sada ti stavis adresu, pa odradis rotaciju

		}
		Code.store(DStatementAssign.getDesignator().obj);
//		 on u pozadini u mjruntime odradi store zavisno sta stavljas

//		Obj a = DStatementAssign.getDesignator().obj;
//		System.out.println("Dstatementassign : \n" + a.getType() + "\n" + a.getType().getElemType() + "\n"
//				+ a.getType().getKind() + "\n" + a.getName() + "\n" + a.getKind() + "\n" + a.getAdr() + "\n");
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
//			Code.load(DesignatorOneArray.obj);
			{
				Obj obj = new Obj(Obj.Var, "nebitno", Tab.intType);
				obj.setLevel(DesignatorOneArray.obj.getLevel());
				obj.setAdr(DesignatorOneArray.obj.getAdr());
				Code.load(obj);
			}
//			Obj a = DesignatorOneArray.obj;
//			System.out.println("Sta se ovde desava");
//			System.out.println("Dstatementassign : \n" + a.getType() + "\n" + a.getType().getElemType() + "\n"
//					+ a.getType().getKind() + "\n" + a.getName() + "\n" + a.getKind() + "\n" + a.getAdr() + "\n");

			Code.put(Code.dup_x1);
			Code.put(Code.pop);
//			Zasto x1? Zato sto je prvo indeks dosao na stek, pa ti stavis adresu
//			i onda zamenis mesta 

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

//	Vracam se da odradim ono sto nisam odradio malo pre, a to je 
//	DStatementInc i DStatementDec

	public void visit(DStatementInc DStatementInc) {
//		ovo nije tako jednostavno kako si mislio. Da bi odradio inkrement
//		moras da dodas i koju lokalnu vrednost i za koliko increment
//		Znaci moras da ucitas objekat koji zelis da inkrementiras
//		Da ga inkrementiras i sacuvas opet. Ali nisam siguran
//		da ce sve lepo raditi sa inc pa zato odradi add ako ne bude radilo		

//		v1
//		Code.load(DStatementInc.getDesignator().obj);
//		Code.loadConst(1);
//		Code.put(Code.add);
//		Code.store(DStatementInc.getDesignator().obj);

//		v2 Proveri radi li
		Code.put(Code.inc);
		Code.put(DStatementInc.getDesignator().obj.getAdr());
		Code.put(1);// increment
//		Probaj, za niz bi bilo mozda nesto tipa getAdr+ indeks
	}

	public void visit(DStatementDec DStatementDec) {
		Code.put(Code.inc);
		Code.put(DStatementDec.getDesignator().obj.getAdr());
		Code.put(-1);
	}

//	E na zalost je doslo vreme da se radi if 
//	Hej za A nivo ti ne treba IF, samo ternarni operator                                                                                                  

//	Da krenem prvo od uslova
//	Pa brate u izmenjenom kodu ono, gde ti je
//	expr ? expr : expr tu ti ne treba uopste 
//	cond bilo sta

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
		if (isEqual)
			Code.putFalseJump(Code.eq, 0); // put(jeq) put put 100 101 102
//			Code.loadConst(0);				// put					103 
//			Code.putJump(0);				// put(jmp) put put		104 105 106
//			Code.fixup(Code.pc-6);			//							  		PC = 107
//			Code.loadConst(1);				// put					107 
//			Code.fixup(Code.pc-3);//							  				PC = 108
		else if (isNotEqual)
			Code.putFalseJump(Code.ne, 0);
		else if (isGreater)
			Code.putFalseJump(Code.gt, 0);
		else if (isGreaterEqual)
			Code.putFalseJump(Code.ge, 0);
		else if (isLower)
			Code.putFalseJump(Code.lt, 0);
		else if (isLowerEqual)
			Code.putFalseJump(Code.le, 0);
		else
			System.out.println("Greska neka");

		Code.loadConst(0); // put 103
		Code.putJump(0); // put(jmp) put put 104 105 106
		Code.fixup(Code.pc - 6); // PC = 107
		Code.loadConst(1); // put 107
		Code.fixup(Code.pc - 3);// PC = 108
	}

	public void visit(Expr0 Expr0) {
//		Izlazak iz ternarnog, na steku bi trebalo da se nalazi vrednost za dodelu
//		System.out.println("Expro0" + Code.pc);

//		adrExpr.print();
		adrExpr.fixup(adrExpr.whereToPutFirstNext, adrExpr.nextInst);
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

		adrExpr.whereToPutFirstNext = Code.pc + 1;
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
		adrExpr.nextInst = Code.pc;
	}

}
