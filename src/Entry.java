package src;

import org.json.simple.JSONObject;

import src.BL.Grid.Grid;
import src.BL.JsonParser.JsonParser;
import src.BL.LogicLayer.*;
import src.Storage.DatabaseHandler.*;
import src.Storage.FileHandler.*;
import src.UI.Console.*;
import src.UI.Graphical.*;

public class Entry {
    public static void main(String[] args) {

        LogicLayer Logic = new LogicLayer(10, 10);

        // JsonParser parser = new JsonParser();

        // JSONObject JGrid = parser.GridTOJSON(Logic.getGrid());

        // Grid grid = parser.JSONTOGrid(JGrid);

        // for (int i = 0; i < grid.getRows(); i++) {
        //     for (int j = 0; j < grid.getColumns(); j++) {

        //         if (grid.getCellState(i, j)) {
        //             System.out.print(" + ");
        //         } else {
        //             System.out.print(" - ");
        //         }
        //     }

        //     System.out.print("\n");
        // }

        // FileHandler fileHandler = new FileHandler();
        // DatabaseHandler databaseHandler = new DatabaseHandler();

        // Graphical GUI = new Graphical(Logic, Logic, fileHandler);
        // GUI.Display();

        // Console cui = new Console(Logic, Logic, fileHandler);
        // cui.Display();

    }
}
