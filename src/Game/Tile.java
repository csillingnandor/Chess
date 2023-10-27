package Game;

import Pieces.Piece;

import java.awt.*;
import java.util.ArrayList;

public class Tile {
    private Color color;
    private Piece pieceontile;
    private ArrayList<Piece> PiecesCoveringTile = new ArrayList<>();

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

    public ArrayList<Piece> getPiecesCoveringTile() {
        return PiecesCoveringTile;
    }
}
