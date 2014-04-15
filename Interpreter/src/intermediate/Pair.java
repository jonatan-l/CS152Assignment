package intermediate;

import frontend.Token;

class Pair implements Atom {
	private Atom car;
	private Pair cdr;

	public Pair() {
		car = null;
		cdr = null;
	}

	public Pair(Atom car) {
		this.car = car;
		cdr = new Pair();
	}

	public Pair(Atom car, Atom cdr) {
		this.car = car;
		this.cdr = new Pair(cdr);
	}

	public Pair add(Atom item) {
		car = item;
		cdr = new Pair();
		return cdr;
	}

	public Atom getCar() {
		return car;
	}

	public Atom getCdr() {
		return cdr;
	}

	public void setCar(Atom car) {
		this.car = car;
		if (cdr == null) {
			cdr = new Pair();
		}
	}

	public void setCdr(Token cdr) {
		this.cdr = new Pair(cdr);
	}

	public void setCdr(Pair cdr) {
		this.cdr = cdr;
	}

	public boolean isAtom() {
		return false;
	}
}