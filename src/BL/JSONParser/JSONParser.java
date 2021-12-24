package src.BL.JsonParser;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import src.BL.Grid.Grid;
import src.BL.Grid.Grid.Cell;

public class JsonParser {

    public JSONObject GridTOJSON(Grid grid)
    {
        /// Add code here.... e.g. put/add
        JSONObject Object = new JSONObject();

        Object.put("MaxRows", grid.getRows());
        Object.put("MaxCols", grid.getColumns());

        for (int row = 0 ; row < grid.getRows(); row++)
        {
            for (int col = 0 ; col < grid.getColumns(); col++)
            {
                if (grid.getCellState(row, col))
                {
                    String Location = row + " " + col;
                    Object.put(Location, 1);
                }
              
            }
        }

        return Object;
    }

    public Grid JSONTOGrid(JSONObject object)
    {
        int Rows = (int) object.get("MaxRows");

        int Cols = (int) object.get("MaxCols");

        Grid grid = new Grid(Rows,Cols);

        for (int row = 0 ; row < grid.getRows(); row++)
        {
            for (int col = 0 ; col < grid.getColumns(); col++)
            {
                String Location = row + " " + col;

                if (object.get(Location) != null)
                {
                        grid.setCellState(row, col, true);
                }

            }

        }

        return grid;
    }

}
