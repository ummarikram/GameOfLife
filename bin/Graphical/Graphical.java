package Graphical;

import UserInterface.*;
import StateHandler.*;

import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.*;

public class Graphical extends UserInterface implements MouseInputListener, ActionListener {

    private int Spacing = 2;
    private int CellSize = 20;
    private int MouseX, MouseY;
    private boolean Clicked = false;
    JFrame Frame;
    JSlider Speed;
    JButton Next;
    JSlider Zoom;

    public Graphical(StateHandler stateHandler) {

        m_stateHandler = stateHandler;

        Frame = new JFrame();
        Speed = new JSlider();
        Zoom = new JSlider();
        Next = new JButton("NEXT STATE");

        Frame.setTitle("John Conway's Game of Life");
        Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        Frame.setVisible(true);
        Frame.setResizable(false);
        Frame.setBackground(Color.BLACK);

        Frame.addMouseMotionListener(this);
        Frame.addMouseListener(this);
    }

    @Override
    public void Display() {

        Board board = new Board(Frame.getWidth(), Frame.getHeight());

        Frame.add(board);

    }

    public class Board extends JPanel {

        private int BoardX, BoardY;

        public Board(int width, int height) {
            BoardX = width;
            BoardY = height - (height / 4);

            m_stateHandler.getGrid().ChangeDimensions(BoardX / CellSize, BoardY / CellSize);
            
        }

        public void paintComponent(Graphics g) {

            g.setColor(Color.GRAY);

            for (int i = 0; i < BoardX / CellSize; i++) {
                for (int j = 0; j < BoardY / CellSize; j++) {

                    m_stateHandler.getGrid().setCellNeighbours(i, j);

                    boolean isAlive = m_stateHandler.getGrid().getCellState(i, j);

                    if (isAlive) {
                        g.setColor(Color.YELLOW);
                    } else {
                        g.setColor(Color.GRAY);
                    }

                    if (MouseX >= Spacing + i * CellSize && MouseX < Spacing + i * CellSize + CellSize - 2 * Spacing
                            && MouseY >= Spacing + j * CellSize
                            && MouseY < Spacing + j * CellSize + CellSize - 2 * Spacing) {
                        g.setColor(Color.ORANGE);

                        if (Clicked) {
                            m_stateHandler.getGrid().setCellState(i, j, !isAlive);
                            Clicked = false;

                        }
                    }

                    g.fillRect(Spacing + i * CellSize, Spacing + j * CellSize, CellSize - 2 * Spacing,
                            CellSize - 2 * Spacing);
                }
            }

           
            repaint();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

        Clicked = true;

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

        MouseX = e.getX() - 8;
        MouseY = e.getY() - 30;

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // if (e.getSource() == Next) {
        // m_stateHandler.nextState();;
        // }
    }

}
