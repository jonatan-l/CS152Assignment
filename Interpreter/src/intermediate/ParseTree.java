package intermediate;
import java.util.ArrayList;

import frontend.Token;
/*
   For Assignment #5, your parse tree and symbol table classes in the intermediate package.
*/
public class ParseTree {
    private Pair root;
    private Pair end;
    private ArrayList<Pair> stack;//stack holds all the left pairs after parenthesis

    public ParseTree() {
        stack = new ArrayList<Pair>();
        root = new Pair();
        end = root;
        stack.add(end);
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

    public Pair add(Token lst)
    {
        //need method that gives next item as a token
        if(lst.getValue().compareTo("\\)") == 0){
            end = stack.remove(stack.size() - 1); //base case closing back up and resets end to last part of stack
        }
        if(lst.getValue().compareTo("\\(")== 0){
            Pair left = new Pair();//new list in left pair
            stack.add(end.add(left));//adds left pair to end, and adds right pair to stack
        }
        else{
            end = end.add(lst);//add token
        }
        return end;
    }
}
