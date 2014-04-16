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
public class Parser
{
    private String[] keywords = {"and", "begin","begin0","break-var", "case", "cond", "cycle", "define",
						"delay", "delay-list-cons", "do", "else", "extend-syntax", "for", "freeze",
						"if", "lambda", "let", "letrec", "let*", "macro", "object-maker", "or",
						"quote", "repeat", "safe-letrec", "set!", "stream-cons", "variable-case",
						"while", "wrap"};
						
	//Check if the token is one of the keywords.					
    public boolean isReservedWord(String currentToken) throws IllegalArgumentException {
        boolean isReserved = false; //By default thinks its bad, unless proven otherwise
        for (int i = 0; i < keywords.length; i++) {
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
        if(currentToken.isEmpty())
        {
            throw new IllegalArgumentException("Argument can not be Empty!");
        }

        boolean startsWithDoubleQuote = false;
        boolean startsWithSingleQuote = false;

        int numOfDoubleQuotes = 0;
        int numOfSingleQuotes = 0;

        if(currentToken.charAt(0) == '\'')
        {
            numOfSingleQuotes = 1;
            startsWithSingleQuote = true;
        }
        else if(currentToken.charAt(0) == '\"')
        {
            numOfDoubleQuotes = 1;
            startsWithDoubleQuote = true;
        }

        for(int i = 1; i < currentToken.length(); i++)
        {
            if(((!valSchemeSymbols(currentToken.charAt(i)) && !Character.isLetterOrDigit(currentToken.charAt(i))) ||
                !Character.isLetterOrDigit(currentToken.charAt(i)))||
                (i < currentToken.length() - 1 && (currentToken.charAt(i) == '\"' || currentToken.charAt(i) == '\'')))
            {
                if((startsWithSingleQuote && numOfSingleQuotes > 1 && i < currentToken.length() - 1) ||
                        ((startsWithSingleQuote || startsWithDoubleQuote) && (numOfDoubleQuotes > 1 && i < currentToken.length() - 1)))
                {
                    System.out.println("HERE---> 2: " + " i: " + i + "  currentToken.charAt(i) = " + currentToken.charAt(i));
                    throw new IllegalArgumentException("More than 2 \" marks are not allowed, except for beginning and end");
                }
                else if(currentToken.charAt(i) == '\"'){ System.out.println("HERE---> 3: " + " i: " + i + "  currentToken.charAt(i) = " +  currentToken.charAt(i)); numOfDoubleQuotes++; } //Increments any time a double quote is encountered
                else if(currentToken.charAt(i) == '\''){ System.out.println("HERE---> 4: " + " i: " + i + "  currentToken.charAt(i) = " +  currentToken.charAt(i)); numOfSingleQuotes++; }

                System.out.println("HERE---> 1: " + " i: " + i + "  currentToken.charAt(i) = " + currentToken.charAt(i));
                   throw new IllegalArgumentException("More than 2 \" marks are not allowed, except for beginning and end\n"+
                          "2 \' are not allowed too! In addition, except for (), everything is allowed!");
            }
            else if(i < currentToken.length() - 1 && (currentToken.charAt(i) == '\"' || currentToken.charAt(i) == '\''))
            {

            }

            if((startsWithDoubleQuote == true && currentToken.charAt(currentToken.length() - 1) == '\"' && numOfDoubleQuotes == 2) ||
                    (startsWithSingleQuote == true && (currentToken.charAt(currentToken.length() - 1) == '\'' ||
                     currentToken.charAt(currentToken.length() - 1) == '\"') && (numOfSingleQuotes > 1 || numOfDoubleQuotes > 1)))
            {
               return currentToken;
            }

            if((startsWithDoubleQuote == true && currentToken.charAt(currentToken.length() - 1) != '\"' && (numOfDoubleQuotes > 2 || numOfSingleQuotes > 2))||
               !startsWithSingleQuote)
            {
                System.out.println("HERE---> 5: " + " i: " + i + "  currentToken.charAt(i) = " +  currentToken.charAt(i));
                throw new IllegalArgumentException("More than 2 \" marks are not allowed, except for beginning and end"+
                        "\nA string can not have () or )(");
            }
        }
        return currentToken;
    }

    //Verifies that character c is in the valid Scheme symbol range.
    private boolean valSchemeSymbols(char c)
    {
        String validSymbs ="!@#$%^&*-+=./<>:~?_";
        boolean foundSymbol = false;
        for(int j = 0; j < validSymbs.length(); j++)
        {
            if(c == validSymbs.charAt(j))
            {
                foundSymbol = true;
            }
        }
        return foundSymbol;
    }

    //Works flawlessly!
    public String isValidVar(String currentToken) throws IllegalArgumentException ///This function will be part of a class
    {
        if(currentToken.length() == 0)
        {
            throw new IllegalArgumentException("Argument can not be Empty!");
        }

        for(int i = 0; i < currentToken.length(); i++)
        {
                if(((currentToken.charAt(0) >= '0' && currentToken.charAt(0) <= '9') ||
                        !valSchemeSymbols(currentToken.charAt(0)) &&
                        !Character.isLetterOrDigit(currentToken.charAt(0)) ||
                   (currentToken.charAt(0) == '+' || currentToken.charAt(0) == '-' || currentToken.charAt(0) == '.')) ||
                   ((i > 0) && ((!valSchemeSymbols(currentToken.charAt(i)) &&
                                 !Character.isLetterOrDigit(currentToken.charAt(i)))||
                           (!Character.isLetterOrDigit(currentToken.charAt(i))&&
                                   !valSchemeSymbols(currentToken.charAt(i)))
                  )))
            {
                   throw new IllegalArgumentException("Variables can not start with a number!\n"+
                    "It can start with !@#$%^&*-+=./<>:~?_ or a letter!\n"+
                    "It may be followed by any number,letter or previously shown symbols!");
            }
        }
        //Save the name of a variable here!!!!!
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
        Parser si = new Parser();
        System.out.println(si.isValidNumber("1232.35536"));
        System.out.println(si.isValidUnsignedInt("123235536"));
        System.out.println(si.isValidVar("*fskdgjskgjsk"));
        System.out.println(si.isReservedWord("let*"));
        System.out.println(si.isValidString("''lgksldgjslkgjsl()203596103691095015"));
        /*
          "7(bjfksdjgka" NO
          "(bjfksdjgka" YES
          "a()&%@(7407_@Q865+bdklskgsl" NO
          "a&%@7407_@Q865+bdklskgsl" YES
          "7bjfk4764749439!@#$%^&*()-+=./<>:~?_sdjgka" NO
          "758932854764749439!@#$%^&*()-+=./<>:~?_sdjgka" NO
          "kalsdjalfkj95103968103968105891053910asdfa." YES
          "+fskdgjskgjsk" NO
          "-lsfksldfksdlfkd" NO
          ".fksdlkgsdlgksldgk" NO
          "*fskdgjskgjsk" YES
          toTest:"'lgksldgjslkgjsl203596103691095015"
          "'lgksldgjslkgjsl()203596103691095015" NO
          "''lgksldgjslkgjsl()203596103691095015" NO
        */
   }
}
