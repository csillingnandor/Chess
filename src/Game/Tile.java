package Game;

import Pieces.Piece;

import javax.swing.text.Position;
import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;

public class Tile implements Serializable {
    private Color color;
    private Piece pieceontile;
    private Point position;

    public Tile() {}
    public Color getColor() {
        return color;
    }

    public Piece getPieceontile() {
        return pieceontile;
    }

    public void setPieceontile(Piece piece) {
        this.pieceontile = piece;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public boolean isEmpty() {
        return pieceontile == null;
    }

    public boolean canSelect() {
        return (!isEmpty() && getPieceontile().getColor().equals(Game.colorinplay));
    }

    public Point getPosition() {
        return position;
    }

    public int getX() {
        return position.x;
    }
    public int getY() {
        return position.y;
    }

    public void setPosition(Point position) {
        this.position = position;
    }
}
