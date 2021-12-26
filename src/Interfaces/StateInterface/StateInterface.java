package src.Interfaces.StateInterface;

import org.json.simple.JSONObject;

public interface StateInterface {

   public void startState();

   public void stopState();

   public void resetState();

   public void clearState();

   public void nextState();

   public JSONObject isRunning();

   public JSONObject getGeneration();
}
