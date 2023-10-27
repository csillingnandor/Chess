package Pieces;
import java.awt.*;

public class Piece {
    protected int value;
    protected String textureID;
    protected Color color, tilecolor;
    public String getID() {
        return textureID;
    }

    public void setTilecolor(Color color) {
        this.tilecolor = color;
    }

    public void setColor(Color color) {
        this.color = color;
    }


}
