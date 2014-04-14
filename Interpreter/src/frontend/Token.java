package frontend;

public class Token {
	private String value; 
	private String type; 

	public Token() {
		value = "";
		type = "";// will be whatever value is classified as
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