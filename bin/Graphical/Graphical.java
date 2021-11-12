package Graphical;
import UserInterface.*;
import Grid.Grid;
import javax.swing.*;

public class Graphical extends UserInterface
{
    private JFrame Frame;

    public Graphical()
    {
        Frame = new JFrame();
        Frame.setTitle("John Conway's Game of Life");
        Frame.setSize(600, 600);
        Frame.setVisible(true);
    }

    public void Display(Grid grid)
    {
        
    }
}
