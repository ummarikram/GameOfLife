package Cell;

public class Cell {

    private boolean m_Alive ;
    private int m_Neighbours;

    public Cell()
    {
        m_Alive = false;
        m_Neighbours = 0;
    }
    
    public boolean isAlive()
    {
        return m_Alive;
    }

    public void setAlive(boolean state)
    {
        m_Alive = state;
    }

    public int getNeighbours()
    {
        return m_Neighbours;
    }

    public void setNeighbours(int NeighbourCount)
    {
        m_Neighbours = NeighbourCount;
    }
}