package backend;

import frontend.Token;
import intermediate.Pair;
import intermediate.ParseTree;

public class TreePrinter {

    private ParseTree t;
    public TreePrinter(ParseTree tree){
        t = tree;
    }

    public void print(Pair t){
        if(t.getCar() == null){
            System.out.println(")");
            System.out.println();
        }
        else if(!t.getCar().isAtom()){//false ---- ! or not
            System.out.println("(");
            print((Pair) t.getCar());//manual type change?
            print(t.getCdr());
        }
        else if(t.getCar().isAtom()){//true
            System.out.print(((Token)t.getCar()).getValue() + " ");
            print(t.getCdr());
        }

    }

    //create TreePrinter with the Parse tree then just call printer()
    public void printer(){
        print(t.getRoot());
    }
}