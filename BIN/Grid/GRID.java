package Grid;
import GridHandler.GridHandler;
import Cell.Cell;

public class Grid {

    private Cell m_Cells[][];
    private int m_Rows;
    private int m_Columns;
    private GridHandler m_GridHandler;

    public Grid(int Rows, int Coloumns)
    {
        this.m_Rows=Rows;
        this.m_Columns=Coloumns;
        m_Cells = new Cell[m_Rows][m_Columns];
        m_GridHandler = new GridHandler();
    }

    public void setCellState(int row, int col, boolean value)
    {
        if (row < m_Rows && col < m_Columns)
        {
            m_Cells[row][col].setAlive(value);
        }
    }

    public boolean getCellState(int row, int col)
    {
        if (row < m_Rows && col < m_Columns)
        {
            return m_Cells[row][col].isAlive();
        }

        return false;
    }

    public void setCellNeighbours(int row, int col, int value)
    {
        if (row < m_Rows && col < m_Columns)
        {
            m_Cells[row][col].setNeighbours(value);
        }
    }

    public int getCellNeighbours(int row, int col)
    {
        if (row < m_Rows && col < m_Columns)
        {
            return m_Cells[row][col].getNeighbours();
        }

        return 0;
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
