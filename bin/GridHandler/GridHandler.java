package GridHandler;

public class GridHandler {

    private int m_Speed;
    private int m_ZoomFactor;


    public GridHandler()
    {
        m_Speed = m_ZoomFactor = 0;
    }

    public void setSpeedcontrol(int Speed)
    {
        m_Speed = Speed;
    }

    public int getSpeedcontrol()
    {
        return m_Speed;
    }

    public void setZoomFactor(int ZoomFactor)
    {
        m_ZoomFactor = ZoomFactor;
    }

    public int getZoomFactor()
    {
        return m_ZoomFactor;
    }
}
