package backend;

import frontend.Token;
import intermediate.Atom;
import intermediate.Pair;
import intermediate.ParseTree;

public class TreePrinter {

	private ParseTree t;
	public TreePrinter(ParseTree tree){
		
		t = tree;
	}
	
	public void print(Pair t){
		
		if(t.getCar() == null){
			System.out.print(")");
		}
		else if(!t.getCar().isAtom()){//false ---- ! or not
			System.out.print("\n(");
			print((Pair) t.getCar());//manual type change?
			print(t.getCdr());
		}
		else if(t.getCar().isAtom()){//true
			System.out.print(t.getCar() + " ");
			print(t.getCdr());
		}
		
	}
	
	//create TreePrinter with the Parse tree then just call printer()
	public void printer(){
		
		System.out.print("(");
		print(t.getRoot());
	}
}
