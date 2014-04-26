package backend;

import intermediate.SymbolTable;
import intermediate.SymbolTable;
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
