package backend;

import frontend.Token;
import intermediate.Pair;
import intermediate.Atom;
import intermediate.SymbolTable;

public class Executor {

	public Object run(Pair p) {
		return p;
	}

	public Pair cdr(Pair currentRoot) {
		if (currentRoot == null) {
			return null;
		}
		if (currentRoot.getCdr().isAtom()) // Checks if an Atom is a Pair(true)
											// pr Atom (false)
		{
			return currentRoot.getCdr();
		}
		return null;
	}

	public Token car(Pair currentRoot) {
		if (currentRoot == null) {
			return null;
		}
		if (currentRoot.getCar().isAtom()) // Checks if an Atom is a Token
											// (true) or Pair(false)
		{
			return (Token) currentRoot.getCar();
		}
		return null;
	}

	public Pair cons(Pair p) {
		return null;
	}

	public boolean isList() {
		return false;
	}

	public boolean testNull(Pair p) {

		if (p == null)
			return true;
		else
			return false;
	}

	public boolean testSymbol(Pair p) {
		String[] specialchars = { "(", ")", "[", "]", "{", "}", ";", ",", ".",
				"\"", "'", "#", "\\" };
		for (int i = 0; i < specialchars.length; i++) {
			if (p.getCdr().isAtom()) {
				if (((Token) ((Atom) p.getCdr())).getValue().equals(
						specialchars[i])) {
					return true;
				}
			}
		}
		return false;
	}

	public boolean testInteger(Pair p) {
		if (Integer.parseInt(((Token) ((Atom) p.getCdr())).getValue()) % 1 == 0) {
			return true;
		} else
			return false;
	}

	public boolean testFloat(Pair p) {
		if (Float.parseFloat(((Token) ((Atom) p.getCdr())).getValue())
				% (float) 1.0 != (float) 0.0) {
			return true;
		} else
			return false;
	}

	public boolean testBoolean(Pair p) {
		if (((Token) ((Atom) p.getCdr())).getValue().equals("#t")
				|| ((Token) ((Atom) p.getCdr())).getValue().equals("#f")) {
			return true;
		} else
			return false;
	}

	public boolean testChar(Pair p) {
		return false;
	}

	public boolean testString(Pair p) {
		return false;
	}

	public boolean testPair(Pair p) {// (a) also a pair
		if (p.getCdr() == null) {
			return false;
		} else
			return true;
	}

	public int addIntegerMethod(Pair p) {// use root can add as many as we need
											// to -recursive...base case!!! cdr
											// empty add 0

		if (p == null) {
			return 0;
		} else if (!p.getCar().isAtom()) {
			return Integer.parseInt(run((Pair) p.getCar()).toString())
					+ addIntegerMethod(p.getCdr());// will run() return int for
													// this?
		} else {
			return Integer.parseInt(((Token) p.getCar()).getValue())
					+ addIntegerMethod(p.getCdr());// p.getCar() is atom
													// -integer-
		}
	}// is car is a pair - call main run on it

	public int subIntegerMethod(Pair p) {

		if (p == null) {
			return 0;
		} else if (!p.getCar().isAtom()) {
			return Integer.parseInt(run((Pair) p.getCar()).toString())
					- subIntegerMethod(p.getCdr());
		} else {
			return Integer.parseInt(((Token) p.getCar()).getValue())
					- subIntegerMethod(p.getCdr());
		}
	}

	public int multiplyIntegerMethod(Pair p) {
		if (p == null) {
			return 1;
		} else if (!p.getCar().isAtom()) {
			return Integer.parseInt(run((Pair) p.getCar()).toString())
					* multiplyIntegerMethod(p.getCdr());
		} else {
			return Integer.parseInt(((Token) p.getCar()).getValue())
					* multiplyIntegerMethod(p.getCdr());
		}
	}

	public float addNumberMethod(Pair p) {// use root can add as many as we need
											// to -recursive...base case!!! cdr
											// empty add 0

		if (p == null) {
			return 0;
		} else if (!p.getCar().isAtom()) {
			return Float.parseFloat(run((Pair) p.getCar()).toString())
					+ addNumberMethod(p.getCdr());// will run() return int for
													// this?
		} else {
			return Float.parseFloat(((Token) p.getCar()).getValue())
					+ addNumberMethod(p.getCdr());// p.getCar() is atom
													// -integer-
		}
	}// is car is a pair - call main run on it

	public float subNumberMethod(Pair p) {

		if (p == null) {
			return 0;
		} else if (!p.getCar().isAtom()) {
			return Float.parseFloat(run((Pair) p.getCar()).toString())
					- subNumberMethod(p.getCdr());
		} else {
			return Float.parseFloat(((Token) p.getCar()).getValue())
					- subNumberMethod(p.getCdr());
		}
	}

	public float multiplyNumberMethod(Pair p) {
		if (p == null) {
			return 1;
		} else if (!p.getCar().isAtom()) {
			return Float.parseFloat(run((Pair) p.getCar()).toString())
					* multiplyNumberMethod(p.getCdr());
		} else {
			return Float.parseFloat(((Token) p.getCar()).getValue())
					* multiplyNumberMethod(p.getCdr());
		}
	}

	public boolean andMethod(Pair p) {

		if (p.getCdr().getCar() == null) {
			return true;
		} else if (Boolean.parseBoolean(run(((Pair) p.getCdr().getCar()))
				.toString())
				&& Boolean.parseBoolean(run(p.getCdr().getCdr()).toString())) {
			return true;
		} else
			return false;
	}

	public boolean orMethod(Pair p) {

		if (p.getCdr().getCar() == null) {// call has no expressions
			return false;
		}
		if (p.getCar() != null && p.getCdr() == null) {// end of expressions
			return Boolean.parseBoolean(run(((Pair) p.getCar())).toString());
		} else if (Boolean.parseBoolean(run(((Pair) p.getCar())).toString()) == true
				&& p.getCdr() != null) {
			return orMethod(p.getCdr());
		}
		return false;
	}
}
