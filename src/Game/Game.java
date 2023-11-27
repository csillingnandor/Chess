package Game;

import Pieces.Piece;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class Game {
    private GUI gui = new GUI(this);
    private Board board = new Board();
    private boolean selected = false;
    private Piece selectedpiece = null;
    public static Color colorinplay = Color.white;
    private boolean gameOver = false;
    private String whitePlayerName, blackPlayerName;
    private TextureLoader textureLoader = new TextureLoader(this);
    public void start() {
        gui.initwindow();
        textureLoader.loadChessBoard();
    }

    public Board getBoard() {
        return board;
    }

    public GUI getGUI() {
        return gui;
    }

    public Color getColorinplay() {
        return colorinplay;
    }

    public void setColorinplay(Color colorinplay) {
        Game.colorinplay = colorinplay;
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

    public boolean canMoveTo(Tile tile) {
        return selectedpiece.getLegalMoves().contains(tile);
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }
    public void end(String result) { setGameOver(true); }

    public void setPlayerName(Color color, String name) {
        if (color.equals(Color.white)) {
            whitePlayerName = name;
        }
        else {
            blackPlayerName = name;
        }
    }

    public void reset() {
        this.board = new Board();
        colorinplay = Color.white;
        this.selectedpiece = null;
        this.selected = false;
        this.gameOver = false;
        textureLoader.loadChessBoard();

    }

    public void save() throws IOException {
        ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("save" + 1 + ".txt"));
    }
}
