package bin.Interfaces.StateHandler;

public interface StateHandler {

   public void startState();

   public void stopState();

   public void resetState();

   public void clearState();

   public void nextState();

   public boolean isRunning();

   public int getGeneration();
}
