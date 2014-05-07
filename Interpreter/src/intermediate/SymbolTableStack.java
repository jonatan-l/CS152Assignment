package intermediate;

import java.util.ArrayList;

public class SymbolTableStack {
	private ArrayList<SymbolTable> symbolStack;
	private SymbolTable top;

	public SymbolTableStack() {
		symbolStack = new ArrayList<SymbolTable>();
		symbolStack.add((top = new SymbolTable()));
	}

	public void add(SymbolTable newTable) {
		symbolStack.add(newTable);
	}

	public SymbolTable getSymbTable(int i) {
		return symbolStack.get(i);
	}

	public void remSymbTable()
   {
       symbolStack.remove(symbolStack.size() - 1);
       top = symbolStack.get(symbolStack.size() - 1);
   }

	public void newScope(){
	   symbolStack.add((top = new SymbolTable()));
	   }
}