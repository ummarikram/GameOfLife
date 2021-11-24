import Console.Console;
import DatabaseHandler.DatabaseHandler;
import FileHandler.FileHandler;
import Graphical.Graphical;
import BusinessLayer.*;



public class Entry 
{	
    public static void main(String[] args)
    {   

        BusinessLayer Logic = new BusinessLayer(20,20);
        
        FileHandler fileHandler = new FileHandler();
        DatabaseHandler databaseHandler = new DatabaseHandler();


        Graphical GUI = new Graphical(Logic, Logic, databaseHandler);
        GUI.Display();

        // Console CUI = new Console(Logic, Logic, fileHandler);

        // Delete State
        // CUI.File_deleteState("Test");

        // Save State
        // CUI.File_saveState("Test");

        // Load State
        // CUI.File_loadState("Test");
 
        // View States
        // System.out.print(CUI.File_viewStates());
    }
}





