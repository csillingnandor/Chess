package Game;

import Pieces.Piece;

import java.awt.*;
import java.io.*;
import java.util.HashMap;
import java.util.Objects;

public class Game {
    private GUI gui;
    private Board board;
    private boolean selected;
    private Piece selectedpiece;
    public static Color colorinplay = Color.white;
    private boolean gameOver;
    private TextureLoader textureLoader;
    private CurrentPlayState playState;

    public Game() {
        gui = new GUI(this);
        board = new Board();
        textureLoader = new TextureLoader(this);
        selected = false;
        selectedpiece = null;
        gameOver = false;

    }
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
    public void end(String result) {
        setGameOver(true);
        System.out.println("Game ended by: " + result);
    }

    public TextureLoader getTextureLoader() {
        return textureLoader;
    }

    public void reset() {
        colorinplay = Color.white;
        this.board = new Board();
        this.selectedpiece = null;
        this.selected = false;
        this.gameOver = false;
        textureLoader.loadChessBoard();

    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public void save(int saveNumber) throws IOException {
            playState = new CurrentPlayState(colorinplay, getBoard());
            ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("saves/save" + saveNumber + ".txt"));
            outputStream.writeObject(playState);
            outputStream.close();

    }

    public void load(int saveNumber) throws IOException, ClassNotFoundException {
        ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("saves/save" + saveNumber + ".txt"));
        CurrentPlayState currentPlayState = (CurrentPlayState) inputStream.readObject();
        Board gameBoard = currentPlayState.getBoard();
        Color color = currentPlayState.getColorInPLay();
        setBoard(gameBoard);
        getBoard().updateMovableTiles();
        colorinplay = color;
        textureLoader.loadChessBoard();
        inputStream.close();

    }

    public void delete(int saveNumber) {
        File saveFolder = new File("saves");
        File saves[] = saveFolder.listFiles();
        assert saves != null;
        for (File file: saves) {
            if (file.getName().contains(String.valueOf(saveNumber))) {
                file.delete();
            }
        }
    }
}
