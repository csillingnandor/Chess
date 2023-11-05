package Game;

import Pieces.Piece;

import java.awt.*;

public class Game {
    private GUI gui = new GUI(this);
    private Board board = new Board();
    private static Color colorinplay = Color.white;
    private boolean selected = false;
    private Piece selectedpiece = null;
    private TextureLoader textureLoader = new TextureLoader(this);
    public void start() {
        gui.initwindow();
        board.SetBoard();
        textureLoader.LoadChessBoard();
    }

    public Board getBoard() {
        return board;
    }

    public GUI getGui() {
        return gui;
    }

    public void gameloop() {

    }

    public Color getColorinplay() {
        return colorinplay;
    }

    public boolean isSelected() {
        return selected;
    }

    public Piece getSelectedpiece() {
        return selectedpiece;
    }

    public void setSelectedpiece(Piece selectedpiece) {
        this.selectedpiece = selectedpiece;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
