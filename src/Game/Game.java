package Game;

public class Game {
    private GUI gui = new GUI();
    private Board board = new Board();
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
}
