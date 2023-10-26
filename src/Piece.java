import java.awt.*;

public class Piece {
    private int value;
    private String textureID;
    private Color colour, tileColour;

    public String getID() {
        return textureID;
    }

    public void setTileColour(Color colour) {
        this.tileColour = colour;
    }

    public void setColour(Color colour) {
        this.colour = colour;
    }


}
