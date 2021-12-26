
///////////////////////////////////////////////////////////////////////////
//AUTHOR:UMER FAROOQ
//DESCRIPTION:THIS IS FOR LOGIC LAYER CLASS IMPLEMENTATION IN WHICH WE APPLY
//RULES AS WELL AS START ,STOP,CLEAR AND RESET THE STATES 

//////////////////////////////////////////////////////////////////////////
package src.BL.LogicLayer;

import java.util.ArrayList;

import org.json.simple.JSONObject;

import src.BL.Grid.*;
import src.Interfaces.GridInterface.*;
import src.Interfaces.JSONInterface.JSONInterface;
import src.Interfaces.StateInterface.*;

public class LogicLayer implements StateInterface, GridInterface, JSONInterface {

    private Grid m_Grid;
    private Grid m_ResetGrid;
    private boolean m_isRunning;
    private int m_CurrentGeneration;

    public LogicLayer() {
        m_Grid = new Grid();
        m_isRunning = false;
        m_CurrentGeneration = 0;//counter

    }

    public JSONObject getGrid() {
        return GridTOJSON(m_Grid);
    }
//set all the Cells of grid with their defaults state and neighbours count 
    public void setGrid(JSONObject JGrid) {

        if (JGrid != null) {
            m_Grid.ChangeDimensions(JSONTOINT(JGrid, "MaxRows") , JSONTOINT(JGrid, "MaxCols"));

            for (int i = 0; i < JSONTOINT(JGrid, "MaxRows"); i++) {
                for (int j = 0; j < JSONTOINT(JGrid, "MaxCols"); j++) {

                    String Location = i + " " + j;

                    if (JGrid.get(Location) != null)
                    {
                        m_Grid.setCellState(i, j, true);
                    }
                   
                    m_Grid.calculateCellNeighbours(i, j);
                }
            }
        }
    }

    public JSONObject getRows()
    {
        return INTTOJSON(m_Grid.getRows(), "Rows");
    }

    public JSONObject getColumns()
    {
        return INTTOJSON(m_Grid.getColumns(), "Columns");
    }

    public JSONObject getCellState(JSONObject Location)
    {
        return BOOLTOJSON(m_Grid.getCellState(JSONTOINT(Location, "Rows"), JSONTOINT(Location, "Columns")), "CellState");
    }

    public void setCellState(JSONObject LocationNValue)
    {
        if (JSONTOINT(LocationNValue, "Rows") >= 0 && JSONTOINT(LocationNValue, "Columns")>= 0)
        {
            m_Grid.setCellState(JSONTOINT(LocationNValue, "Rows"), JSONTOINT(LocationNValue, "Columns"), JSONTOBOOLEAN(LocationNValue, "CellState"));
        }
    }

    public void ChangeDimensions(JSONObject Dimensions)
    {
        if (JSONTOINT(Dimensions, "Rows") >= 0 && JSONTOINT(Dimensions, "Columns")>= 0)
        {
            m_ResetGrid = null;
            m_isRunning = false;
            m_CurrentGeneration = 0;
            
            m_Grid.ChangeDimensions(JSONTOINT(Dimensions, "Rows"), JSONTOINT(Dimensions, "Columns"));
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

    public JSONObject isRunning() {
        return BOOLTOJSON(m_isRunning, "isRunning");
    }

    public JSONObject getGeneration() {
        return INTTOJSON(m_CurrentGeneration, "Generation");
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

    public JSONObject GridTOJSON(Grid grid)
    {
        /// Add code here.... e.g. put/add
        JSONObject Object = new JSONObject();

        Object.put("MaxRows", grid.getRows());
        Object.put("MaxCols", grid.getColumns());

        for (int row = 0 ; row < grid.getRows(); row++)
        {
            for (int col = 0 ; col < grid.getColumns(); col++)
            {
                if (grid.getCellState(row, col))
                {
                    String Location = row + " " + col;
                    Object.put(Location, 1);
                }
              
            }
        }

        return Object;
    }

    public Grid JSONTOGrid(JSONObject object)
    {
        int Rows = (int) object.get("MaxRows");

        int Cols = (int) object.get("MaxCols");

        Grid grid = new Grid(Rows,Cols);

        for (int row = 0 ; row < grid.getRows(); row++)
        {
            for (int col = 0 ; col < grid.getColumns(); col++)
            {
                String Location = row + " " + col;

                if (object.get(Location) != null)
                {
                        grid.setCellState(row, col, true);
                }

            }

        }

        return grid;
    }

    public JSONObject STRINGTOJSON (String string, String key )
    {
        JSONObject object = new JSONObject();
      
        
            object.put(key,string);
        

        return object;
    }

    public String JSONTOSTRING (JSONObject object, String key)
    {
     
        String string=(String) object.get(key);
        
        return string;
    }

    public JSONObject INTTOJSON (int val, String key)
    {
        JSONObject obj = new JSONObject();
        obj.put(key, val);
        return obj;

    }

    public int JSONTOINT (JSONObject object,String key)
    {
        int temp =(int) object.get(key);
        return temp;
    }

    public JSONObject BOOLTOJSON (boolean val, String key)
    {
        JSONObject obj = new JSONObject();
        obj.put(key, val);
        return obj;
    }

    public boolean JSONTOBOOLEAN (JSONObject object, String key)
    {
        boolean bool=(boolean) object.get(key);
        return bool;
    }

    public JSONObject ArraylistTOjson(ArrayList<String> arr)
    {
        JSONObject object = new JSONObject();
        
        for (int i = 0; i < arr.size(); i++) 
        {
            object.put(String.valueOf(i), arr.get(i));
              
        }
 
        return object;
    }
    
    public ArrayList<String> JsonTOarrlist(JSONObject object)
    {

        ArrayList<String> arr = new  ArrayList<String>();
    
        for (int i = 0; i < object.size(); i++) 
        {
           arr.add((String) object.get(String.valueOf(i)));
        }        

        return arr;

    }

    public  JSONObject concatjson (JSONObject obj1, String obj1key, JSONObject obj2, String obj2key)
    {
        
        JSONObject combined = new JSONObject();
        combined.put(obj1key, obj1);
        combined.put(obj2key, obj2);

       // JSONObject newobj= new JSONObject();
        //newobj.put(newkey, combined);

        return combined;
    } 


}