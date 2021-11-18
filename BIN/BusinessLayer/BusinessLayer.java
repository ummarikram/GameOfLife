package BusinessLayer;

import StateHandler.StateHandler;
import Grid.Grid;

public class BusinessLayer implements StateHandler {
   
   private Grid m_Grid;
   private boolean m_isRunning;
   private int m_CurrentGeneration;

   public BusinessLayer() {
      m_Grid = new Grid();
      m_isRunning = false;
      m_CurrentGeneration = 0;

   }

   public BusinessLayer(int rows, int columns) {
      m_Grid = new Grid(rows, columns);
      m_isRunning = false;
      m_CurrentGeneration = 0;
   }

   public Grid getGrid() {
      return m_Grid;
   }

   public void setGrid(Grid grid)
   {
      m_Grid.ChangeDimensions(grid.getRows(), grid.getColumns());    
      
      for (int i = 0; i < grid.getRows(); i++)
      {
         for (int j =0 ; j < grid.getColumns(); j++)
         {
            m_Grid.setCellState(i, j, grid.getCellState(i, j));
            m_Grid.setCellNeighbours(i, j);
         }
      }
   }

   public void startState() {
      m_isRunning = true;
  }

  public void stopState() {
      m_isRunning = false;
  }

  public void resetState() {

  }

  public void nextState() {
      m_CurrentGeneration++;
      applyrules();
  }

  public boolean isRunning() {
      return m_isRunning;
  }

  public void setRunning(boolean running_state) {
      m_isRunning = running_state;
  }

  public int getGeneration() {
      return m_CurrentGeneration;
  }

  public void setGeneration(int count) {
      m_CurrentGeneration = count;
  }

  private void applyrules() {

      Grid dummy = new Grid(m_Grid.getRows(), m_Grid.getColumns());

      for (int i = 0; i < m_Grid.getRows(); i++) {
          for (int j = 0; j < m_Grid.getColumns(); j++) {
              // Any live cell with two or three live neighbours survives.

              if (m_Grid.getCellState(i, j) == true) {
                  if (m_Grid.getCellNeighbours(i, j) < 2) {
                      dummy.setCellState(i, j, false);
                  } else if (m_Grid.getCellNeighbours(i, j) > 3) {
                      dummy.setCellState(i, j, false);
                  } else {
                      dummy.setCellState(i, j, true); // remain same
                  }

              } else {
                  if (m_Grid.getCellNeighbours(i, j) == 3) {
                      dummy.setCellState(i, j, true);
                  } else {
                      dummy.setCellState(i, j, false);
                  }
              }
          }

      }

      // copy to original
      for (int i = 0; i < m_Grid.getRows(); i++) {
          for (int j = 0; j < m_Grid.getColumns(); j++) {
              m_Grid.setCellState(i, j, dummy.getCellState(i, j));
          }
      }
  }

}