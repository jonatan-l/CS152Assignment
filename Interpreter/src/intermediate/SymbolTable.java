package intermediate;
<<<<<<< HEAD

=======
>>>>>>> fe04f258b7976aa0344baa072648ee65a28e36c8
import java.util.Collection;
import java.util.Iterator;
import java.util.TreeMap;

public class SymbolTable {
	TreeMap<String, String> map;

	public SymbolTable() {
		map = new TreeMap<String, String>();
	}

	public void add(String key, String value) {
		map.put(key, value);
	}

	public String getValue(String key) {
		return (String) map.get(key);
	}

	public void entries() {
		// TODO Auto-generated method stub
		Collection<?> entrySet = map.entrySet();

		Iterator<?> it = entrySet.iterator();

		while (it.hasNext())
			System.out.println(it.next());
	}
}
