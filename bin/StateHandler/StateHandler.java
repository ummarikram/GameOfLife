package StateHandler;
import Grid.Grid;

public interface StateHandler {

   public void startState();

   public void stopState();

   public void resetState();

   public void clearState();

   public void nextState();

   public boolean isRunning();

   public int getGeneration();

   public Grid getGrid();

   public void setGrid(Grid grid);
}
