package Pieces;
import Game.Tile;

import javax.swing.text.Position;
import java.awt.*;
import java.util.ArrayList;

public class Piece {
    protected int value;
    protected String textureID;
    protected Color color, tilecolor;
    protected Point tileindex;
    protected ArrayList<Tile> movabletiles;
    public String getID() {
        return textureID;
    }

    public void setTilecolor(Color color) {
        this.tilecolor = color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public ArrayList<Tile> getMovabletiles() {
        return movabletiles;
    }

    public Color getColor() {
        return color;
    }

    public Point getTileindex() {
        return tileindex;
    }
}
