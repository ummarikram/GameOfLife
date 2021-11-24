package bin.UI.Graphical;

import bin.Interfaces.GridInterface.*;
import bin.Interfaces.StateInterface.*;
import bin.Interfaces.StorageInterface.*;
import bin.Interfaces.UserInterface.*;

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
            if (getCellState(x, y)) {
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
                        setCellState(this.x, this.y, false);

                    } else {
                        this.setBackground(Color.yellow);
                        setCellState(this.x, this.y, true);
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
    JButton ViewStates, LoadState, SaveState, DeleteState;
    JLabel Generation;
    JLabel L_Zoom;

    int CellSize = 30;
    Timer timer;

    ActionListener m_ActionListner = new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {

            if (e.getSource() == Next) {

                Clear.setVisible(false);
                Reset.setVisible(true);
                SaveState.setVisible(true);

                next();

                Generation.setText(Integer.toString(getGeneration()));
            }

            else if (e.getSource() == Start) {

                Start.setVisible(false);
                Next.setVisible(false);
                Clear.setVisible(false);
                Zoom.setVisible(false);
                L_Zoom.setVisible(false);
                SaveState.setVisible(false);

                Stop.setVisible(true);
                Reset.setVisible(true);

                start();

                Thread GameLoop = new Thread(new Runnable() {
                    public void run() {
                        while (isRunning()) {
                        
                            next();

                            for (int i = 0; i < getRows(); i++) {
                                for (int j = 0; j < getColumns(); j++) {
                                    m_gridCells[i][j].ChangeState();
                                }
                            }

                            Generation.setText(Integer.toString(getGeneration()));

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

                stop();

                Stop.setVisible(false);
                Start.setVisible(true);
                Next.setVisible(true);
                Zoom.setVisible(true);
                L_Zoom.setVisible(true);
                SaveState.setVisible(true);
            }

            else if (e.getSource() == Clear) {
               
                clear();
            }

            else if (e.getSource() == Reset) {

                reset();

                Generation.setText(Integer.toString(getGeneration()));

                Reset.setVisible(false);
                Stop.setVisible(false);
                Start.setVisible(true);
                Next.setVisible(true);
                Clear.setVisible(true);
                Zoom.setVisible(true);
                L_Zoom.setVisible(true);
                SaveState.setVisible(true);
            }

            for (int i = 0; i < getRows(); i++) {
                for (int j = 0; j < getColumns(); j++) {
                    m_gridCells[i][j].ChangeState();
                }
            }

        }
    };

    public Graphical(StateInterface stateHandler, GridInterface gridHandler, StorageInterface storageHandler) {

        setStateHandler(stateHandler);
        setGridHandler(gridHandler);
        setStorageHandler(storageHandler);

        Frame = new JFrame();
        Generation = new JLabel();
        Start = new JButton("START");
        Stop = new JButton("STOP");
        Next = new JButton("NEXT");
        Clear = new JButton("CLEAR");
        Reset = new JButton("RESET");
        SaveState = new JButton("SAVE STATE");
        Zoom = new JSlider(CellSize, CellSize + 20, CellSize);
        Speed = new JSlider(0, 2000, 500);
        timer = new Timer(Speed.getValue(), m_ActionListner);

        Generation.setText(Integer.toString(getGeneration()));
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

        SaveState.addActionListener(m_ActionListner);
        SaveState.setFocusable(false);

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

        L_Zoom = new JLabel("ZOOM");
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
        Controls.add(SaveState);

        Controls.add(Generation);

        timer.start();

        Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Frame.setLayout(new BorderLayout());
        Frame.setTitle("John Conway's Game of Life");
        Frame.setResizable(false);
        Frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        Frame.setVisible(true);

    }

    private void INIT_GRID() {

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double width = screenSize.getWidth();
        double height = screenSize.getHeight();

        ChangeDimensions((int) width / CellSize, (int) height / (CellSize/2));

        GridPanel.setLayout(new GridLayout(getRows(), getColumns()));

        m_gridCells = new GridCells[getRows()][getColumns()];

        for (int i = 0; i < getRows(); i++) {
            for (int j = 0; j < getColumns(); j++) {
                m_gridCells[i][j] = new GridCells(i, j);
                GridPanel.add(m_gridCells[i][j]);
            }
        }

    }

    @Override
    public void stateChanged(ChangeEvent e) {
  
        if (e.getSource() == Zoom) {

            CellSize = Zoom.getValue();

            clear();

            GridPanel.removeAll();

            this.INIT_GRID();

            GridPanel.revalidate();
            GridPanel.repaint();
        }
    }

}
