package StateHandler;

public class StateHandler {
    
    private boolean m_Running;
 // private DatabaseHandler m_DatabaseHandler;
    private FileHandler m_FileHandler;
    private int m_counter;

    public StateHandler()
    {
        m_Running = false;
        // m_DatabaseHandler = 
        // m_FileHandler =
        m_counter = 0;
    }
    //  public void setDataBaseHandler(DataBaseHandler DBHandler)
    //  {  
    //     m_DatabaseHandler = DBHandler;
    //  } 
    //
    //  public DataBaseHandler getDataBaseHandler()
    //  {  
    //     return m_DatabaseHandler ;
    //  } 
    //
    //  public void setFileHandler(FileHandler FHandler)
    //  {
    //    m_FileHandler = FHandler;
    //  }
    //
    //  public FileHandler getFileHandler()
    //  {
    //      return m_FileHandler;  
    //  }
    //
    public boolean isRunning()
    {
        return m_Running;
    }

    public void setRunning(boolean running_state)
    {
        m_Running = running_state;
    }

    public int getCounter()
    {
        return m_counter;
    }

    public void setCounter(int count)
    {
        m_counter = count;
    }

}
