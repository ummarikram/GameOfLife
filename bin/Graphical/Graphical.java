package Graphical;

import UserInterface.*;
import Grid.Grid;
import java.awt.Color;
import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Graphical extends JFrame implements UserInterface, ActionListener {
    private JFrame Frame;
    private JPanel Cells[];
    private JButton Start;
    private JButton Stop;
    private JButton Reset;
    private JButton Next;
    private JButton Clear;
    private JButton ViewStates;

    public Graphical() {
        Frame = new JFrame();
        Frame.setTitle("John Conway's Game of Life");
        Frame.setDefaultCloseOperation(Frame.EXIT_ON_CLOSE);
        Frame.setLayout(null);
        Frame.setSize(600, 600);
        Frame.setVisible(true);
        Frame.getContentPane().setBackground(Color.BLACK);

        JPanel Grid = new JPanel();
        Grid.setBackground(Color.DARK_GRAY);
        Grid.setBounds(0, 50, Frame.getWidth(), Frame.getHeight() / 2);

        Frame.add(Grid);
    }

    public void Display(Grid grid) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
