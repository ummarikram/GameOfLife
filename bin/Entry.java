import Console.Console;
import Graphical.Graphical;
import BusinessLayer.*;



public class Entry 
{	
    public static void main(String[] args)
    {   

        BusinessLayer Logic = new BusinessLayer(10,10);
        
        Graphical GUI = new Graphical(Logic);
        GUI.Display();

        // Console CUI = new Console(Logic);

        // CUI.Display();

        // Logic.getGrid().setCellState(3, 4, true);
        // Logic.getGrid().setCellState(3, 5, true);
        // Logic.getGrid().setCellState(3, 6, true);
        // Logic.getGrid().setCellState(4, 4, true);
        // Logic.getGrid().setCellState(4, 5, true);
        // Logic.getGrid().setCellState(4, 6, true);

        // CUI.Display();

        // CUI.next();

        // CUI.Display();
    
    }
}





