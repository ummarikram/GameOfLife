package Grid;
import GridHandler.GridHandler;
import Cell.Cell;

public class Grid {

    Cell m_Cells[][];
    int m_Rows;
    int m_Columns;
    GridHandler m_GridHandler;

    public Grid(int Rows, int Coloumns)
    {
        this.m_Rows=Rows;
        this.m_Columns=Coloumns;
    }
    
    public Cell[][] getGrid()
    {
        return m_Cells;
    }

    public int getRows()
    {
        return m_Rows;
    }

    public int getColumns()
    {
        return m_Columns;
    }

    public GridHandler GridHandler()
    {
        return m_GridHandler;
    }
}
