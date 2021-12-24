package src.Interfaces.StateInterface;

public interface StateInterface {

   public void startState();

   public void stopState();

   public void resetState();

   public void clearState();

   public void nextState();

   public boolean isRunning();

   public int getGeneration();
}
