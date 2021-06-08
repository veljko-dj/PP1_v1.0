package rs.ac.bg.etf.pp1;
 

public class CompilerTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		MyCompilerImpl mycomp = new MyCompilerImpl();
		
		mycomp.compile("test/program.mj", "test/izlaz1.obj");
 
		System.out.println("\n");
		System.out.println(MyCompilerImpl.toStringVeljko());
	}
		
}
