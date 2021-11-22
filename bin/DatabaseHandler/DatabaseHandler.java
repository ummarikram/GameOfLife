package DatabaseHandler;
import Grid.Grid;
import STORAGE_INTERFACE.STORAGE_INTERFACE;

public class DatabaseHandler implements STORAGE_INTERFACE {

    @Override
    public String viewStates() {
        // TODO Auto-generated method stub

        String StateNames = "";

        // Query

        // while (rs.next())
        // {
        //     StateNames = StateNames + rs.getString(1) + "\n";
        // }

     
        return null;
    }

    @Override
    public Grid loadState(String name) {
        // TODO Auto-generated method stub
        
        // int rows = 0, cols = 0;

        // select top(1) Grid.row
        // from Grid
        // order by desc Grid.row

    //    Grid Temp = new Grid(rows,cols);

        // write query here
    //    while (rs.next())
    //    {
    //        //Temp.setCellState(rs.getInt(2), rs.getInt(3), rs.getBoolan(4));
    //    }
        
        
        return null;
    }

    @Override
    public void saveState(Grid grid, String name) {
        // TODO Auto-generated method stub

        for (int row = 0; row < grid.getRows(); row++)
        {
            for (int col = 0; col < grid.getColumns(); col++)
            {
                Boolean Alive = grid.getCellState(row, col);

                // Run query here...
            }
        }
        
    }

    @Override
    public void deleteState(String name) {
        // TODO Auto-generated method stub
        
    }
    
}
