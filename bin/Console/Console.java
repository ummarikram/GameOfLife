package Console;

import UserInterface.*;
import Grid.Grid;

public class Console extends UserInterface {
    
    public void Display(Grid grid)
    {
        for (int i = 0; i < grid.getRows(); i++)
        {
            for (int j = 0; j < grid.getColumns(); j++)
            {
                if (grid.getCellState(i, j) == true)
                {
                    System.out.println(" + ");
                }
                else
                {
                    System.out.println(" - ");
                }
            }

            System.out.println("\n");
        }
    }
}
