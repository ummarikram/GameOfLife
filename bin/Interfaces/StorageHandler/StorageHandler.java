package bin.Interfaces.StorageHandler;
import bin.BL.Grid.*;

public interface StorageHandler {
    public String viewStates();

    public Grid loadState(String name);

    public void saveState(Grid grid, String name);

    public void deleteState(String name);
}
