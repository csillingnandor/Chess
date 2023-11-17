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
        Tile clickedTile = game.getBoard().getTiles()[rowidx][colidx];
        if (!game.isSelected()) {
            if (clickedTile.canSelect()) {
                selectPiece(e.getComponent(), game, rowidx, colidx);
            }
        }
        else {
            /// We save the initially selected cell's color, in case we'd like to select another piece, so we can remove the highlighting from the originally selected piece and replace it with the color of the tile
            Component selectedcomponent = boardgrid.getComponent(game.getSelectedpiece().getTileindex().x * 8 + game.getSelectedpiece().getTileindex().y);
            Color tilecolor = game.getBoard().tileColorAtPosition(game.getSelectedpiece().getTileindex().x, game.getSelectedpiece().getTileindex().y);

            if (clickedTile.canSelect()) {
                deselectPiece(selectedcomponent, tilecolor);
                selectPiece(e.getComponent(), game, rowidx, colidx);
            }
            else {
                if (game.canMoveTo(clickedTile)) {
                    addPieceTexture(boardgrid, game.getSelectedpiece(), rowidx, colidx);
                    removePieceTexture(boardgrid, game.getSelectedpiece().getTileindex().x, game.getSelectedpiece().getTileindex().y);

                    game.getBoard().setPiece(game.getSelectedpiece(), rowidx, colidx);
                    deselectPiece(selectedcomponent, tilecolor);
                    game.setSelected(false);
                    game.getBoard().updateMovableTiles();


                    if (game.getColorinplay().equals(Color.white)) {
                        game.setColorinplay(Color.black);
                    }
                    else {
                        game.setColorinplay(Color.white);
                    }
                }
            }
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

    public void addPieceTexture(JPanel boardgrid, Piece selectedpiece, int row, int col) {
        int component_idx = row * 8 + col;
        ((JPanel) boardgrid.getComponent(component_idx)).remove(0);
        ((JPanel) boardgrid.getComponent(component_idx)).add(new JLabel(TextureLoader.transformimage(selectedpiece.getID(), 50, 50)));
    }

    public void removePieceTexture(JPanel boardgrid, int row, int col) {
        int component_idx = row * 8 + col;
        ((JPanel) boardgrid.getComponent(component_idx)).remove(0);
        ((JPanel) boardgrid.getComponent(component_idx)).add(new JLabel());
    }

    public void selectPiece(Component component, Game game, int row, int col) {
        component.setBackground(Color.cyan);
        game.setSelected(true);
        game.setSelectedpiece(game.getBoard().getPiece(row, col));
    }

    public void deselectPiece(Component component, Color tilecolor) {
        component.setBackground(tilecolor);
    }
}
