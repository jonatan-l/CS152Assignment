package backend;
<<<<<<< HEAD

import intermediate.SymbolTable;

=======
import intermediate.SymbolTable;
>>>>>>> fe04f258b7976aa0344baa072648ee65a28e36c8
/*
 For Assignment #5, the backend package can contain only (for now) your tree printer
 and a symbol table printer classes.
 */

public class SymbolTablePrinter {

	private SymbolTable t;

	public SymbolTablePrinter(SymbolTable table) {

		t = table;
	}

	public void printer() {

		t.entries();
	}
}
