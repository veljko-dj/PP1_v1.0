package rs.ac.bg.etf.pp1;



public class CompilerTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		MyCompilerImpl mycomp= new MyCompilerImpl(); 
		//list<error
		//boolean postojiGreska= false;
		
		/****TEST301***
		Javni test
		*************/
		System.out.println("***TEST301***"); 
		mycomp.compile("test/test301.mj", "test/test301.obj");  
		System.out.println("\n"+ MyCompilerImpl.toStringVeljko());
		/************/
		
		/****TEST1***
		Nizovi:
			dodela elemntu niza tipa int
			dodela elemntu niza tipa bool
			inc, dec na elementu niza
			dodela niza nizu
			niz[] u if-u
			print i read u niz
			GRESKA nekompatibilna dodela
		*************/ 
		mycomp.compile("test/test1.mj", "test/test1.obj");  
		System.out.println("\n"+ MyCompilerImpl.toStringVeljko());
		/************/
		
		
		/****TEST2***
		If:
			ugnjezdeni
			bez/sa else granom
			&& || zajedno
			ternarni
			globalni inc
		*************/ 
		mycomp.compile("test/test2.mj", "test/test2.obj");  
		System.out.println("\n"+ MyCompilerImpl.toStringVeljko());
		/************/
		
		
		/****TEST3***
		Ternarni:
			Testiranje starog ternarnog.
			Moze da se desi da ne radi. 
			Posle izmene projekta ga nisam testirao
		*************/ 
		mycomp.compile("test/test3.mj", "test/test3.obj");  
		System.out.println("\n"+ MyCompilerImpl.toStringVeljko());
		/************/
		

		/****TEST4***
		Sluzio za testiranje:
			++ -- na globalnim
			++ -- na nizu
			++ -- na lokalnim
			slozenih expr
			blokova koda
			dodeljivanja elemenata itd.
		*************/ 
		mycomp.compile("test/test4.mj", "test/test4.obj");  
		System.out.println("\n"+ MyCompilerImpl.toStringVeljko());
		/************/
		

		/****TEST5***
		GRESKE:
		*************/ 
		mycomp.compile("test/test5.mj", "test/test5.obj");  
		System.out.println("\n"+ MyCompilerImpl.toStringVeljko());
		/************/
		 
	}
		
}
