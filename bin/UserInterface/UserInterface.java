package UserInterface;
import Grid.Grid;
import StateHandler.StateHandler;

public interface UserInterface {

    // protected EventHandler m_EventHandler; 

    public abstract void Display(Grid grid,StateHandler stateHandler );

}
