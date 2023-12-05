package Game;

import Pieces.Piece;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GUI implements MouseListener {
    private Game game;
    private JFrame window;
    private JPanel boardGrid;
    private ButtonPanel buttonGrid;
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

    public JPanel getBoardGrid() {
        return boardGrid;
    }

    /**
     * Ablak, sakktábla és a kezelőpanel létrehozása, megjelenítése
     */
    public void initwindow() {
        window = new JFrame("Chess");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        boardGrid = new JPanel(new GridLayout(8, 8));
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
            boardGrid.add(cell);
        }
        buttonGrid = new ButtonPanel(game);
        buttonGrid.init();
        window.add(boardGrid);
        window.setSize(width, height);
        window.getContentPane().setPreferredSize(new Dimension(800, 800));
        visible();
    }


    @Override
    public void mouseClicked(MouseEvent e) {

    }

    /**
     * Kezeli a logikát egérgomb lenyomást követően
     * @param e the event to be processed
     */
    @Override
    public void mousePressed(MouseEvent e) {
        if (!game.isGameOver()) {

            int x = e.getComponent().getX() / e.getComponent().getWidth();
            int y = 7 - e.getComponent().getY() / e.getComponent().getHeight();
            int rowidx = 7 - y;
            int colidx = x;
            Tile clickedTile = game.getBoard().getTileAt(rowidx, colidx);
            if (!game.isSelected()) {
                if (clickedTile.canSelect()) {
                    selectPiece(e.getComponent(), game, rowidx, colidx);
                    highLightMovableTiles(game.getSelectedpiece());
                }
            } else {

                if (clickedTile.canSelect()) {
                    resetBoardColors();
                    selectPiece(e.getComponent(), game, rowidx, colidx);
                    highLightMovableTiles(game.getSelectedpiece());
                } else {
                    if (game.canMoveTo(clickedTile)) {
                        addPieceTexture(boardGrid, game.getSelectedpiece(), rowidx, colidx);
                        removePieceTexture(boardGrid, game.getSelectedpiece().getTileindex().x, game.getSelectedpiece().getTileindex().y);

                        game.getBoard().setPiece(game.getSelectedpiece(), rowidx, colidx);
                        resetBoardColors();
                        game.setSelected(false);


                        if (game.getColorinplay().equals(Color.white)) {
                            game.setColorinplay(Color.black);
                        } else {
                            game.setColorinplay(Color.white);
                        }
                        game.getBoard().updateMovableTiles();

                    }
                }
            }
            if (game.getBoard().isMate()) {
                game.end("Checkmate");
            } else if (game.getBoard().isStalemate()) {
                game.end("Stalemate");
            }
            visible();
        }
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

    /**
     * Láthatóvá teszi a képernyőt
     */
    public void visible() {
        window.setVisible(true);
    }

    /**
     * Kirajzolja a megadott bábu textúráját a képernyőre az adott sor és oszlopt által megadott pozícióban
     * @param boardgrid
     * @param selectedpiece
     * @param row
     * @param col
     */
    public void addPieceTexture(JPanel boardgrid, Piece selectedpiece, int row, int col) {
        int component_idx = row * 8 + col;
        ((JPanel) boardgrid.getComponent(component_idx)).remove(0);
        ((JPanel) boardgrid.getComponent(component_idx)).add(new JLabel(TextureLoader.transformImage(selectedpiece.getID(), 40, 40)));
    }

    /**
     * Kitörli egy bábu texturáját az adott sor és oszlop által megadott pozícióban
     * @param boardgrid
     * @param row
     * @param col
     */
    public void removePieceTexture(JPanel boardgrid, int row, int col) {
        int component_idx = row * 8 + col;
        ((JPanel) boardgrid.getComponent(component_idx)).remove(0);
        ((JPanel) boardgrid.getComponent(component_idx)).add(new JLabel());
    }

    /**
     * Kiválasztja azt a bábut, melyre kattintottunk, és a képernyőn megjelöli
     * @param component
     * @param game
     * @param row
     * @param col
     */
    public void selectPiece(Component component, Game game, int row, int col) {
        component.setBackground(Color.cyan);
        game.setSelected(true);
        game.setSelectedpiece(game.getBoard().getPieceAt(row, col));
    }

    /**
     * Visszaállítja a mezők eredeti színeit
     */
    public void resetBoardColors() {
        for (int i = 0; i < 64; i++) {
            getBoardGrid().getComponent(i).setBackground(game.getBoard().tileColorAtPosition(i / 8, i % 8));
        }
    }

    /**
     * Egy adott bábut kiválasztva kiemeli azokat a mezőket, melyre lépni tud az adott bábu
     * @param piece
     */
    public void highLightMovableTiles(Piece piece) {
        for (Tile tile: piece.getLegalMoves()) {
            getBoardGrid().getComponent(tile.getX() * 8 + tile.getY()).setBackground(Color.red);
        }
    }

}
