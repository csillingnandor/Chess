package Pieces;
import Game.Tile;

import javax.swing.text.Position;
import java.awt.*;
import java.util.ArrayList;

public class Piece {
    protected int value;
    protected String textureID;
    protected Color color;
    protected Point tileindex;
    protected ArrayList<Tile> movabletiles;
    public String getID() {
        return textureID;
    }
    public void setColor(Color color) {
        this.color = color;
    }
    public Color getColor() { return color; }

    public ArrayList<Tile> getMovabletiles() { return movabletiles; }

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
}
