package STORAGE_INTERFACE;
import Grid.Grid;

public interface STORAGE_INTERFACE {
    public String viewStates();

    public Grid loadState(String name);

    public void saveState(Grid grid, String name);

    public void deleteState(String name);
}
