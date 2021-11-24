package bin.BL.Grid;

public class Grid {

    private Cell m_Cells[][];
    private int m_Rows;
    private int m_Columns;

    public class Cell {

        private boolean m_Alive;
        private int m_Neighbours;

        public Cell() {
            m_Alive = false;
            m_Neighbours = 0;
        }

        public boolean isAlive() {
            return m_Alive;
        }

        public void setAlive(boolean state) {
            m_Alive = state;
        }

        public int getNeighbours() {
            return m_Neighbours;
        }

        public void setNeighbours(int NeighbourCount) {
            m_Neighbours = NeighbourCount;
        }
    }

    public Grid() {
        m_Rows = 0;
        m_Columns = 0;
    }

    public Grid(int Rows, int Coloumns) {

        ChangeDimensions(Rows, Coloumns);

    }

    public void ChangeDimensions(int Rows, int Coloumns) {
        this.m_Rows = Rows;
        this.m_Columns = Coloumns;
        m_Cells = new Cell[m_Rows][m_Columns];

        for (int i = 0; i < m_Rows; i++) {
            for (int j = 0; j < m_Columns; j++) {
                m_Cells[i][j] = new Cell();
            }
        }
    }

    public void setCellState(int row, int col, boolean value) {
        if (row < m_Rows && col < m_Columns) {
            m_Cells[row][col].setAlive(value);
        }
    }

    public boolean getCellState(int row, int col) {
        if (row < m_Rows && col < m_Columns) {
            return m_Cells[row][col].isAlive();
        }

        return false;
    }

    public void calculateCellNeighbours(int row, int col) {
        if (row < m_Rows && col < m_Columns && row >= 0 && col >= 0) {
            int value = 0;

            if (col - 1 >= 0 && row - 1 >= 0) {
                if (m_Cells[row - 1][col - 1].isAlive()) {
                    value++;
                }
            }
            if (row - 1 >= 0) {
                if (m_Cells[row - 1][col].isAlive()) {
                    value++;
                }
            }

            if (row - 1 >= 0 && col + 1 < m_Columns) {
                if (m_Cells[row - 1][col + 1].isAlive()) {
                    value++;
                }
            }

            if (col - 1 >= 0) {
                if (m_Cells[row][col - 1].isAlive()) {
                    value++;
                }
            }

            if (col + 1 < m_Columns) {
                if (m_Cells[row][col + 1].isAlive()) {
                    value++;
                }
            }

            if (row + 1 < m_Rows && col - 1 >= 0) {
                if (m_Cells[row + 1][col - 1].isAlive()) {
                    value++;
                }
            }

            if (row + 1 < m_Rows) {
                if (m_Cells[row + 1][col].isAlive()) {
                    value++;
                }
            }

            if (row + 1 < m_Rows && col + 1 < m_Columns) {
                if (m_Cells[row + 1][col + 1].isAlive()) {
                    value++;
                }
            }

            m_Cells[row][col].setNeighbours(value);
        }
    }

    public void setCellNeighbours(int row, int col, int value) {
        m_Cells[row][col].setNeighbours(value);
    }

    public int getCellNeighbours(int row, int col) {
        if (row < m_Rows && col < m_Columns) {
            return m_Cells[row][col].getNeighbours();
        }

        return 0;
    }

    public int getRows() {
        return m_Rows;
    }

    public int getColumns() {
        return m_Columns;
    }

    
}