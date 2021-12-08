package bin.UI.Graphical;

import bin.Interfaces.GridInterface.*;
import bin.Interfaces.StateInterface.*;
import bin.Interfaces.StorageInterface.*;
import bin.Interfaces.UserInterface.*;

import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;

import java.awt.event.*;
import java.util.ArrayList;

public class Graphical extends UserInterface implements ChangeListener {

    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    double width = screenSize.getWidth();
    double height = screenSize.getHeight();

    private class AllStates extends JPanel implements ActionListener {

        private JLabel m_StateName;
        private JButton m_LoadState;
        private JButton m_DeleteState;

        public AllStates(String StateName) {

            m_StateName = new JLabel(StateName);
            m_StateName.setFont(new Font("Consolas", Font.PLAIN, 14));
            m_StateName.setForeground(Color.WHITE);

            m_LoadState = new JButton("LOAD STATE");
            m_DeleteState = new JButton("DELETE STATE");
            m_LoadState.addActionListener(this);
            m_LoadState.setFocusable(false);
            m_DeleteState.addActionListener(this);
            m_DeleteState.setFocusable(false);

            this.add(m_StateName);
            this.add(m_LoadState);
            this.add(m_DeleteState);

            this.setBackground(Color.BLACK);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == m_LoadState) {

                loadState(m_StateName.getText());

                GridPanel.removeAll();

                INIT_GRID();

                GridPanel.revalidate();
                GridPanel.repaint();

                Generation.setText(Integer.toString(getGeneration()));
            }

            else if (e.getSource() == m_DeleteState) {
                deleteState(m_StateName.getText());

                this.removeAll();
                this.repaint();
                this.revalidate();
            }

        }

    }

    private class GridCells extends JButton implements ActionListener {

        private int x, y;

        public GridCells(int x, int y) {

            this.x = x;
            this.y = y;
            this.ChangeState();
            this.addActionListener(this);

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

    ActionListener m_ActionListner = new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {

            if (e.getSource() == Next) {

                Clear.setVisible(false);
                Reset.setVisible(true);

                next();
            }

            else if (e.getSource() == Start) {

                Start.setVisible(false);
                Next.setVisible(false);
                Clear.setVisible(false);
                Zoom.setVisible(false);
                L_Zoom.setVisible(false);

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
            }

            else if (e.getSource() == Clear) {

                clear();
            }

            else if (e.getSource() == Reset) {

                reset();

                Reset.setVisible(false);
                Stop.setVisible(false);
                Start.setVisible(true);
                Next.setVisible(true);
                Clear.setVisible(true);
                Zoom.setVisible(true);
                L_Zoom.setVisible(true);
            }

            else if (e.getSource() == SaveState) {

                SaveState.setVisible(false);
                ViewStates.setVisible(false);

                JFrame Saving = new JFrame();
                JButton Submit = new JButton("SUBMIT");
                JTextField StateName = new JTextField();

                Saving.addWindowListener(new java.awt.event.WindowAdapter() {
                    public void windowClosing(WindowEvent e) {

                        SaveState.setVisible(true);
                        ViewStates.setVisible(true);

                        Saving.dispose();

                    }

                    public void windowIconified(WindowEvent e) {
                        SaveState.setVisible(true);
                        ViewStates.setVisible(true);

                        Saving.dispose();
                    }

                    public void windowDeactivated(WindowEvent e) {
                        SaveState.setVisible(true);
                        ViewStates.setVisible(true);

                        Saving.dispose();
                    }

                });

                Submit.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource() == Submit) {
                            String s_StateName = StateName.getText();

                            if (s_StateName.length() > 0) {

                                saveState(s_StateName);
                                SaveState.setVisible(true);
                                ViewStates.setVisible(true);

                                Saving.dispose();
                            }
                        }

                    }

                });

                Saving.add(StateName);
                Saving.add(Submit);

                Saving.setTitle("Enter State Name");
                Saving.setResizable(false);
                Saving.setSize(500, 70);
                Saving.setLocationRelativeTo(null);
                Saving.setLayout(new GridLayout(1, 2, 5, 5));
                Saving.setVisible(true);

            }

            else if (e.getSource() == ViewStates) {

                ArrayList<String> AllStates = viewStates();

                if (AllStates.size() > 0) {

                    ViewStates.setVisible(false);
                    SaveState.setVisible(false);

                    JFrame State = new JFrame();

                    State.addWindowListener(new java.awt.event.WindowAdapter() {
                        public void windowClosing(java.awt.event.WindowEvent e) {

                            ViewStates.setVisible(true);
                            SaveState.setVisible(true);
                            State.dispose();
                        }

                        public void windowIconified(WindowEvent e) {
                            ViewStates.setVisible(true);
                            SaveState.setVisible(true);
                            State.dispose();

                        }

                        public void windowDeactivated(WindowEvent e) {
                            ViewStates.setVisible(true);
                            SaveState.setVisible(true);
                            State.dispose();
                        }
                    });

                    AllStates[] View = new AllStates[AllStates.size()];

                    for (int i = 0; i < AllStates.size(); i++) {
                        String Name = AllStates.get(i);
                        View[i] = new AllStates(Name);
                        State.add(View[i]);
                    }

                    State.setTitle("VIEW STATES");
                    State.setResizable(false);
                    State.setLayout(new GridLayout(AllStates.size(), 1));
                    State.pack();
                    State.setLocationRelativeTo(null);
                    State.setVisible(true);
                }
            }

            Generation.setText(Integer.toString(getGeneration()));

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
        ViewStates = new JButton("VIEW STATES");
        Zoom = new JSlider(CellSize, CellSize + 100, CellSize);
        Speed = new JSlider(0, 2000, 500);

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

        ViewStates.addActionListener(m_ActionListner);
        ViewStates.setFocusable(false);

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

    @Override
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

        ChangeDimensions((int) width / CellSize, (int) height / (CellSize / 4));

        INIT_GRID();

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
        Controls.add(ViewStates);
        Controls.add(Generation);

        Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Frame.setLayout(new BorderLayout());
        Frame.setTitle("John Conway's Game of Life");
        Frame.setResizable(false);
        Frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        Frame.setVisible(true);

    }

    private void INIT_GRID() {

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

            GridPanel.removeAll();

            ChangeDimensions((int) width / CellSize, (int) height / (CellSize / 4));

            INIT_GRID();

            GridPanel.revalidate();
            GridPanel.repaint();

            Generation.setText(Integer.toString(getGeneration()));
        }
    }

}
