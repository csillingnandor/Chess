package Pieces;
import Game.Board;
import Game.Tile;

import java.awt.*;
import java.util.ArrayList;

public abstract class Piece implements Piecemovement{
    protected int value;
    protected String textureID;
    protected Color color;
    protected Point tileindex;
    protected ArrayList<Tile> movableTiles;
    protected ArrayList<Tile> legalMoves;
    public String getID() {
        return textureID;
    }
    public void setColor(Color color) {
        this.color = color;
    }
    public Color getColor() { return color; }

    public ArrayList<Tile> getMovableTiles() { return movableTiles; }

    public int getrow() {
        return tileindex.x;
    }

    public int getcol() { return tileindex.y; }

    public Point getTileindex() {
        return tileindex;
    }

    public void setTileindex(Point coordinates) {
        tileindex = coordinates;
    }

    public boolean canMoveTo(Tile tile) {
        return movableTiles.contains(tile);
    }

    @Override
    public abstract void collectMovableTiles(Board board);

    public ArrayList<Tile> getLegalMoves() {
        return legalMoves;
    }

    public void setLegalMoves(ArrayList<Tile> legalMoves) {
        this.legalMoves = legalMoves;
    }
}
