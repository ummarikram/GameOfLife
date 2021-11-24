package Interfaces.GridHandler;
import Grid.Grid;

public interface GridHandler {

    public int getRows();

    public int getColumns();

    public boolean getCellState(int row, int col);

    public void setCellState(int row, int col, boolean value);

    public void ChangeDimensions(int newRow, int newCol);

    public void setGrid(Grid grid);

    public Grid getGrid();
}
