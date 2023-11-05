package Game;

import Pieces.Piece;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GUI implements MouseListener{
    private Game game;
    private JFrame window;
    private JPanel boardgrid;
    private JPanel buttongrid;
    int width, height;
    public GUI(Game game) {
        this.game = game;
        width = 600;
        height = 600;
    }

    public GUI(Game game, int width, int height) {
        this.game = game;
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
            cell.addMouseListener(this);
            boardgrid.add(cell);
        }
        buttongrid = new JPanel(new GridLayout(4, 1));
        buttongrid.add(new JButton("text1"));
        buttongrid.add(new JButton("text2"));

        window.add(boardgrid);
        window.add(buttongrid, BorderLayout.EAST);
        window.setSize(width, height);
        window.getContentPane().setPreferredSize(new Dimension(800, 700));
        visible();
    }


    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        int x=e.getComponent().getX()/e.getComponent().getWidth();
        int y=7-e.getComponent().getY()/e.getComponent().getHeight();
        int rowidx = 7 - y;
        int colidx = x;
        if (!game.isSelected()) {
            Tile clickedtile = game.getBoard().getTiles()[rowidx][colidx];
            if (!clickedtile.isEmpty() && clickedtile.getPieceontile().getColor().equals(game.getColorinplay())) {
                e.getComponent().setBackground(Color.cyan);
                game.setSelected(true);
                game.setSelectedpiece(game.getBoard().getPiece(rowidx, colidx));
            }
        }
        else {
            placePiece(boardgrid, game.getSelectedpiece(), rowidx, colidx);
            game.setSelected(false);
        }
        visible();
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

    public void visible() {
        window.setVisible(true);
    }

    public void placePiece(JPanel boardgrid, Piece selectedpiece, int row, int col) {
        int component_idx = row * 8 + col;
        ((JPanel) boardgrid.getComponent(component_idx)).remove(0);
        ((JPanel) boardgrid.getComponent(component_idx)).add(new JLabel(TextureLoader.transformimage(selectedpiece.getID(), 50, 50)));
    }
}
