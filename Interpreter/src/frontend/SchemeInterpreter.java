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
   public static void main(String[] args)
   {
        Token tok = new Token();
        System.out.println(tok.isValidNumber("1232.35536"));
        System.out.println(tok.isValidUnsignedInt("123235536"));
   }
}
