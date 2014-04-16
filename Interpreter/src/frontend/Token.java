package frontend;

import intermediate.Atom;

public class Token implements Atom{
	private String value; 
	private String type; 

	public Token() {
		value = "";
		type = "";
	}
	
	public boolean isAtom(){
		return true;
	}

	public void setValue(String newValue) {
		value = newValue;
	}

	public void setType(String newType) {
		type = newType;
	}

	public String getType() {
		return type;
	}

	public String getValue() {
		return value;
	}
}