package frontend;
/*
    For Assignment #5, put your parser and scanner classes in the frontend package,
    and your parse tree and symbol table classes in the intermediate package.
    The backend package can contain only (for now) your tree printer and a symbol table printer classes.
    You should read the input (the Scheme source file) from standard input.
*/
/**
 * Created by Dima on 4/10/2014.
 */

/*
    identifier (name)
    number
    special symbol (+ - * / = etc.)
    reserved word
*/
public class Token
{
    private String identifier;
    private String attribute;

    public Token()
    {
        identifier = "";
        attribute = "";
    }

    boolean isValidElem(String Atom) //This function will be part of a class
    {
     /*
    Validates a scanned token by the Scanner class to be a valid Scheme element.
     */
        return false;
    }

    boolean isValidVar(/*Some arguments*/) //This function will be part of a class
    {
     /*
    Validates a scanned token by the Scanner class to be a valid Scheme variable.
     */
        return false;
    }

    //class frontend.Token has a toString method which returns a string ONLY if the token is a valid Scheme token.

}