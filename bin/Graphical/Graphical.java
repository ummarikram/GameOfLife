package Graphical;

import UserInterface.*;
import Grid.Grid;
import StateHandler.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.event.MouseInputListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;


public class Graphical extends JFrame implements UserInterface, ActionListener {
    
    private JFrame Frame;
    private int Spacing = 2;
    private int CellSize = 40;
    private int MouseX,MouseY;

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

        Click Mouse = new Click();

        Frame.addMouseMotionListener(Mouse);
        Frame.addMouseListener(Mouse);
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
                    g.setColor(Color.GRAY);

                    if(MouseX>=Spacing + i * CellSize
                            && MouseX<Spacing + i * CellSize + CellSize - 2 * Spacing
                            && MouseY>=Spacing + j * CellSize
                            && MouseY<Spacing + j * CellSize + CellSize - 2 * Spacing
                    ){
                        g.setColor(Color.yellow);
                    }
                    
                    g.fillRect(Spacing+i*CellSize, Spacing+j*CellSize, CellSize-2*Spacing, CellSize-2*Spacing);
                }
            }

            repaint();
        }
    }

    public class Click implements MouseInputListener
    {

        @Override
        public void mouseClicked(MouseEvent e) {
            // TODO Auto-generated method stub
          
            System.out.println("The Mouse was Clicked");
        }

        @Override
        public void mousePressed(MouseEvent e) {
            // TODO Auto-generated method stub
           
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            // TODO Auto-generated method stub
          
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            // TODO Auto-generated method stub
           
        }

        @Override
        public void mouseExited(MouseEvent e) {
            // TODO Auto-generated method stub
           
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            // TODO Auto-generated method stub
           
        }

        @Override
        public void mouseMoved(MouseEvent e) {
            // TODO Auto-generated method stub
            MouseX = e.getX() - 8;
            MouseY = e.getY() - 30;

            System.out.println("X : " + MouseX + " Y : " + MouseY);
            
        }
        
    }

    public void Display(Grid grid, StateHandler stateHandler) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
