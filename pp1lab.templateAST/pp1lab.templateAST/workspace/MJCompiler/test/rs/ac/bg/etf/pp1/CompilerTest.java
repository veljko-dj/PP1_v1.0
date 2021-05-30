package rs.ac.bg.etf.pp1;

import rs.ac.bg.etf.pp1.MyCompiler;

public class CompilerTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		MyCompiler mycomp = new MyCompiler();
		
		mycomp.compile("test/program.mj", "test/izlaz.obj");
	}

}
