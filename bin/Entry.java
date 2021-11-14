import Graphical.Graphical;
import Console.Console;
import BusinessLayer.*;

public class Entry 
{

    public static void main(String[] args)
    {
        // Graphical graphical = new Graphical();

        BusinessLayer Logic = new BusinessLayer(20, 20);
        Console CI = new Console();
        CI.Display(Logic.getGrid());

    }
}
