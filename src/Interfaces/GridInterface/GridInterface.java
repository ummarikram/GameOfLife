package src.Interfaces.GridInterface;
import org.json.simple.JSONObject;

public interface GridInterface {

    public JSONObject getRows();

    public JSONObject getColumns();

    public JSONObject getCellState(JSONObject Location);

    public void setCellState(JSONObject LocationNValue);

    public void ChangeDimensions(JSONObject Dimensions);

    public void setGrid(JSONObject grid);

    public JSONObject getGrid();
}
