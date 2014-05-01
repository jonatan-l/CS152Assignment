package intermediate;

import frontend.Token;

public class SchemeFunctionality
{
    public SchemeFunctionality(){}
    public Pair cdr(Pair currentRoot)
    {
        if(currentRoot == null)
        {
            return null;
        }
        if(currentRoot.getCdr().isAtom()) //Checks if an Atom is a Pair(true) pr Atom (false)
        {
            return currentRoot.getCdr();
        }
        return null;
    }
    public Atom car(Pair currentRoot)
    {
        if(currentRoot == null)
        {
            return null;
        }
        if(currentRoot.getCar().isAtom()) //Checks if an Atom is a Token (true) or Pair(false)
        {
            return currentRoot.getCar();
        }
        return null;
    }
    public int SchemeAdd(Token a,Token b)
    {

    }

}
