package intermediate;
/*
   For Assignment #5, your parse tree and symbol table classes in the intermediate package.
*/
public class ParseTree {
	private Pair root;
	private Pair end;
	
	public ParseTree() {
		root = new Pair();
		end = root;
	}
	
	public Pair getRoot() {
		return root;
	}
	
	public Pair getLast(){
		return end;
	}
	
	public void add(Atom item){
		end = end.add(item);
	}
}
