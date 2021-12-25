package src.BL.JSONParser;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import src.BL.Grid.Grid;
import src.BL.Grid.Grid.Cell;

import java.util.ArrayList;

public class JsonParser {

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
        int temp =(int) object.get("int");
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
