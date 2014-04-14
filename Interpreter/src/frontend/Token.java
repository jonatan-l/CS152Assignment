package frontend;//////commit test again

public class Token
{
    private String name; //The name of symbol
    private String type; //The type of symbol

    public Token()
    {
        name = "";
        type = "";
    }

    public void setName(String newName){ name = newName; }
    public void setType(String newType){ type = newType; }
    public String getType(){ return type; }
    public String getName(){ return name; }
}