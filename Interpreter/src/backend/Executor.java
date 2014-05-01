package backend;
import java.lang.Character;
import frontend.Token;
import intermediate.Pair;

public class Executor
{

	public Object run(Pair p){
		
	}

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
    public Token car(Pair currentRoot)
    {
        if(currentRoot == null)
        {
            return null;
        }
        if(currentRoot.getCar().isAtom()) //Checks if an Atom is a Token (true) or Pair(false)
        {
            return (Token)currentRoot.getCar();
        }
        return null;
    }
    public int SchemeAdd(Token a, Token b)
    {
        if(a.getType().equals("Unsigned Int"))
        {

        }
    }
    public int SchemeSubtr(Token a, Token b)
    {

    }
    public int SchemeMult(Token a, Token b)
    {

    }
    public int SchemeDiv(Token a, Token b)
    {

    }
    public int SchemePow(Token a, Token b)
    {

    }

    public Atom car(Pair p){
		
		return p.getCar();
	}
	
	public Pair car(Pair p){
		return p.getCar();
	}
	
	public Pair cdr(Pair p){
		
		return p.getCdr();
	}
	
	public Pair cons(Pair p){
		
	}
	
	public list(){
		
	}
	
	public boolean testNull(Pair p){
		
		if(p == null)
			return true;
		else
			return false;
	}
	
	public String testSymbol(Pair p){
		
	}
	
		public boolean testInteger(Pair p){
		if((p.getCdr().getValue() % 1) == 0){
			return true;
		}
		else
			return false;
	}
	
	public boolean testFloat(Pair p){
		if(!(p.getCdr().getValue() % 1) != 0){
			return true;
		}
		else
			return false;
	}
	
	public boolean testBoolean(Pair p){
		if( (p.getCdr().equals("#t")) || (p.getCdr().equals("#f")) ){
			return true;
		}
		else
			return false;
	}
	
	public boolean testChar(Pair p){
		
	}
	
	public boolean testString(Pair p){
		
	}
	
	public boolean testPair(Pair p){//(a) also a pair
		if(p.getCdr() == null){
			return false;
		}
		else
			return true;
	}
	
	public int addMethod(Pair p){//use root can add as many as we need to -recursive...base case!!! cdr empty add 0
		
		if(p == null){
			return 0;
		}
		else if(!p.getCar().isAtom()){
			return add((run(p.getCar())), addMethod(p.getCdr()));//will run() return int for this?
		}
		else{
			return add(p.getCar().getValue(), addMethod(p.getCdr()));//p.getCar() is atom -integer-
		}
	}//is car is a pair - call main run on it
	
	public int subMethod(Pair p){
		
		if(p == null){
			return 0;
		}
		else if(!p.getCar().isAtom()){
			return subtract((run(p.getCar())), subMethod(p.getCdr()));
		}
		else{
			return subtract(p.getCar().getValue(), subMethod(p.getCdr()));
		}
	}
	
	public int multiMethod(Pair p){
		if(p == null){
			return 1;
		}
		else if(!p.getCar().isAtom()){
			return multiple((run(p.getCar())), multiplyMethod(p.getCdr()));
		}
		else{
			return multiple(p.getCar().getValue(), multiplyMethod(p.getCdr()));
		}
	}
	
	public boolean andMethod(Pair p){
		
		if(p.getCdr().getCar() == null){
			return true;
		}
		else if((boolean)(run(p.getCdr().getCar())) && (boolean)(run(p.getCdr().getCdr()))){
			return true;
		}
		else
			return false;
	}
	
	public boolean orMethod(Pair p){
		
		if(p.getCdr().getCar() == null){//call has no expressions
			return false;
		}
		if(p.getCar() != null && p.getCdr() == null){//end of expressions
			return (boolean) run(p.getCar());
		}
		else if((boolean) run(p.getCar()) == true && p.getCdr() != null){
			return orMethod(p.getCdr());
		}
		
		
	}

}
