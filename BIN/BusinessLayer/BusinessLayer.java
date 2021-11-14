package BusinessLayer;

import Grid.Grid;
import StateHandler.StateHandler;

public class BusinessLayer
{
   private Grid m_Grid;
   private StateHandler m_StateHandler;

   public BusinessLayer(int m_row, int m_Columns)
   {
      m_Grid = new Grid(m_row, m_Columns);
      m_StateHandler = new StateHandler();
   }

   public Grid getGrid()
   {
      return m_Grid;
   }

   public void Start() 
   {
      m_StateHandler.setRunning(true);
   }

   public void Stop()
   {
      m_StateHandler.setRunning(false);
   }

   public void next() 
   {
      applyrules();
      m_StateHandler.setCounter(m_StateHandler.getCounter()+1);
   }

   public void reset() 
   {

   }

   private void applyrules() 
   {
      // Any live cell with two or three live neighbours survives.
      // Any dead cell with three live neighbours becomes a live cell.
      /// All other live cells die in the next generation. Similarly, all other dead
      // cells stay dead.
      // RULE 1
      Grid dummy = new Grid(m_Grid.getRows(), m_Grid.getColumns());
      
      for (int i = 0; i < m_Grid.getRows(); i++)
      {
         for (int j = 0; j < m_Grid.getColumns(); j++) 
         {
            // Any live cell with two or three live neighbours survives.

            if (m_Grid.getCellState(i, j) == true) 
            {
               if (m_Grid.getCellNeighbours(i, j) < 2) 
               {
                  dummy.setCellState(i, j, false);
               }
               else if (m_Grid.getCellNeighbours(i, j) > 3) 
               {
                  dummy.setCellState(i, j, false);
               }
               else
               {
                  dummy.setCellState(i, j,true); // remain same
               }

            } 
            else
            {
               if (m_Grid.getCellNeighbours(i, j) == 3) 
               {
                  dummy.setCellState(i, j, true);
               }
               else
               {
                  dummy.setCellState(i, j, false);
               }
            }
         }

      }

      // copy to original
      for (int i = 0; i < m_Grid.getRows(); i++)
      {
         for (int j = 0; j < m_Grid.getColumns(); j++) 
         {
               m_Grid.setCellState(i, j, dummy.getCellState(i, j));
         }
      }

      
   }

}