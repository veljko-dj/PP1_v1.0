package rs.ac.bg.etf.pp1;

import java.util.ArrayList;
import java.util.List;

import rs.ac.bg.etf.pp1.test.CompilerError;

public class CompilerTest {
	// Ispis sintaksnih informacija: U mjparser.cup odkomentarisi liniju 53
	// Ispih sintaksnog stabla : MyCompilerImpl.java -> prva bool promenljiva = true
	// Ispis semantickih informacija:  SemanticPass.java -> prva bool promenljiva = true
	// Ispis tabele simbola:  MyCompilerImpl.java -> druga bool promenljiva = true
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		
		MyCompilerImpl mycomp = new MyCompilerImpl();
		List<CompilerError> tmpErrorList = new ArrayList<CompilerError>();
		boolean postojiGreska = false;

		/****
		 * TEST301*** Javni test
		 *************/
		System.out.println("####################################################################################"); 
		System.out.println("\t\t***TESTT301***");
		tmpErrorList = mycomp.compile("test/test301.mj", "test/test301.obj");
		System.out.println("\n" + MyCompilerImpl.toStringVeljko());
		if (!tmpErrorList.isEmpty())
			postojiGreska = true;
		/************/

		/****
		 * TEST1*** Nizovi: dodela elemntu niza tipa int dodela elemntu niza tipa bool
		 * inc, dec na elementu niza dodela niza nizu niz[] u if-u print i read u niz
		 * GRESKA nekompatibilna dodela
		 *************/ 
		System.out.println("####################################################################################"); 
		System.out.println("\t\t***TESTT1***");
		tmpErrorList = mycomp.compile("test/test1.mj", "test/test1.obj");
		System.out.println("\n" + MyCompilerImpl.toStringVeljko());
		if (!tmpErrorList.isEmpty())
			postojiGreska = true;
		/************/

		/****
		 * TEST2*** If: ugnjezdeni bez/sa else granom && || zajedno ternarni globalni
		 * inc
		 *************/
		System.out.println("####################################################################################"); 
		System.out.println("\t\t***TESTT2***");
		tmpErrorList = mycomp.compile("test/test2.mj", "test/test2.obj");
		System.out.println("\n" + MyCompilerImpl.toStringVeljko());
		if (!tmpErrorList.isEmpty())
			postojiGreska = true;
		/************/

		/****
		 * TEST3*** Ternarni: Testiranje starog ternarnog. Moze da se desi da ne radi.
		 * Posle izmene projekta ga nisam testirao
		 *************/
		System.out.println("####################################################################################"); 
		System.out.println("\t\t***TESTT3***");
		tmpErrorList = mycomp.compile("test/test3.mj", "test/test3.obj");
		System.out.println("\n" + MyCompilerImpl.toStringVeljko());
		if (!tmpErrorList.isEmpty())
			postojiGreska = true;
		/************/

		/****
		 * TEST4*** Sluzio za testiranje: ++ -- na globalnim ++ -- na nizu ++ -- na
		 * lokalnim slozenih expr blokova koda dodeljivanja elemenata itd.
		 *************/
		System.out.println("####################################################################################"); 
		System.out.println("\t\t***TESTT4***");
		tmpErrorList = mycomp.compile("test/test4.mj", "test/test4.obj");
		System.out.println("\n" + MyCompilerImpl.toStringVeljko());
		if (!tmpErrorList.isEmpty())
			postojiGreska = true;
		/************/

		/****
		 * TEST5*** GRESKE:
		 *************/
		System.out.println("####################################################################################"); 
		System.out.println("\t\t***TESTT5***");
		tmpErrorList = mycomp.compile("test/test5.mj", "test/test5.obj");
		System.out.println("\n" + MyCompilerImpl.toStringVeljko());
		if (!tmpErrorList.isEmpty())
			postojiGreska = true;
		/************/
		
		
		
		
		/************/
		if (!postojiGreska) System.out.println("\n\n *** Ni u jednom fajlu ne postoji greska ***");
		else System.out.println("\n\n *** U nekom fajlu postoji greska ***");
	}

}
