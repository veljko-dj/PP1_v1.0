package rs.ac.bg.etf.pp1;

import java.util.HashSet;
import java.util.Set;

public class KlasaFinal {

	private static Set<String> skupFinal = new HashSet<>();
	private static Set<String> skupDodeljeno = new HashSet<>();

	public static void naisaoNaFinal(String s) {
		skupFinal.add(s);
	}

	public static void dodelioVrednost(String s) {
		skupDodeljeno.add(s);
	}

	public static boolean daLiJeFinal(String s) {
		return skupFinal.contains(s);
	}

	public static boolean daLiJeDodeljanaVrednost(String s) {
		return skupDodeljeno.contains(s);
	}

}
