package rs.ac.bg.etf.pp1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import java_cup.runtime.Symbol;
import rs.ac.bg.etf.pp1.ast.Program;
import rs.ac.bg.etf.pp1.test.Compiler;
import rs.ac.bg.etf.pp1.test.CompilerError;
import rs.ac.bg.etf.pp1.test.CompilerError.CompilerErrorType;
import rs.ac.bg.etf.pp1.util.Log4JUtils;
import rs.etf.pp1.mj.runtime.Code;
import rs.etf.pp1.symboltable.Tab;

public class MyCompilerImpl implements Compiler {

	private static List<CompilerError> listError = new ArrayList<CompilerError>();

	@Override
	public List<CompilerError> compile(String sourceFilePath, String outputFilePath) {
		listError.clear();

		// static. Ovo ovde je sve bilo static
		DOMConfigurator.configure(Log4JUtils.instance().findLoggerConfigFile());
		Log4JUtils.instance().prepareLogFile(Logger.getRootLogger());

		// a odavde je kretao main
		Logger log = Logger.getLogger(MyCompilerImpl.class);

		Reader br = null;
		try {
			File sourceCode = new File(sourceFilePath);
			log.info("Compiling source file: " + sourceCode.getAbsolutePath());

			br = new BufferedReader(new FileReader(sourceCode));
			Yylex lexer = new Yylex(br);

			log.info("\n================Sintaksna analiza===================");

			MJParser p = new MJParser(lexer);
			Symbol s = p.parse(); // pocetak parsiranja

			Program prog = (Program) (s.value);
			Tab.init();
			// ispis sintaksnog stabla
			log.info("\n==============Sintaksno stablo=====================");
			log.info(prog.toString(""));
			log.info("\n==============Semanticka analiza=====================");

			// ispis prepoznatih programskih konstrukcija
			SemanticPass v = new SemanticPass();
			prog.traverseBottomUp(v);

			log.info(" Print count calls = " + v.printCallCount);

			// log.info(" Deklarisanih promenljivih ima = " + v.varDeclCount);

			// log.info(" I to su: "+ v.varDeclString);

			log.info("===================================");
			//////////////////////////// DODATO DODATO DODATO ! !! ! ! ! !
			Tab.dump(new My_DumpSymbolTableVisitor_Impl());
			// Tab.dump();

			if (!p.errorDetected && v.passed()) {
				File objFile = new File(outputFilePath);
				if (objFile.exists())
					objFile.delete();

				CodeGenerator codeGenerator = new CodeGenerator();
				prog.traverseBottomUp(codeGenerator);
				Code.dataSize = v.nVars;
				Code.mainPc = codeGenerator.getMainPc();
				Code.write(new FileOutputStream(objFile));
				log.info("Parsiranje uspesno zavrseno!");
			} else {
				log.error("Parsiranje NIJE uspesno zavrseno");
			}

		} catch (FileNotFoundException e) {
			System.err.println("Ne postoji fajl koji zelis da otvoris");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (br != null)
				try {
					br.close();
				} catch (IOException e1) {
					log.error(e1.getMessage(), e1);
				}
		}

		return listError;
	}

	public static void addErrorVeljko(int type, String message, int line) {

		CompilerError tmp = new CompilerError(line, message, type == 0 ? CompilerErrorType.LEXICAL_ERROR
				: (type == 1 ? CompilerErrorType.SYNTAX_ERROR : CompilerErrorType.SEMANTIC_ERROR));

		listError.add(tmp);

		// System.out.println("addErrorVeljko() " + tmp.toString());
	}

	public static String toStringVeljko() {

		listError.sort(new Comparator<CompilerError>() {
			@Override
			public int compare(CompilerError e1, CompilerError e2) {
				return (e1.getLine() >= e2.getLine()) ? 1 : -1;
			}
		});

		if (!listError.isEmpty())
			System.out.println("ISPIS_GRESAKA\n");
		else
			System.out.println("NEMA_GRESAKA");
		StringBuilder str = new StringBuilder();
		for (CompilerError tmp : listError) {
			// System.out.println(tmp.toString());
			str.append(tmp.toString());
			str.append('\n');
		}
		return str.toString();
	}

}
