package Console;

import StateHandler.StateHandler;
import UserInterface.*;

public class Console extends UserInterface {

    
    public Console(StateHandler stateHandler)
    {
      m_stateHandler = stateHandler; 
    }

    private static void cls()
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
    
    public void Display()
    {
          // cls();

          for (int i = 0; i < m_stateHandler.getGrid().getRows(); i++)
            {
            for (int j = 0; j < m_stateHandler.getGrid().getColumns(); j++)
            {
              m_stateHandler.getGrid().calculateCellNeighbours(i, j);
                
                if (m_stateHandler.getGrid().getCellState(i, j) == true)
                {
                    System.out.print(" 0 ");
                }
                else
                {
                    System.out.print(" . ");
                }
            }

            System.out.print("\n");
         }

         System.out.print("\n");
         System.out.print("\n");
    }

    
  }
