package StateHandler;
import FileHandler.FileHandler;
import Grid.Grid;

public class StateHandler {
    
    private boolean m_Running;
 // private DatabaseHandler m_DatabaseHandler;
    private FileHandler m_FileHandler;
    private int m_counter;

    public StateHandler()
    {
        m_Running = false;
        // m_DatabaseHandler = 
        m_FileHandler = new FileHandler();
        m_counter = 0;
    }
    public void next(Grid grid) 
    {
    
       applyrules(grid);
       m_counter++;
   //    m_StateHandler.setCounter(m_StateHandler.getCounter()+1);
    }
    
    
   private void applyrules(Grid m_Grid) 
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
    //  public void setDataBaseHandler(DataBaseHandler DBHandler)
    //  {  
    //     m_DatabaseHandler = DBHandler;
    //  } 
    //
    //  public DataBaseHandler getDataBaseHandler()
    //  {  
    //     return m_DatabaseHandler ;
    //  } 
    //
    //  public void setFileHandler(FileHandler FHandler)
    //  {
    //    m_FileHandler = FHandler;
    //  }
    //
      public FileHandler getFileHandler()
      {
          return m_FileHandler;  
      }
    
    public boolean isRunning()
    {
        return m_Running;
    }

    public void setRunning(boolean running_state)
    {
        m_Running = running_state;
    }

    public int getCounter()
    {
        return m_counter;
    }

    public void setCounter(int count)
    {
        m_counter = count;
    }

}
