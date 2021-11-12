package STORAGE_INTERFACE;
import Grid.Grid;

public interface STORAGE_INTERFACE {
    public void viewStates();

    public Grid loadState();

    public void saveState(Grid grid);

    public void deleteState();
}
