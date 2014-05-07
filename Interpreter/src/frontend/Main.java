package frontend;

import java.io.File;
import java.io.FileNotFoundException;
import backend.SymbolTablePrinter;
import backend.TreePrinter;

public class Main {
	public static void main(String[] args) throws FileNotFoundException {
		File fi = new File("C:\\Users\\Dima\\IdeaProjects\\CS152Assignment\\Interpreter\\src\\input.lisp");
		Parser start = new Parser(fi);
		start.populateParseTree();
		System.out.println("Tree \n--------------------");
		TreePrinter p = new TreePrinter(start.getParseTree());
		p.printer();
	}
}
