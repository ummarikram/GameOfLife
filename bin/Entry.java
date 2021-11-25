package bin;

import bin.UI.Console.*;
import bin.Storage.FileHandler.*;
import bin.Storage.DatabaseHandler.*;
import bin.UI.Graphical.*;
import bin.BL.LogicLayer.*;


public class Entry 
{	
    public static void main(String[] args)
    {   

        LogicLayer Logic = new LogicLayer(20,20);
        
        // FileHandler fileHandler = new FileHandler();
        DatabaseHandler databaseHandler = new DatabaseHandler();


        // Graphical GUI = new Graphical(Logic, Logic, databaseHandler);
        // GUI.Display();

        Console CUI = new Console(Logic, Logic, databaseHandler);

        // Delete State
       //CUI.deleteState("Test");
       //CUI.deleteState("temp");
        // Save State
        //CUI.setCellState(5, 5, true);
        //CUI.setCellState(4, 4, true);
       //CUI.saveState("Test");

        // Load State
      //  CUI.loadState("Test");
      CUI.Display();
      CUI.loadState("Test");
      CUI.Display();
 
        // View States
      // System.out.print(CUI.viewStates());
    }
}





