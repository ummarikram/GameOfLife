package src.Interfaces.StorageInterface;

import org.json.simple.JSONObject;

public interface StorageInterface {

    public JSONObject viewStates();

    public JSONObject loadState(JSONObject name);

    public void saveState(JSONObject state);

    public void deleteState(JSONObject name);
}
