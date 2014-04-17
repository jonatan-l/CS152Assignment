package frontend;

import intermediate.ParseTree;
import intermediate.SymbolTable;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

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

	public Parser(File f) throws FileNotFoundException {
		in = new Scanner(f);
		tree = new ParseTree();
		map = new SymbolTable();
		populateParseTree();
		
	}

	// Check if the token is one of the keywords.
	public boolean isReservedWord(String currentToken)
			throws IllegalArgumentException { // By default thinks its bad,
												// unless proven otherwise
		for (int i = 0; i < keywords.length; i++) {
			if (currentToken.compareToIgnoreCase(keywords[i]) == 0) {
				return true;
			}
		}
		return false;
	}
	
	public SymbolTable getSymbolTable() {
		return map;
	}
	
	public ParseTree getParseTree() {
		return tree;
	}

	public String isValidString(String currentToken)
			throws IllegalArgumentException {
		// A string must start with ' or start with " and end with ", it can not
		// be followed by ( ) or )(
		// Two single quotes are not allowed or one double quote is allowed. No
		// more than 2 double quoted symbols.
		if (currentToken.isEmpty()) {
			throw new IllegalArgumentException("Argument can not be Empty!");
		}

		boolean startsWithDoubleQuote = false;
		boolean startsWithSingleQuote = false;

		int numOfDoubleQuotes = 0;
		int numOfSingleQuotes = 0;

		if (currentToken.charAt(0) == '\'') {
			numOfSingleQuotes = 1;
			startsWithSingleQuote = true;
		} else if (currentToken.charAt(0) == '\"') {
			numOfDoubleQuotes = 1;
			startsWithDoubleQuote = true;
		}

		for (int i = 1; i < currentToken.length(); i++) {
			if ((!valSchemeSymbols(currentToken.charAt(i))
					&& !Character.isLetterOrDigit(currentToken.charAt(i)) && (currentToken
					.charAt(i) != '\'' && currentToken.charAt(i) != '\"'))) {
				throw new IllegalArgumentException(
						"More than 2 \" marks are not allowed, except for beginning and end\n"
								+ "2 \' are not allowed too! In addition, except for (), everything is allowed!");
			} else if (i < currentToken.length() - 1
					&& (currentToken.charAt(i) == '\"' || currentToken
							.charAt(i) == '\'')) {
				if (currentToken.charAt(i) == '\"') {
					numOfDoubleQuotes++;
				} // Increments any time a double quote is encountered
				else if (currentToken.charAt(i) == '\'') {
					numOfSingleQuotes++;
				}

				if ((startsWithSingleQuote && numOfSingleQuotes > 1 && i < currentToken
						.length() - 1)
						|| ((startsWithSingleQuote || startsWithDoubleQuote) && (numOfDoubleQuotes > 1 && i < currentToken
								.length() - 1))) {
					throw new IllegalArgumentException(
							"More than 2 \" marks are not allowed, except for beginning and end");
				}
			}
			if ((startsWithDoubleQuote == true
					&& currentToken.charAt(currentToken.length() - 1) != '\"' && (numOfDoubleQuotes > 2 || numOfSingleQuotes > 2))
					|| !startsWithSingleQuote) {
				throw new IllegalArgumentException(
						"More than 2 \" marks are not allowed, except for beginning and end"
								+ "\nA string can not have () or )(");
			}
		}
		return currentToken;
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
	public String isValidVar(String currentToken)
			throws IllegalArgumentException // /This function will be part of a
											// class
	{
		if (currentToken.length() == 0) {
			throw new IllegalArgumentException("Argument can not be Empty!");
		}

		for (int i = 0; i < currentToken.length(); i++) {
			if (((currentToken.charAt(0) >= '0' && currentToken.charAt(0) <= '9')
					|| !valSchemeSymbols(currentToken.charAt(0))
					&& !Character.isLetterOrDigit(currentToken.charAt(0)) || (currentToken
					.charAt(0) == '+' || currentToken.charAt(0) == '-' || currentToken
					.charAt(0) == '.'))
					|| ((i > 0) && ((!valSchemeSymbols(currentToken.charAt(i)) && !Character
							.isLetterOrDigit(currentToken.charAt(i))) || (!Character
							.isLetterOrDigit(currentToken.charAt(i)) && !valSchemeSymbols(currentToken
							.charAt(i)))))) {
				throw new IllegalArgumentException(
						"Variables can not start with a number!\n"
								+ "It can start with !@#$%^&*-+=./<>:~?_ or a letter!\n"
								+ "It may be followed by any number,letter or previously shown symbols!");
			}
		}
		// Save the name of a variable here!!!!!
		return currentToken;
	}

	// Check the validity of an unsigned integer, this function works
	// flawlessly!
	public String isValidUnsignedInt(String currentToken)
			throws IllegalArgumentException// Assures all digits are numbers
	{
		if (currentToken.length() == 0) {
			throw new IllegalArgumentException("Argument can not be Empty!");
		}

		for (int i = 0; i < currentToken.length(); i++) {
			if (!(currentToken.charAt(i) >= '0' && currentToken.charAt(i) <= '9')) {
				throw new IllegalArgumentException(
						"This is not an Unsigned Integer!");
			}
		}
		// save type information here: "unsigned int";
		return currentToken; // Returns the unsigned integer if it is a valid
								// unsigned integer
	}

	// Check the validity of a number, this function works flawlessly!
	public boolean isValidNumber(String currentToken)
	// throws IllegalArgumentException// Assures all digits are number and
	// . is only present once
	{/*
	 * if (currentToken.length() == 0) { throw new
	 * IllegalArgumentException("Argument can not be Empty!"); }
	 */
		// boolean foundDotAlready = false;

		for (int i = 0; i < currentToken.length(); i++) {
			if (!(currentToken.charAt(i) >= '0' && currentToken.charAt(i) <= '9')) {
				// && (currentToken.charAt(i) //== '.' && foundDotAlready)) {
				return false;
			}
			/*
			 * throw new IllegalArgumentException("This is not a number!"); }
			 * else if (currentToken.charAt(i) == '.') { foundDotAlready = true;
			 * }
			 */
		}
		return true;
		// Save token type here, in this case: "number";
		// return currentToken; // Returns the value if currentToken is indeed a
		// number
	}

	public boolean checkSpecial(String item) throws IllegalArgumentException {
		for (int i = 0; i < specialchars.length; i++) {
			if (item.compareToIgnoreCase(specialchars[i]) == 0) {
				return true;
			}
		}
		return false;
	}

	/*public static void main(String[] args) {
		Parser si = new Parser();
		System.out.println(si.isValidNumber("1232.35536"));
		System.out.println(si.isValidUnsignedInt("123235536"));
		System.out.println(si.isValidVar("*fskdgjskgjsk"));
		System.out.println(si.isReservedWord("let*"));
		System.out.println(si
				.isValidString("'!@#$%^*lgksldgjslkgjsl203596103691095015-_="));
		/*
		 * "7(bjfksdjgka" NO "(bjfksdjgka" YES "a()&%@(7407_@Q865+bdklskgsl" NO
		 * "a&%@7407_@Q865+bdklskgsl" YES
		 * "7bjfk4764749439!@#$%^&*()-+=./<>:~?_sdjgka" NO
		 * "758932854764749439!@#$%^&*()-+=./<>:~?_sdjgka" NO
		 * "kalsdjalfkj95103968103968105891053910asdfa." YES "+fskdgjskgjsk" NO
		 * "-lsfksldfksdlfkd" NO ".fksdlkgsdlgksldgk" NO "*fskdgjskgjsk" YES
		 * "'!@#$%^*lgksldgjslkgjsl203596103691095015-_=" YES
		 * "'lgksldgjslkgjsl203596103691095015" YES
		 * "'lgksldgjslkgjsl()203596103691095015" NO
		 * "''lgksldgjslkgjsl()203596103691095015" NO
		 
	}*/

	private Token identify(Token item) {
		String value = item.getValue();
		if (isReservedWord(value)) {
			item.setType("Keyword");
		} else if (checkSpecial(value)) {
			item.setType("Special Symbol");
		} else if (isValidNumber(item.getValue())) {
			item.setType("Number");
		} else {
			item.setType("Symbol");
			map.add(value, value);//add to symbol table
			
		}
		return item;

	}

	/**
	 * Scans the given file and populates the tree with 
	 */
	public void populateParseTree() {
		while (in.hasNextLine()) {
			String line = in.nextLine();
			line.replaceAll("(", " ( ");
			line.replaceAll(")", " ) ");
			String[] linearray = line.split(" ");
			for (int i = 0; i < linearray.length; i++) {
				if (linearray[i].compareTo(";") == 0) { // comment nullification
					i = linearray.length + 1;
				} else {
					// potential sysout print place
					String item = "";
					if (linearray[i].compareTo("'") == 0) {// 0
						item += "'";
						i++;// 0->1
						if (linearray[i++].compareTo("(") == 0) {// 1->2
							item += "(";
							while (linearray[i++].compareTo(")") != 0) {// 234
								item += linearray[i];
							}
							item += ")";
						} else {
							item += linearray[i];
						}
					} else {
						item = linearray[i++];
					}
					Token piece = new Token(item);
					identify(piece);//add type to token
					tree.add(piece);// add token to parsetree
				}
			}
		}
	}
}
