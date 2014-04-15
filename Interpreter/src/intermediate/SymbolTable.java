package intermediate;

import java.util.TreeMap;

public class SymbolTable
{
	TreeMap map;
	public SymbolTable(){
		map = new TreeMap<String, String>();
	}
	
	public void add(String key, String value){
		map.put(key, value);
	}
	
	public String getValue(String key){
		return (String) map.get(key);
	}
}
