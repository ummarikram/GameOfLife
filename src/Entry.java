package src;

import src.BL.LogicLayer.*;
//import src.Storage.DatabaseHandler.*;
import src.Storage.FileHandler.*;
// import src.UI.Console.*;
import src.UI.Graphical.*;


public class Entry 
{	
    public static void main(String[] args)
    {   

        LogicLayer Logic = new LogicLayer();
        

        FileHandler fileHandler = new FileHandler();
        //DatabaseHandler databaseHandler = new DatabaseHandler();


        Graphical GUI = new Graphical(Logic, Logic, fileHandler);
        GUI.Display();

         //Console cui = new Console(Logic, Logic, databaseHandler);
         //cui.Display();
    
    }
}





