package intermediate;

import java.util.ArrayList;

public class SymbolTableStack {
	private ArrayList<SymbolTable> symbolStack;
	private SymbolTable top;

	public SymbolTableStack() {
		symbolStack = new ArrayList<SymbolTable>();
		SymbolTable Level0 = new SymbolTable();
		symbolStack.add(Level0);
		top = Level0;
	}

	public void add(SymbolTable newTable) {
		symbolStack.add(newTable);
	}

	public SymbolTable getSymbTable(int i) {
		return symbolStack.get(i);
	}

	public void remSymbTable()
   {
	   top = symbolStack.get()
       symbolStack.remove(symbolStack.size());
   }

	public void newScope(){
	   SymbolTable scope = new SymbolTable()
	   symbolStack.add(scope);
	   top = scope;
	   }
}
