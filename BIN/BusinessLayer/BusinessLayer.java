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
   public StateHandler getStateHandler()
   {
      return m_StateHandler;
   }

   public void Start() 
   {
      m_StateHandler.setRunning(true);
   }

   public void Stop()
   {
      m_StateHandler.setRunning(false);
   }

   public void reset() 
   {

   }


}