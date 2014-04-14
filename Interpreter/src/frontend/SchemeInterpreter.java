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

    public static String isValidString(String currentToken) throws IllegalArgumentException
    {
        //A string must start with ' or start with " and end with ", it can not be followed by ( ) or )(
        //Two single quotes are not allowed or one double quote is allowed. No more than 2 double quoted symbols.
        return currentToken;
    }
    //Check how well this function validates variables!
    public static String isValidVar(String currentToken) throws IllegalArgumentException//This function will be part of a class
    {
        String validSymbs ="!@#$%^&*()-+=./<>:~?";
        for(int i = 0; i < currentToken.length(); i++)
        {
            for(int j = 0; j < validSymbs.length(); j++)
            {
                if(((currentToken.charAt(0) >= '0' && currentToken.charAt(0) <= '9') &&
                   (currentToken.charAt(0) != validSymbs.charAt(j)))||
                   ((i > 0)&&(!(currentToken.charAt(i) >= '0' && currentToken.charAt(i) <= '9') ||
                                (currentToken.charAt(i) != validSymbs.charAt(j)))))
                {
                    throw new IllegalArgumentException("Variables can not start with a number!\n"+
                    "They can start with !@#$%^&*()-+=./<>:~? or a letter!\n"+
                    "It may be followed by any number,letter or previously shown symbols!");
                }
            }
        }
        return currentToken;
    }
    //Check the validity of an unsigned integer, this function works flawlessly!
    public static String isValidUnsignedInt(String currentToken) throws IllegalArgumentException//Assures all digits are numbers
    {
        if(currentToken.length() == 0)
        {
            throw new IllegalArgumentException("This is not an Unsigned Integer!");
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
    public static String isValidNumber(String currentToken) throws IllegalArgumentException//Assures all digits are number and . is only present once
    {
        if(currentToken.length() == 0)
        {
            throw new IllegalArgumentException("This is not a number!");
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
        Token tok = new Token();
        System.out.println(isValidNumber("1232.35536"));
        System.out.println(isValidUnsignedInt("123235536"));
   }
}
