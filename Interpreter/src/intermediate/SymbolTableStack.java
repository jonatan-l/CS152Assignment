package intermediate;

import java.util.ArrayList;

public class SymbolTableStack
{
   private ArrayList<SymbolTable> symbolStack;

   public SymbolTableStack()
   {
       symbolStack = new ArrayList<SymbolTable>();
   }
   public void add(SymbolTable newTable)
   {
       symbolStack.add(newTable);
   }
   public SymbolTable getSymbTable(int i){
       return symbolStack.get(i);
   }
   public void remSymbTable()
   {
       symbolStack.remove(0);
   }
}
