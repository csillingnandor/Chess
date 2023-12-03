package Game;

import java.awt.*;
import java.io.Serializable;

public class CurrentPlayState implements Serializable {
    private Color colorInPLay;
    private Board board;

    public CurrentPlayState(Color color, Board board) {
        this.colorInPLay = color;
        this.board = board;
    }

    public Color getColorInPLay() {
        return colorInPLay;
    }

    public void setColorInPLay(Color colorInPLay) {
        this.colorInPLay = colorInPLay;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }
}
