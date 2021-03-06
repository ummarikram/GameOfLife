
///////////////////////////////////////////////////////////////////////////
//AUTHOR:UMER FAROOQ
//DESCRIPTION:THIS IS FOR LOGIC LAYER CLASS IMPLEMENTATION IN WHICH WE APPLY
//RULES AS WELL AS START ,STOP,CLEAR AND RESET THE STATES 

//////////////////////////////////////////////////////////////////////////
package src.BL.LogicLayer;

import src.BL.Grid.*;
import src.Interfaces.GridInterface.*;
import src.Interfaces.StateInterface.*;

public class LogicLayer implements StateInterface, GridInterface {

    private Grid m_Grid;
    private Grid m_ResetGrid;
    private boolean m_isRunning;
    private int m_CurrentGeneration;

    public LogicLayer() {
        m_Grid = new Grid();
        m_isRunning = false;
        m_CurrentGeneration = 0;//counter

    }

    public LogicLayer(int rows, int columns) {
        m_Grid = new Grid(rows, columns);
        m_isRunning = false;
        m_CurrentGeneration = 0;
    }

    public Grid getGrid() {
        return m_Grid;
    }
//set all the Cells of grid with their defaults state and neighbours count 
    public void setGrid(Grid grid) {

        if (grid != null) {
            m_Grid.ChangeDimensions(grid.getRows(), grid.getColumns());

            for (int i = 0; i < grid.getRows(); i++) {
                for (int j = 0; j < grid.getColumns(); j++) {
                    m_Grid.setCellState(i, j, grid.getCellState(i, j));
                    m_Grid.calculateCellNeighbours(i, j);
                }
            }
        }
    }

    public int getRows()
    {
        return m_Grid.getRows();
    }

    public int getColumns()
    {
        return m_Grid.getColumns();
    }

    public boolean getCellState(int row, int col)
    {
        return m_Grid.getCellState(row, col);
    }

    public void setCellState(int row, int col, boolean value)
    {
        if (row >= 0 && col>= 0)
        {
            m_Grid.setCellState(row, col, value);
        }
    }

    public void ChangeDimensions(int newRow, int newCol)
    {
        if (newRow >= 0 && newCol>= 0)
        {
            m_ResetGrid = null;
            m_isRunning = false;
            m_CurrentGeneration = 0;
            
            m_Grid.ChangeDimensions(newRow, newCol);
        }
    }
//During Start state m_Grid values should be copied to m_ResetGrid
    public void startState() {

        m_isRunning = true;

        if (m_ResetGrid == null) {
            m_ResetGrid = new Grid(m_Grid.getRows(), m_Grid.getColumns());

            for (int i = 0; i < m_Grid.getRows(); i++) {
                for (int j = 0; j < m_Grid.getColumns(); j++) {
                    m_ResetGrid.setCellState(i, j, m_Grid.getCellState(i, j));
                }
            }
        }
    }

    public void stopState() {
        m_isRunning = false;
    }
//when click on reset button all the main reset Grid should be copied main grid 
    public void resetState() {

        if (m_ResetGrid != null) {
            m_isRunning = false;
            m_CurrentGeneration = 0;

            m_Grid = new Grid(m_ResetGrid.getRows(), m_ResetGrid.getColumns());

            for (int i = 0; i < m_ResetGrid.getRows(); i++) {
                for (int j = 0; j < m_ResetGrid.getColumns(); j++) {
                    m_Grid.setCellState(i, j, m_ResetGrid.getCellState(i, j));
                }
            }
        }
    }
//when click on next button all the main grid values should be copied to main reset grid and then apply rules
    public void nextState() {

        for (int i = 0; i < m_Grid.getRows(); i++) {
            for (int j = 0; j < m_Grid.getColumns(); j++) {
                m_Grid.calculateCellNeighbours(i, j);
            }
        }

        if (m_ResetGrid == null) {
            m_ResetGrid = new Grid(m_Grid.getRows(), m_Grid.getColumns());

            for (int i = 0; i < m_Grid.getRows(); i++) {
                for (int j = 0; j < m_Grid.getColumns(); j++) {
                    m_ResetGrid.setCellState(i, j, m_Grid.getCellState(i, j));
                }
            }
        }

        m_CurrentGeneration++;
        applyrules();
    }
//clear the grid when we click on clear button
    public void clearState() {
        m_ResetGrid = null;
        m_isRunning = false;
        m_CurrentGeneration = 0;

        for (int i = 0; i < m_Grid.getRows(); i++) {
            for (int j = 0; j < m_Grid.getColumns(); j++) {
                m_Grid.setCellState(i, j, false);
                m_Grid.setCellNeighbours(i, j, 0);
            }
        }
    }

    public boolean isRunning() {
        return m_isRunning;
    }

    public int getGeneration() {
        return m_CurrentGeneration;
    }

    private void applyrules() {

        Grid dummy = new Grid(m_Grid.getRows(), m_Grid.getColumns());

        for (int i = 0; i < m_Grid.getRows(); i++) {
            for (int j = 0; j < m_Grid.getColumns(); j++) {
                // Any live cell with two or three live neighbours survives.

                if (m_Grid.getCellState(i, j) == true) {
                    if (m_Grid.getCellNeighbours(i, j) < 2) {
                        dummy.setCellState(i, j, false);
                    } else if (m_Grid.getCellNeighbours(i, j) > 3) {
                        dummy.setCellState(i, j, false);
                    } else {
                        dummy.setCellState(i, j, true); // remain same
                    }

                } else {
                    //if neighbours count is equal to the 3 the cell should be live 
                    if (m_Grid.getCellNeighbours(i, j) == 3) {
                        dummy.setCellState(i, j, true);
                    } else {
                        dummy.setCellState(i, j, false);
                    }
                }
            }

        }

        // copy to original m_Grid from dummy
        for (int i = 0; i < m_Grid.getRows(); i++) {
            for (int j = 0; j < m_Grid.getColumns(); j++) {
                m_Grid.setCellState(i, j, dummy.getCellState(i, j));
            }
        }
    }

}