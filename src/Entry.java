package src;

import src.BL.LogicLayer.*;
import src.Storage.DatabaseHandler.*;
import src.Storage.FileHandler.*;
import src.UI.Console.*;
import src.UI.Graphical.*;

public class Entry {
    public static void main(String[] args) {

        LogicLayer Logic = new LogicLayer();

        
        FileHandler fileHandler = new FileHandler(Logic);
        // DatabaseHandler databaseHandler = new DatabaseHandler(Logic);

        Graphical GUI = new Graphical(Logic, Logic, fileHandler, Logic);
        GUI.Display();

        // Console cui = new Console(Logic, Logic, fileHandler);
        // cui.Display();

    }
}
