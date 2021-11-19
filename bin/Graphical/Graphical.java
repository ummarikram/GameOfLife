package Graphical;

import UserInterface.*;
import StateHandler.*;

import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;

import java.awt.event.*;

public class Graphical extends UserInterface implements ChangeListener {

    public class GridCells extends JButton implements ActionListener {

        int x, y;

        public GridCells(int x, int y) {
            
            this.setBackground(Color.lightGray);
            this.addActionListener(this);
            this.x = x;
            this.y = y;
        }

        public void ChangeState() {
            if (m_stateHandler.getGrid().getCellState(x, y)) {
                this.setBackground(Color.yellow);
            } else {
                this.setBackground(Color.lightGray);
            }
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == this) {

                if (!this.getModel().isPressed()) {

                    if (this.getBackground() == Color.yellow) {
                        this.setBackground(Color.lightGray);
                        m_stateHandler.getGrid().setCellState(this.x, this.y, false);

                    } else {
                        this.setBackground(Color.yellow);
                        m_stateHandler.getGrid().setCellState(this.x, this.y, true);
                    }

                }
            }
        }
    }

    JFrame Frame;
    JSlider Zoom, Speed;
    JPanel GridPanel;
    GridCells[][] m_gridCells;
    JButton Start, Stop, Reset, Next, Clear;
    JLabel Generation;

    int CellSize = 40;
    int rows;
    int cols;

    Timer timer;

    ActionListener m_ActionListner = new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {

            if (e.getSource() == Next) {

                for (int i = 0; i < rows; i++) {
                    for (int j = 0; j < cols; j++) {
                        m_stateHandler.getGrid().calculateCellNeighbours(i, j);
                    }
                }

                m_stateHandler.nextState();

                for (int i = 0; i < rows; i++) {
                    for (int j = 0; j < cols; j++) {
                        m_gridCells[i][j].ChangeState();
                    }
                }

                Generation.setText(Integer.toString(m_stateHandler.getGeneration()));
            }

            else if (e.getSource() == Start) {

                Start.setVisible(false);
                Next.setVisible(false);
                Clear.setVisible(false);

                Stop.setVisible(true);
                Reset.setVisible(true);

                m_stateHandler.setRunning(true);

                Thread GameLoop = new Thread(new Runnable() {
                    public void run() {
                        while (m_stateHandler.isRunning()) {
                            for (int i = 0; i < rows; i++) {
                                for (int j = 0; j < cols; j++) {
                                    m_stateHandler.getGrid().calculateCellNeighbours(i, j);
                                }
                            }

                            m_stateHandler.nextState();

                            for (int i = 0; i < rows; i++) {
                                for (int j = 0; j < cols; j++) {
                                    m_gridCells[i][j].ChangeState();
                                }
                            }

                            Generation.setText(Integer.toString(m_stateHandler.getGeneration()));

                            try {
                                Thread.sleep(Speed.getValue());
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });

                GameLoop.start();

            }

            else if (e.getSource() == Stop) {

                m_stateHandler.setRunning(false);

                Start.setVisible(true);

                Stop.setVisible(false);

                Next.setVisible(true);

            }

            else if (e.getSource() == Clear) {
                m_stateHandler.setGeneration(0);

                for (int i = 0; i < rows; i++) {
                    for (int j = 0; j < cols; j++) {
                        m_stateHandler.getGrid().setCellState(i, j, false);
                        m_stateHandler.getGrid().setCellNeighbours(i, j, 0);
                        m_gridCells[i][j].ChangeState();
                    }
                }
            }

            else if (e.getSource() == Reset) {
                // Reset.setVisible(false);
                // Clear.setVisible(false);
            }

        }
    };

    public Graphical(StateHandler stateHandler) {

        m_stateHandler = stateHandler;

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double width = screenSize.getWidth();
        double height = screenSize.getHeight();

        rows = (int) width / CellSize;
        cols = (int) height / CellSize;

        Frame = new JFrame();
        Generation = new JLabel();
        Start = new JButton("START");
        Stop = new JButton("STOP");
        Next = new JButton("NEXT");
        Clear = new JButton("CLEAR");
        Reset = new JButton("RESET");
        Zoom = new JSlider(10, 50, 40);
        Speed = new JSlider(0, 2000, 500);
        timer = new Timer(Speed.getValue(), m_ActionListner);

        Generation.setText(Integer.toString(m_stateHandler.getGeneration()));
        Generation.setFont(new Font("Consolas", Font.PLAIN, 20));
        Generation.setForeground(Color.WHITE);

        Start.addActionListener(m_ActionListner);
        Start.setFocusable(false);

        Stop.addActionListener(m_ActionListner);
        Stop.setFocusable(false);
        Stop.setVisible(false);

        Next.addActionListener(m_ActionListner);
        Next.setFocusable(false);

        Clear.addActionListener(m_ActionListner);
        Clear.setFocusable(false);

        Reset.addActionListener(m_ActionListner);
        Reset.setFocusable(false);
        Reset.setVisible(false);

        Zoom.addChangeListener(this);
        Speed.addChangeListener(this);

        Speed.setPreferredSize(new Dimension(100, 50));
        Speed.addChangeListener(this);
        Speed.setBackground(Color.black);

        Zoom.setPreferredSize(new Dimension(100, 50));
        Zoom.addChangeListener(this);
        Zoom.setBackground(Color.black);

        Display();
    }

    public void Display() {
        Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        Frame.setLayout(new BorderLayout());
        Frame.setTitle("John Conway's Game of Life");
        Frame.setResizable(false);

        JPanel Top = new JPanel();
        JPanel Controls = new JPanel();
        JPanel GridContainer = new JPanel();
        GridPanel = new JPanel();

        Top.setPreferredSize(new Dimension(100, 100));
        Controls.setPreferredSize(new Dimension(100, 100));
        GridContainer.setPreferredSize(new Dimension(100, 100));
        GridPanel.setPreferredSize(new Dimension(100, 100));

        Top.setBackground(Color.black);
        Controls.setBackground(Color.black);
        GridContainer.setBackground(Color.black);
        GridPanel.setBackground(Color.black);

        GridContainer.setLayout(new BorderLayout());

        Frame.add(Top, BorderLayout.NORTH);
        Frame.add(Controls, BorderLayout.SOUTH);
        Frame.add(GridContainer, BorderLayout.CENTER);
        GridContainer.add(GridPanel, BorderLayout.CENTER);

        this.INIT_GRID();

        JLabel L_GameName = new JLabel("JOHN CONWAY'S GAME OF LIFE");
        L_GameName.setFont(new Font("Consolas", Font.PLAIN, 40));
        L_GameName.setForeground(Color.WHITE);

        JLabel L_Delay = new JLabel("DELAY");
        L_Delay.setFont(new Font("Consolas", Font.PLAIN, 14));
        L_Delay.setForeground(Color.WHITE);

        JLabel L_Zoom = new JLabel("ZOOM");
        L_Zoom.setFont(new Font("Consolas", Font.PLAIN, 14));
        L_Zoom.setForeground(Color.WHITE);

        Top.add(L_GameName);
        Controls.add(L_Delay);
        Controls.add(Speed);
        Controls.add(L_Zoom);
        Controls.add(Zoom);
        Controls.add(Start);
        Controls.add(Stop);
        Controls.add(Next);
        Controls.add(Reset);
        Controls.add(Clear);

        Controls.add(Generation);

        Frame.setVisible(true);

        timer.start();

    }


    public void INIT_GRID() {

        m_stateHandler.getGrid().ChangeDimensions(rows, cols);
        GridPanel.setLayout(new GridLayout(rows, cols));

        m_gridCells = new GridCells[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                m_gridCells[i][j] = new GridCells(i, j);
                GridPanel.add(m_gridCells[i][j]);
            }
        }

    }

    @Override
    public void stateChanged(ChangeEvent e) {
        // TODO Auto-generated method stub

        if (e.getSource() == Zoom) {
            CellSize = Zoom.getValue();

            // Readjust Grid
        }

    }

  
}
