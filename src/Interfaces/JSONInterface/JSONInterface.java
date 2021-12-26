package src.Interfaces.JSONInterface;

import java.util.ArrayList;

import org.json.simple.JSONObject;

import src.BL.Grid.Grid;

public interface JSONInterface {

    public JSONObject GridTOJSON(Grid grid);

    public Grid JSONTOGrid(JSONObject object);

    public JSONObject STRINGTOJSON (String string, String key );

    public String JSONTOSTRING (JSONObject object, String key);

    public JSONObject INTTOJSON (int val, String key);

    public int JSONTOINT (JSONObject object,String key);

    public JSONObject BOOLTOJSON (boolean val, String key);

    public boolean JSONTOBOOLEAN (JSONObject object, String key);

    public JSONObject ArraylistTOjson(ArrayList<String> arr);
    
    public ArrayList<String> JsonTOarrlist(JSONObject object);

    public  JSONObject concatjson (JSONObject obj1, String obj1key, JSONObject obj2, String obj2key);
}
