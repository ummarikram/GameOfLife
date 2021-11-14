package Graphical;

import UserInterface.*;
import Grid.Grid;
import java.awt.*;
import javax.swing.*;
import java.util.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Graphical extends JFrame implements UserInterface, ActionListener {
    
    private JFrame Frame;
    private int Spacing = 2;
    private int CellSize = 40;

    public Graphical() {

        Frame = new JFrame();
        Frame.setTitle("John Conway's Game of Life");
        Frame.setDefaultCloseOperation(Frame.EXIT_ON_CLOSE);
        Frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
        Frame.setVisible(true);
        Frame.setResizable(false);
        Frame.setBackground(Color.BLACK);

        Board board = new Board();
        Frame.setContentPane(board);
    }

    public class Board extends JPanel{

        public void paintComponent(Graphics g)
        {
            int BoardX = Frame.getWidth();
            int BoardY = Frame.getHeight()-(Frame.getHeight()/4);

            g.setColor(Color.DARK_GRAY);
            g.fillRect(0, 0, BoardX, BoardY);
            g.setColor(Color.GRAY);


            for (int i = 0; i < BoardX/CellSize; i++)
            {
                for (int j = 0; j < BoardY/CellSize; j++)
                {
                    g.fillRect(Spacing+i*CellSize, Spacing+j*CellSize, CellSize-2*Spacing, CellSize-2*Spacing);
                }
            }
        }
    }

    

    public void Display(Grid grid) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
