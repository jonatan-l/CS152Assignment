package frontend;

import java.io.File;
import java.io.FileNotFoundException;

import backend.SymbolTablePrinter;
import backend.TreePrinter;

public class Main {
	public static void main(String[] args) throws FileNotFoundException {
<<<<<<< HEAD
		System.out.println("Start");
		File fi = new File("src/input.lisp");
		Parser start = new Parser(fi);
		start.populateParseTree();
		System.out.println("Tree /n --------------");
		TreePrinter p = new TreePrinter(start.getParseTree());
		p.printer();
		
		System.out.println("Table /n -------------");
		SymbolTablePrinter t = new SymbolTablePrinter(start.getSymbolTable());
		t.printer();
		
=======
		File fi = new File("C:\\Users\\Dima\\IdeaProjects\\CS152Assignment\\Interpreter\\src\\frontend\\input.lisp");
		Parser start = new Parser(fi);
		start.populateParseTree();
>>>>>>> fe04f258b7976aa0344baa072648ee65a28e36c8
	}
}
