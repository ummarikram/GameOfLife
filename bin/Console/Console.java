package Console;

import UserInterface.*;
import Grid.Grid;
import StateHandler.StateHandler;

public class Console implements UserInterface {
    public static void cls()
    {
	    try
	    {	
		    new ProcessBuilder("cmd","/c","cls").inheritIO().start().waitFor();
	    }
         catch(Exception E)
    	{
			System.out.println(E);
	    }
    }
    public void Display(Grid grid,StateHandler stateHandler )
    {
        stateHandler.setRunning(true);
        //grid.setCellState(0, 0, true);
       
        grid = stateHandler.getFileHandler().loadState();
       
        while(stateHandler.isRunning())
        {

          for (int i = 0; i < grid.getRows(); i++)
            {
            for (int j = 0; j < grid.getColumns(); j++)
            {
                grid.setCellNeighbours(i, j);
                
                if (grid.getCellState(i, j) == true)
                {
                    System.out.print(" * ");
                }
                else
                {
                    System.out.print(" - ");
                }
            }

            System.out.print("\n");
         }

         try{

            Thread.sleep(1000);
          }catch(InterruptedException ex){
            //do stuff
          }

            cls();
            stateHandler.next(grid);

        }
    }
}
