package intermediate;

import java.util.ArrayList;

public class SymbolTableStack
{
   private ArrayList<SymbolTable> symbolStack;
   private Pair parseTreePair;
   public SymbolTableStack()
   {
       symbolStack = new ArrayList<SymbolTable>();
       parseTreePair = null;
   }
   public void add(SymbolTable newTable)
   {
       symbolStack.add(newTable);
   }
   public void setStackPtr(Pair neededPair)
   {
        /*
        * Find the current token, then set parseTreePair to it!
        * */
   }
   public Pair getStackPtr(){ return parseTreePair; }
   public SymbolTable getSymbTable(int i)
   {
       return symbolStack.get(i);
   }
   public void remSymbTable(int i)
   {
       symbolStack.remove(i);
   }
}
