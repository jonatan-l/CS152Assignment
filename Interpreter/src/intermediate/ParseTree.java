package intermediate;

import frontend.Token;
/*
   For Assignment #5, your parse tree and symbol table classes in the intermediate package.
*/
public class ParseTree {

	public ParseTree() {

	}

	class Pair implements Atom{
		private Atom car;
		private Atom cdr;
		public Pair(){
		
		}
		public Atom getCar() {
			return car;
		}

		public Atom getCdr() {
			return cdr;
		}

		public void setCar(Token car) {
			this.car = car;
		}

		public void setCdr(Token cdr) {
			this.cdr = cdr;
		}
		public boolean isAtom() {
			return false;
		}
	}
}
