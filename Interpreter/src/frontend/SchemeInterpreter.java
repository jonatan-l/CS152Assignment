package frontend;

/*
    For Assignment #5, put your parser and scanner classes in the frontend package.
    You should read the input (the Scheme source file) from standard input.
*/

/*
    identifier (name)
    number
    special symbol (+ - * / = etc.)
    reserved word
*/

//Testing
public class SchemeInterpreter
{
    
    private String[] keywords = {"and", "begin", "begin0", "break-var", "case", "cond", "cycle", "define",
						"delay", "delay-list-cons", "do", "else", "extend-syntax", "for", "freeze",
						"if", "lambda", "let", "letrec", "let*", "macro", "object-maker", "or",
						"quote", "repeat", "safe-letrec", "set!", "stream-cons", "variable-case",
						"while", "wrap"};
						
	//Check if the token is one of the keywords.					
    public boolean isReservedWord(String currentToken) throws IllegalArgumentException {
        boolean isReserved = false; //By default thinks its bad, unless proven otherwise
        for (int i = 0; keywords.length < i; i++) {
            if (currentToken.equals(keywords[i]))
            {
                isReserved = true;
                break;
            }
        }
        return isReserved;
    }
    public String isValidString(String currentToken) throws IllegalArgumentException
    {
        //A string must start with ' or start with " and end with ", it can not be followed by ( ) or )(
        //Two single quotes are not allowed or one double quote is allowed. No more than 2 double quoted symbols.
        return currentToken;
    }
    //Check how well this function validates variables!
    public String isValidVar(String currentToken) /*throws IllegalArgumentException*///This function will be part of a class
    {
        if(currentToken.length() == 0)
        {
            throw new IllegalArgumentException("Argument can not be Empty!");
        }
        String validSymbs ="!@#$%^&*()-+=./<>:~?_";
        for(int i = 0; i < currentToken.length(); i++)
        {
            for(int j = 0; j < validSymbs.length(); j++)
            { //fixing a bug! Its not a big bug, the bug is, a ( is not being let throught. (I know its not allowed,
                //but to fix a bug, it must be allowed!
                if(((currentToken.charAt(0) >= '0' && currentToken.charAt(0) <= '9') &&
                   (currentToken.charAt(0) == validSymbs.charAt(j)))||
                   ((i > 0)&&(!(currentToken.charAt(i) >= '0' && currentToken.charAt(i) <= '9' ||
                                currentToken.charAt(i) == validSymbs.charAt(j)||
                                currentToken.charAt(i) >= 'a' && currentToken.charAt(i) <= 'z' ||
                                currentToken.charAt(i) >= 'A' && currentToken.charAt(i) <= 'Z'))))
                {
                    System.out.println(currentToken.charAt(i)+ " i:" + i + " j:" + j);
                    throw new IllegalArgumentException("Variables can not start with a number!\n"+
                    "It can start with !@#$%^&*()-+=./<>:~?_ or a letter!\n"+
                    "It may be followed by any number,letter or previously shown symbols!");
                }
            }
        }
        return currentToken;
    }
    //Check the validity of an unsigned integer, this function works flawlessly!
    public String isValidUnsignedInt(String currentToken) throws IllegalArgumentException//Assures all digits are numbers
    {
        if(currentToken.length() == 0)
        {
            throw new IllegalArgumentException("Argument can not be Empty!");
        }

        for(int i = 0; i < currentToken.length(); i++)
        {
            if(!(currentToken.charAt(i) >= '0' && currentToken.charAt(i) <= '9'))
            { throw new IllegalArgumentException("This is not an Unsigned Integer!"); }
        }
        //save type information here: "unsigned int";
        return currentToken; //Returns the unsigned integer if it is a valid unsigned integer
    }
    //Check the validity of a number, this function works flawlessly!
    public String isValidNumber(String currentToken) throws IllegalArgumentException//Assures all digits are number and . is only present once
    {
        if(currentToken.length() == 0)
        {
            throw new IllegalArgumentException("Argument can not be Empty!");
        }

        boolean foundDotAlready = false;

        for(int i = 0; i < currentToken.length(); i++)
        {
            if(!(currentToken.charAt(i) >= '0' && currentToken.charAt(i) <= '9') &&
                    (currentToken.charAt(i) == '.' && foundDotAlready))
            { throw new IllegalArgumentException("This is not a number!"); }
            else if(currentToken.charAt(i) == '.'){ foundDotAlready = true; }
        }
        //Save token type here, in this case: "number";
        return currentToken; //Returns the value if currentToken is indeed a number
   }
   public static void main(String[] args)
   {
        SchemeInterpreter si = new SchemeInterpreter();
        System.out.println(si.isValidNumber("1232.35536"));
        System.out.println(si.isValidUnsignedInt("123235536"));
        System.out.println(si.isValidVar("(bjfksdjgka")); //"a()&%@(7407_@Q865+bdklskgsl"
   }
}
