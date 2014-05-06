package frontend;

import intermediate.ParseTree;
import intermediate.SymbolTable;
import java.util.Scanner;
import static java.lang.Character.*;
import java.io.File;
import intermediate.Pair;
import java.io.FileNotFoundException;
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
public class Parser {

	private String[] keywords = { "and", "begin", "begin0", "break-var",
			"case", "cond", "cycle", "define", "delay", "delay-list-cons",
			"do", "else", "extend-syntax", "for", "freeze", "if", "lambda",
			"let", "letrec", "let*", "macro", "object-maker", "or", "quote",
			"repeat", "safe-letrec", "set!", "stream-cons", "variable-case",
			"while", "wrap" };
	private String[] specialchars = { "(", ")", "[", "]", "{", "}", ";", ",",
			".", "\"", "'", "#", "\\" };
	private Scanner in;
	private ParseTree tree;
	private SymbolTable map;
    private Pair grab;
	public Parser(File f) throws FileNotFoundException {
		in = new Scanner(f);
		tree = new ParseTree();
		map = new SymbolTable();
        grab = null;
		populateParseTree();
	}

	// Check if the token is one of the keywords.
	public boolean isReservedWord(String currentToken,Token tok){ // By default thinks its bad, unless proven otherwise
		for (int i = 0; i < keywords.length; i++) {
			if (currentToken.compareToIgnoreCase(keywords[i]) == 0) {
                tok.setType("Keyword");
                return true;
			}
		}
        tok.setType("Undefined");
		return false;
	}

	public SymbolTable getSymbolTable() {
		return map;
	}

	public ParseTree getParseTree() {
		return tree;
	}

    public boolean isValidString(String currentToken, Token tok)
    {
        // A string must start with ' or start with " and end with ", it can not
        // be followed by ( ) or )( Two single quotes are not allowed or one double quote is allowed. No
        // more than 2 double quoted symbols.
        if (currentToken.isEmpty()) {
            tok.setType("Undefined");
            return false;
        }

        boolean startsWithDoubleQuote = false;
        boolean startsWithSingleQuote = false;

        int numOfDoubleQuotes = 0;

        if (currentToken.charAt(0) == '\'') {
            startsWithSingleQuote = true;
        } else if (currentToken.charAt(0) == '\"') {
            numOfDoubleQuotes = 1;
            startsWithDoubleQuote = true;
        }

        for (int i = 1; i < currentToken.length(); i++) {
            if ((!valSchemeSymbols(currentToken.charAt(i))
                    && !isLetterOrDigit(currentToken.charAt(i)) && (currentToken.charAt(i) != '\'' && currentToken.charAt(i) != '\"'))) {
                tok.setType("Undefined");
                return false;
            }
            else if (currentToken.charAt(i) == '\"') {
                numOfDoubleQuotes++;
            }
            if ((startsWithDoubleQuote && numOfDoubleQuotes > 1) ||
                    (!startsWithDoubleQuote && !startsWithSingleQuote && numOfDoubleQuotes >= 1))
            {
                tok.setType("Undefined");
                return false;
            }
            if((startsWithDoubleQuote == true && startsWithSingleQuote == false) && ((i < currentToken.length() - 1 && currentToken.charAt(i) == '#' && currentToken.charAt(i + 1) != '\'') ||
            i == currentToken.length() - 1 && currentToken.charAt(i) == '#'))
            {
                tok.setType("Undefined");
                return false;
            }
            if ((startsWithDoubleQuote == true && startsWithSingleQuote == false) &&
                    (currentToken.charAt(currentToken.length() - 1) != '\"' && (numOfDoubleQuotes >= 1)))
            {
                tok.setType("Undefined");
                return false;
            }
        }
        tok.setType("String");
        return true;
    }

	// Verifies that character c is in the valid Scheme symbol range.
	private boolean valSchemeSymbols(char c) {
		String validSymbs = "!@#$%^&*-+=./<>:~?_";
		boolean foundSymbol = false;
		for (int j = 0; j < validSymbs.length(); j++) {
			if (c == validSymbs.charAt(j)) {
				foundSymbol = true;
			}
		}
		return foundSymbol;
	}

