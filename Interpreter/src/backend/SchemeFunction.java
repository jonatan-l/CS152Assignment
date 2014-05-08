package backend;
import intermediate.Pair;
import frontend.Token;
public class SchemeFunction
{
    private Pair funcRoot;
    private String objType;
    public SchemeFunction(Pair newFuncRoot)
    {
        funcRoot = newFuncRoot;
    }
    private void EvaluateParameters()
    {

    }
    Object ExecuteFunction(Pair funcRoot)
    {
        //First go through function parameters seeing if they are complex expressions
        if(funcRoot == null)
        {
            return "\nCan't Execute!\n";
        }
        else if(((Token)funcRoot.getCar()).getValue().equals("lambda"))
        {

        }
        //Then execute code using run.
        return "\nCan't Execute!\n"; //Returns the toString
    }
}
