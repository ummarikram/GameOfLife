package StateHandler;
import Grid.Grid;

public interface StateHandler {

   public void startState();

   public void stopState();

   public void resetState();

   public void clearState();

   public void nextState();

   public boolean isRunning();

   public void setRunning(boolean running_state);

   public int getGeneration();

   public void setGeneration(int count);

   public Grid getGrid();

   public void setGrid(Grid grid);
}
