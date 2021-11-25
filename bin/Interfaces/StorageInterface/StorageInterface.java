package bin.Interfaces.StorageInterface;
import java.util.ArrayList;

import bin.BL.Grid.*;

public interface StorageInterface {
    public ArrayList<String> viewStates();

    public Grid loadState(String name);

    public void saveState(Grid grid, String name);

    public void deleteState(String name);
}
