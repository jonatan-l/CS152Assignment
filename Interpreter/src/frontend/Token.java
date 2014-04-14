package frontend;

public class Token
{
    private String value;
    private String type;

    public Token()
    {
        value = "";
        type = "";
    }

    public boolean isValidVar(String currentToken) //This function will be part of a class
    {

        return false;
    }

    public boolean isValidUnsignedInt(String currentToken) //Assures all digits are numbers
    {
        if(currentToken.length() == 0){return false; }

        for(int i = 0; i < currentToken.length(); i++)
        {
            if(!(currentToken.charAt(i) >= '0' && currentToken.charAt(i) <= '9')){ return false; }
        }

        type = "unsigned int";
        return true;
    }

    public boolean isValidNumber(String currentToken) //Assures all digits are number and . is only present once
    {
        if(currentToken.length() == 0){return false; }

        boolean foundDotAlready = false;

        for(int i = 0; i < currentToken.length(); i++)
        {
            if(!(currentToken.charAt(i) >= '0' && currentToken.charAt(i) <= '9')||
                (currentToken.charAt(i) == '.' && foundDotAlready))
            { return false; }
            else if(currentToken.charAt(i) == '.'){ i++; foundDotAlready = true; }
        }
        type = "number";
        return true;
    }
}