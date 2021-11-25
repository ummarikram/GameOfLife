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
        
        FileHandler fileHandler = new FileHandler();
        // DatabaseHandler databaseHandler = new DatabaseHandler();


        Graphical GUI = new Graphical(Logic, Logic, fileHandler);
        GUI.Display();

       
    }
}





