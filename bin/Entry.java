import Console.Console;
import Graphical.Graphical;
import BusinessLayer.*;



public class Entry 
{	
    public static void main(String[] args)
    {   

        BusinessLayer Logic = new BusinessLayer(20,20);
        
        Graphical GUI = new Graphical(Logic);
        GUI.Display();

        // Console CUI = new Console(Logic);

        // CUI.setCellState(5, 10, true);
        // CUI.setCellState(5, 11, true);
        // CUI.setCellState(5, 12, true);
        // CUI.setCellState(4, 11, true);
        // CUI.setCellState(6, 11, true);

        // CUI.StartGameLoop();
    }
}





