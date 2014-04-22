package frontend;

import java.io.File;
import java.io.FileNotFoundException;

public class Main {
	public static void main(String[] args) throws FileNotFoundException {
		File fi = new File("C:\\Users\\Dima\\IdeaProjects\\CS152Assignment\\Interpreter\\src\\frontend\\input.lisp");
		Parser start = new Parser(fi);
		start.populateParseTree();
	}
}
