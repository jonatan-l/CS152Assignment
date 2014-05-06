package intermediate;

import java.util.Collection;
import java.util.Iterator;
import java.util.TreeMap;

public class SymbolTable {
    private TreeMap<String, String> map;
    private Pair parseTreePair;

    public SymbolTable() {
        map = new TreeMap<String, String>();
        parseTreePair = null;
    }
    public void setStackPtr(Pair neededPair) //Simply sets the pointer for every SymbolTable back to the root of a token pair.
    {
        parseTreePair = neededPair;
    }
    public void add(String key, String value) {
        map.put(key, value);
    }
    public TreeMap<String,String> getMap(){ return map; }
    public String getValue(String key) {
        return (String) map.get(key);
    }
    public Pair getStackPtr(){ return parseTreePair; }
    public void entries() {
        // TODO Auto-generated method stub
        Collection<?> entrySet = map.entrySet();

        Iterator<?> it = entrySet.iterator();

        while (it.hasNext())
            System.out.println(it.next());
    }
}
