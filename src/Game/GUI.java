package Game;

import javax.swing.*;
import java.awt.*;

public class GUI {
    private JFrame window;
    private JPanel boardgrid;
    private JPanel buttongrid;
    int width, height;
    public GUI() {
        width = 600;
        height = 600;
    }

    public GUI(int width, int height) {
        this.width = width;
        this.height = height;
    }
    public JFrame getWindow() {
        return window;
    }

    public JPanel getBoardgrid() {
        return boardgrid;
    }

    public void initwindow() {
        window = new JFrame("Chess");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        boardgrid = new JPanel(new GridLayout(8, 8));
        int a = 0;
        int b = 1;
        for (int i = 0; i < 64; i++) {
            JPanel cell = new JPanel(new GridLayout(1, 1));
            cell.add(new JLabel(new ImageIcon()));
            if (i % 2 == a) {
                cell.setBackground(Color.black);
            }
            if (i % 2 == b) {
                cell.setBackground(Color.white);
            }
            if (i % 8 == 7) {
                int temp = a;
                a = b;
                b = temp;
            }
            boardgrid.add(cell);
        }
        buttongrid = new JPanel(new GridLayout(4, 1));
        buttongrid.add(new JButton("text1"));
        buttongrid.add(new JButton("text2"));

        window.add(boardgrid);
        window.add(buttongrid, BorderLayout.EAST);
        window.setSize(width, height);
        window.getContentPane().setPreferredSize(new Dimension(800, 700));
        window.setVisible(true);
    }



}
