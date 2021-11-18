package UserInterface;

import FileHandler.FileHandler;
import GridHandler.GridHandler;
import StateHandler.StateHandler;

public abstract class UserInterface implements GridHandler {
    
    protected StateHandler m_stateHandler;
    protected FileHandler m_fileHandler;
    private int m_speed;
    private int m_zoomFactor;

    // protected DatabaseHandler 
    
    public abstract void Display();

    public void viewStates()
    {
      m_fileHandler.viewStates();
    }

    public void loadState()
    {
        m_stateHandler.setGrid(m_fileHandler.loadState());
    }

    public void saveState()
    {
      m_fileHandler.saveState(m_stateHandler.getGrid());

    }

    public void deleteState()
    {
        m_fileHandler.deleteState();
    }


    public void start()
    {
      m_stateHandler.startState();
    }

    public void stop()
    {
      m_stateHandler.stopState();
    }
 
    public void reset()
    {
      m_stateHandler.resetState();
    }
 
    public void next()
    {
      m_stateHandler.nextState();
    }
    
    protected int getGeneration()
    {
      return m_stateHandler.getGeneration();
    }

    @Override
    public void setSpeedcontrol(int Speed) {
        m_speed = Speed;
      
    }

    @Override
    public int getSpeedcontrol() {
      return m_speed;
    }

    @Override
    public void setZoomFactor(int ZoomFactor) {
      m_zoomFactor = ZoomFactor;
      
    }

    @Override
    public int getZoomFactor() {
        return m_zoomFactor;
    }
    
}
