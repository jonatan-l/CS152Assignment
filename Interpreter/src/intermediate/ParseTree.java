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
			return;//base case closing back up
		}
		if(item.getValue().compareTo("(")== 0){
			Pair left = new Pair();//new list in left pair
			populate(left);//run on left pair
		}
		else{
			lst.add(item);//add token
		}
		populate(lst.getCdr()); //run on right pair
	}
}
