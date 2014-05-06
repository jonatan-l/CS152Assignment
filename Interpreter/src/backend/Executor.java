package backend;

import frontend.Token;
import intermediate.Pair;
import intermediate.Atom;
import intermediate.SymbolTable;

<<<<<<< HEAD
public class Executor {

	public Object run(Pair p) {
		return p;
	}

	public Pair cdr(Pair currentRoot) {
		if (currentRoot == null) {
			return null;
		}
		if (currentRoot.getCdr().isAtom()) // Checks if an Atom is a Pair(true)
											// pr Atom (false)
		{
			return currentRoot.getCdr();
		}
		return null;
	}

	public Token car(Pair currentRoot) {
		if (currentRoot == null) {
			return null;
		}
		if (currentRoot.getCar().isAtom()) // Checks if an Atom is a Token
											// (true) or Pair(false)
		{
			return (Token) currentRoot.getCar();
		}
		return null;
	}

	public Pair cons(Pair p) {
		return null;
	}

	public boolean isList() {
		return false;
=======
public class Executor
{
	public Object run(Pair p)
    {
        if(p == null){ return null; }

        String operation = ((Token)p.getCar()).getValue();

        if(operation.equals("+"))
        {
            if(((Token)p.getCar()).getType().equals("Number"))
            {
                addNumberMethod(p.getCdr());
            }
            else if(((Token)p.getCar()).getType().equals("Unsigned Int"))
            {
                addIntegerMethod(p.getCdr());
            }
        }
        else if(operation.equals("-"))
        {
            if(((Token)p.getCar()).getType().equals("Number"))
            {
                subNumberMethod(p.getCdr());
            }
            else if(((Token)p.getCar()).getType().equals("Unsigned Int"))
            {
                subIntegerMethod(p.getCdr());
            }
        }
        else if(operation.equals("*"))
        {
            if(((Token)p.getCar()).getType().equals("Number"))
            {
                multiplyNumberMethod(p.getCdr());
            }
            else if(((Token)p.getCar()).getType().equals("Unsigned Int"))
            {
                multiplyIntegerMethod(p.getCdr());
            }
        }
        else if(operation.equals("/"))
        {
            if(((Token)p.getCar()).getType().equals("Number"))
            {
                divideNumberMethod(p.getCdr());
            }
            else if(((Token)p.getCar()).getType().equals("Unsigned Int"))
            {
                divideIntegerMethod(p.getCdr());
            }
        }
        else if(operation.equals("^"))
        {
            if(((Token)p.getCar()).getType().equals("Number"))
            {
                powNumberMethod(p.getCdr());
            }
            else if(((Token)p.getCar()).getType().equals("Unsigned Int"))
            {
                powIntegerMethod(p.getCdr());
            }
        }
        else if(operation.equals("=")) //Not Assignment, its a TEST
        {
            testEqualSymbol(p.getCdr());
        }
        else if(operation.equals("car"))
        {
            car(p.getCdr());
        }
        else if(operation.equals("cdr"))
        {
            cdr(p.getCdr());
        }
        else if(operation.equals("cons"))
        {
            cons(p.getCdr());
        }
        else if(operation.equals("list"))
        {

        }
        else if(operation.equals("null?"))
        {
            testNull(p.getCdr());
        }
        else if(operation.equals("char?"))
        {
            testChar(p.getCdr());
        }
        else if(operation.equals("integer?"))
        {
            testInteger(p.getCdr());
        }
        else if(operation.equals("string?"))
        {
            testString(p.getCdr());
        }
        else if(operation.equals("number?"))
        {
            testDouble(p.getCdr());
        }
        else if(operation.equals("boolean?"))
        {
            testBoolean(p.getCdr());
        }
        else if(operation.equals("symbol?"))
        {
            testSymbol(p.getCdr());
        }
        else if(operation.equals("list?"))
        {
            isList(p.getCdr());
        }
        else if(operation.equals("equals?"))
        {

        }
        else if(operation.equals("pair?"))
        {
            testPair(p.getCdr());
        }
        else if(operation.equals("and"))
        {
            andMethod(p.getCdr());
        }
        else if(operation.equals("or"))
        {
            orMethod(p.getCdr());
        }
		return p;
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

	public Pair cons(Pair p)
    {
        if(p == null)
        {
            //Return the final Pair of the result
        }
		else if(p.getCar().isAtom()) //Check if car of a pair is a token
        {
            if(((Token)p.getCar()).getValue().charAt(0) == '\'') //Check the definition in the symbol table
            {

            }
            else if(((Token)p.getCar()).getValue().charAt(0) != '\"' &&
                    ((Token)p.getCar()).getValue().charAt(((Token)p.getCar()).getValue().length() - 1) != '\"')
                    //if that token is naked then check its definition in the symbol table and return that definition
            {

            }
            else if(((Token)p.getCar()).getValue().charAt(0) == '\"' &&
                    ((Token)p.getCar()).getValue().charAt(((Token)p.getCar()).getValue().length() - 1) == '\"')//if that token is in double quotes then treat it as a symbol
            {

            }
        }
        else //If the car of a pair is a pair then pass it back to cons
        {
           cons(((Pair)p.getCar()));
        }
        cons(p.getCdr()); //Pass in the Cdr of pair
        return new Pair(); //return the final Pair
	}

	public boolean isList(Pair p)
    {
        if(p == null)
        {
            return false;
        }
        else if(p.getCar() != null)
        {
            return true;
        }
	    return false;
>>>>>>> 6cbbb78fc9171c55326acfec80573ff75eb4508d
	}

	public boolean testNull(Pair p) {

		if (p == null)
			return true;
		else
			return false;
	}
<<<<<<< HEAD

	public boolean testSymbol(Pair p) {
		String[] specialchars = { "(", ")", "[", "]", "{", "}", ";", ",", ".",
				"\"", "'", "#", "\\" };
		for (int i = 0; i < specialchars.length; i++) {
			if (p.getCdr().isAtom()) {
				if (((Token) ((Atom) p.getCdr())).getValue().equals(
						specialchars[i])) {
					return true;
				}
			}
=======
	
	public boolean testSymbol(Pair p)
    {
        String[] specialchars = { "(", ")", "[", "]", "{", "}", ";", ",",
                ".", "\"", "'", "#", "\\" };
        if(p == null) { return false; }
        for(int i = 0; i < specialchars.length; i++)
        {
            if(p.getCdr().isAtom() && ((Token)((Atom)p.getCdr())).getValue().equals(specialchars[i]))
            {
               return true;
            }
        }
        return false;
	}
	
	public boolean testInteger(Pair p)
    {
        if(p == null){ return false; }
		if(Integer.parseInt(((Token)((Atom)p.getCdr())).getValue()) % 1 == 0)
        {
			return true;
>>>>>>> 6cbbb78fc9171c55326acfec80573ff75eb4508d
		}
		return false;
	}

	public boolean testInteger(Pair p) {
		if (Integer.parseInt(((Token) ((Atom) p.getCdr())).getValue()) % 1 == 0) {
			return true;
		} else
			return false;
	}
<<<<<<< HEAD

	public boolean testFloat(Pair p) {
		if (Float.parseFloat(((Token) ((Atom) p.getCdr())).getValue())
				% (float) 1.0 != (float) 0.0) {
=======
	
	public boolean testDouble(Pair p){
        if(p == null)
        {
            return false;
        }
		if(Double.parseDouble(((Token)((Atom)p.getCdr())).getValue()) % (double)1.0 != (double)0.0){
>>>>>>> 6cbbb78fc9171c55326acfec80573ff75eb4508d
			return true;
		} else
			return false;
	}
<<<<<<< HEAD

	public boolean testBoolean(Pair p) {
		if (((Token) ((Atom) p.getCdr())).getValue().equals("#t")
				|| ((Token) ((Atom) p.getCdr())).getValue().equals("#f")) {
=======
	
	public boolean testBoolean(Pair p){
        if(p == null)
        {
            return false;
        }
		if( ((Token)((Atom)p.getCdr())).getValue().equals("#t")||
                ((Token)((Atom)p.getCdr())).getValue().equals("#f")){
>>>>>>> 6cbbb78fc9171c55326acfec80573ff75eb4508d
			return true;
		} else
			return false;
	}
<<<<<<< HEAD

	public boolean testChar(Pair p) {
		return false;
	}

	public boolean testString(Pair p) {
		return false;
	}

	public boolean testPair(Pair p) {// (a) also a pair
		if (p.getCdr() == null) {
			return false;
		} else
=======
	
	public boolean testChar(Pair p)
    {
        if(p == null){ return false; }
        else if(Character.isLetter(((Token)p.getCar()).getValue().charAt(0)))
        {
            return true;
        }
		return false;
	}
	
	public boolean testString(Pair p)
    {
        if(p == null){ return false; }
        for(int i = 0; i < ((Token)p.getCar()).getValue().length(); i++)
        {
            if(!Character.isLetter(((Token)p.getCar()).getValue().charAt(i)))
            {
                return false;
            }
        }
		return true;
	}
	
	public boolean testEqualSymbol(Pair p){
		
		if(p.getCdr() == null){
			return false;
		}
		else if(p.getCar() != null && p.getCdr() == null){
			return true;
		}
		else if(((Token)p.getCar()).getValue().equals(((Token)p.getCdr().getCar()).getValue())){
			return testEqualSymbol(p.getCdr());
			
		}
        return false;
	}
	
	public boolean testPair(Pair p){//(a) also a pair
		if(p == null)
        {
            return false;
        }
        else if(p.getCdr() == null){
			return false;
		}
		else
>>>>>>> 6cbbb78fc9171c55326acfec80573ff75eb4508d
			return true;
	}

	public int addIntegerMethod(Pair p) {// use root can add as many as we need
											// to -recursive...base case!!! cdr
											// empty add 0

		if (p == null) {
			return 0;
		} else if (!p.getCar().isAtom()) {
			return Integer.parseInt(run((Pair) p.getCar()).toString())
					+ addIntegerMethod(p.getCdr());// will run() return int for
													// this?
		} else {
			return Integer.parseInt(((Token) p.getCar()).getValue())
					+ addIntegerMethod(p.getCdr());// p.getCar() is atom
													// -integer-
		}
	}// is car is a pair - call main run on it

	public int subIntegerMethod(Pair p) {

		if (p == null) {
			return 0;
		} else if (!p.getCar().isAtom()) {
			return Integer.parseInt(run((Pair) p.getCar()).toString())
					- subIntegerMethod(p.getCdr());
		} else {
			return Integer.parseInt(((Token) p.getCar()).getValue())
					- subIntegerMethod(p.getCdr());
		}
	}

	public int multiplyIntegerMethod(Pair p) {
		if (p == null) {
			return 1;
		} else if (!p.getCar().isAtom()) {
			return Integer.parseInt(run((Pair) p.getCar()).toString())
					* multiplyIntegerMethod(p.getCdr());
		} else {
			return Integer.parseInt(((Token) p.getCar()).getValue())
					* multiplyIntegerMethod(p.getCdr());
		}
	}

	public float addNumberMethod(Pair p) {// use root can add as many as we need
											// to -recursive...base case!!! cdr
											// empty add 0

		if (p == null) {
			return 0;
		} else if (!p.getCar().isAtom()) {
			return Float.parseFloat(run((Pair) p.getCar()).toString())
					+ addNumberMethod(p.getCdr());// will run() return int for
													// this?
		} else {
			return Float.parseFloat(((Token) p.getCar()).getValue())
					+ addNumberMethod(p.getCdr());// p.getCar() is atom
													// -integer-
		}
	}// is car is a pair - call main run on it

	public float subNumberMethod(Pair p) {

		if (p == null) {
			return 0;
		} else if (!p.getCar().isAtom()) {
			return Float.parseFloat(run((Pair) p.getCar()).toString())
					- subNumberMethod(p.getCdr());
		} else {
			return Float.parseFloat(((Token) p.getCar()).getValue())
					- subNumberMethod(p.getCdr());
		}
	}

	public float multiplyNumberMethod(Pair p) {
		if (p == null) {
			return 1;
		} else if (!p.getCar().isAtom()) {
			return Float.parseFloat(run((Pair) p.getCar()).toString())
					* multiplyNumberMethod(p.getCdr());
		} else {
			return Float.parseFloat(((Token) p.getCar()).getValue())
					* multiplyNumberMethod(p.getCdr());
		}
	}
<<<<<<< HEAD

	public boolean andMethod(Pair p) {

		if (p.getCdr().getCar() == null) {
=======
    public int divideIntegerMethod(Pair p){
        if(p == null){
            return 1;
        }
        else if(!p.getCar().isAtom()){
            return Integer.parseInt(run((Pair)p.getCar()).toString()) / multiplyIntegerMethod(p.getCdr());
        }
        else
        {
            return Integer.parseInt(((Token)p.getCar()).getValue()) / multiplyIntegerMethod(p.getCdr());
        }
    }
    public int powIntegerMethod(Pair p){
        if(p == null){
            return 1;
        }
        else if(!p.getCar().isAtom()){
            return (int)Math.pow(Double.parseDouble(run((Pair)p.getCar()).toString()),(double)multiplyIntegerMethod(p.getCdr()));
        }
        else
        {
            return (int)Math.pow(Double.parseDouble(((Token)p.getCar()).getValue()), (double)multiplyIntegerMethod(p.getCdr()));
        }
    }
    public double addNumberMethod(Pair p){//use root can add as many as we need to -recursive...base case!!! cdr empty add 0

        if(p == null){
            return 0.0;
        }
        else if(!p.getCar().isAtom()){
            return Double.parseDouble(run((Pair)p.getCar()).toString()) + addNumberMethod(p.getCdr());//will run() return int for this?
        }
        else
        {
            return Double.parseDouble(((Token)p.getCar()).getValue()) + addNumberMethod(p.getCdr());//p.getCar() is atom -integer-
        }
    }//is car is a pair - call main run on it

    public double subNumberMethod(Pair p){

        if(p == null){
            return 0.0;
        }
        else if(!p.getCar().isAtom()){
            return Double.parseDouble(run((Pair)p.getCar()).toString()) - subNumberMethod(p.getCdr());
        }
        else
        {
            return Double.parseDouble(((Token)p.getCar()).getValue()) - subNumberMethod(p.getCdr());
        }
    }

    public double multiplyNumberMethod(Pair p){
        if(p == null){
            return 1.0;
        }
        else if(!p.getCar().isAtom()){
            return Double.parseDouble(run((Pair)p.getCar()).toString()) * multiplyNumberMethod(p.getCdr());
        }
        else
        {
            return Double.parseDouble(((Token)p.getCar()).getValue()) * multiplyNumberMethod(p.getCdr());
        }
    }
    public double divideNumberMethod(Pair p){
        if(p == null){
            return 1.0;
        }
        else if(!p.getCar().isAtom()){
            return Double.parseDouble(run((Pair)p.getCar()).toString()) / multiplyIntegerMethod(p.getCdr());
        }
        else
        {
            return Double.parseDouble(((Token)p.getCar()).getValue()) / multiplyIntegerMethod(p.getCdr());
        }
    }
    public double powNumberMethod(Pair p){
        if(p == null){
            return 1.0;
        }
        else if(!p.getCar().isAtom()){
            return Math.pow(Double.parseDouble(run((Pair)p.getCar()).toString()),multiplyIntegerMethod(p.getCdr()));
        }
        else
        {
            return Math.pow(Double.parseDouble(((Token)p.getCar()).getValue()),multiplyIntegerMethod(p.getCdr()));
        }
    }
	public boolean andMethod(Pair p){
		if(p == null){ return false; }
		else if(p.getCdr().getCar() == null){
>>>>>>> 6cbbb78fc9171c55326acfec80573ff75eb4508d
			return true;
		} else if (Boolean.parseBoolean(run(((Pair) p.getCdr().getCar()))
				.toString())
				&& Boolean.parseBoolean(run(p.getCdr().getCdr()).toString())) {
			return true;
		} else
			return false;
	}
<<<<<<< HEAD

	public boolean orMethod(Pair p) {

		if (p.getCdr().getCar() == null) {// call has no expressions
=======
	
	public boolean orMethod(Pair p){
		if(p == null){ return false; }
		else if(p.getCdr().getCar() == null){//call has no expressions
>>>>>>> 6cbbb78fc9171c55326acfec80573ff75eb4508d
			return false;
		}
		if (p.getCar() != null && p.getCdr() == null) {// end of expressions
			return Boolean.parseBoolean(run(((Pair) p.getCar())).toString());
		} else if (Boolean.parseBoolean(run(((Pair) p.getCar())).toString()) == true
				&& p.getCdr() != null) {
			return orMethod(p.getCdr());
		}
		return false;
	}
}