	// Works flawlessly!
	public boolean isValidVar(String currentToken, Token tok)
	{
		if (currentToken.length() == 0) {
            tok.setType("Undefined");
            return false;
		}

		for (int i = 0; i < currentToken.length(); i++) {
			if (((currentToken.charAt(0) >= '0' && currentToken.charAt(0) <= '9')
					|| !valSchemeSymbols(currentToken.charAt(0))
					&& !isLetterOrDigit(currentToken.charAt(0)) || (currentToken
					.charAt(0) == '+' || currentToken.charAt(0) == '-' || currentToken
					.charAt(0) == '.'))
					|| ((i > 0) && ((!valSchemeSymbols(currentToken.charAt(i)) &&
                    !isLetterOrDigit(currentToken.charAt(i))) || (!isLetterOrDigit(currentToken.charAt(i))
                    && !valSchemeSymbols(currentToken.charAt(i)))))) {
                tok.setType("Undefined");
                return false;
			}
		}
        tok.setType("Symbol");
        return true;
	}

	// Check the validity of an unsigned integer, this function works flawlessly!
	public boolean isValidUnsignedInt(String currentToken, Token tok) // Assures all digits are numbers
	{
		if (currentToken.length() == 0) {
            tok.setType("Undefined");
            return false;
		}

        if(currentToken.charAt(0) != '-' && !(currentToken.charAt(0) >= '0' && currentToken.charAt(0) <= '9'))
        {
            return false;
        }

        for (int i = 0; i < currentToken.length(); i++)
        {
			if ((i > 0 && currentToken.charAt(i) == '-') || (!(currentToken.charAt(i) >= '0' && currentToken.charAt(i) <= '9') &&
                          currentToken.charAt(i) != '-'))
            {
                tok.setType("Undefined");
                return false;
			}
		}
		tok.setType("Unsigned Int");
		return true; // Returns true if it is a valid unsigned integer
	}

	// Check the validity of a number, this function works flawlessly!
	public boolean isValidNumber(String currentToken, Token tok) // Assures all digits are number and . is only present once
	{
	    if (currentToken.length() == 0)
        {
            tok.setType("Undefined");
            return false;
        }
		boolean foundDotAlready = false;
        if(currentToken.charAt(0) != '-' && !(currentToken.charAt(0) >= '0' && currentToken.charAt(0) <= '9'))
        {
            return false;
        }
        for (int i = 1; i < currentToken.length(); i++)
        {
			if (!(currentToken.charAt(i) >= '0' && currentToken.charAt(i) <= '9')
				&& (currentToken.charAt(i) == '.' && foundDotAlready || currentToken.charAt(i) == '-' ||
                   (currentToken.charAt(i) == '.' && i == currentToken.length() - 1)))
            {
              tok.setType("Undefined");
              return false;
            }
			else if (currentToken.charAt(i) == '.')
            {
               foundDotAlready = true;
			}
		}
        tok.setType("Number");
		return true; // Returns the true if currentToken is indeed a number
	}

	public boolean checkSpecial(String item, Token tok)
    {
		for (int i = 0; i < specialchars.length; i++) {
			if (item.compareToIgnoreCase(specialchars[i]) == 0) {
                tok.setType("Special Symbol");
				return true;
			}
		}
        tok.setType("Undefined");
        return false;
	}

	private Token identify(Token item)
    {
		String value = item.getValue();
        if(isReservedWord(value, item) ||
           checkSpecial(value, item) ||
           isValidUnsignedInt(value, item))
        {
        }
        else
        {
            isValidString(value, item);
            isValidVar(value, item);
            isValidNumber(value, item);
        }
        return item;
	}

    /**
     * Scans the given file and populates the tree with
     */
    public void populateParseTree() {
        int counter = 0;
        String key;
        while (in.hasNextLine()) {
            String line = in.nextLine();
            line = line.replaceAll("\\(", " \\( ");
            line = line.replaceAll("\\)", " \\) ");
            String[] linearray = line.split(" ");
            String item = "";
            for (int i = 0; i < linearray.length; i++) {
                if (linearray[i].compareTo(";") == 0) { // comment nullification
                    i = linearray.length + 1;
                } else if (linearray[i].length() < 1) {
                } else {
                    item = "";
                    if (linearray[i].compareTo("'") == 0) {// 0
                        item += "'";
                        i++;// 0->1
                        if (linearray[i++].compareTo("(") == 0) {// 1->2
                            item += "(";
                            while (linearray[i].compareTo(")") != 0) {// 234
                                item += linearray[i++];
                            }
                            item += ")";
                        }
                        else
                        {
                            item += linearray[i];
                        }
                    } else {
                        item = linearray[i];
                    }
                    Token piece = new Token(item);
                    identify(piece);// add type to token
                    Pair test = tree.add(piece);// add token to parsetree
                    if(piece.equals("define"))
                    {
                        counter = 2;
                    }
                    else if(counter == 1)
                    {
                        map.add(key,test);
                        counter = 0;
                    }
                    else if(counter == 2){
                    	key = item;
                    	counter == 1;
                    }
                }
            }
        }
    }
}
