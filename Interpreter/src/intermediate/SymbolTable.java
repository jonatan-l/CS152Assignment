package intermediate;

import java.util.Collection;
import java.util.Iterator;
import java.util.TreeMap;

public class SymbolTable {
    private TreeMap<String, Atom> map;
    private Pair parseTreePair;

    public SymbolTable() {
        map = new TreeMap<String, Atom>();
        parseTreePair = null;
    }
    public void setStackPtr(Pair neededPair) //Simply sets the pointer for every SymbolTable back to the root of a token pair.
    {
        parseTreePair = neededPair;
    }
    public void add(String key, Atom value) {
        map.put(key, value);
    }
    public TreeMap<String,Atom> getMap(){ return map; }
    public Atom getValue(String key) {
        return map.get(key);
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
