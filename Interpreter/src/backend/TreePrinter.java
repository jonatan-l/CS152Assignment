package backend;

import frontend.Token;
import intermediate.Atom;
import intermediate.Pair;
import intermediate.ParseTree;

public class TreePrinter {
	public static String print(ParseTree tree) {
		// look in left subtree
		Pair root = tree.getRoot();
		if(root.isAtom()){
		
		}
		//look in right subtree
		//close brackets
		return null;
	}
	
	private String walkTree(Pair node){
		String result = "";
		if(node.getCar() == null){
			return result;
		}
		else if(node.getCar().isAtom()){
			result += ((Token)node.getCar()).getValue();
		}
		if(node.isAtom()){
			node;
		}
		return null;
	}
}
