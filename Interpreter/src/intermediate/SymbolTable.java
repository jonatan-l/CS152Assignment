package intermediate;

import sun.reflect.generics.tree.Tree;

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
    public TreeMap<String,String> getMap(){ return map; }
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
