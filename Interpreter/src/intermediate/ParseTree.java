package intermediate;
import frontend.Token;
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
	
	public void populate(Pair lst){
		//need method that gives next item as a token
		Token item = Parser.getToken();
		if(item.getValue().compareTo(")") == 0){
			return;
		}
		if(item.getValue().compareTo("(")== 0){
			Pair left = new Pair();
			populate(left);
		}
		else{
			lst.add(item);
		}
		populate(lst.getCdr());
	}
}
