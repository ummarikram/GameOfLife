package bin.Interfaces.StorageInterface;
import bin.BL.Grid.*;

public interface StorageInterface {
    public String viewStates();

    public Grid loadState(String name);

    public void saveState(Grid grid, String name);

    public void deleteState(String name);
}
