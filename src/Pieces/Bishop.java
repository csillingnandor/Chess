package Pieces;

import java.awt.*;

public class Bishop extends Piece {
    public Bishop(Color color, Color tilecolor) {
        this.color = color;
        this.tilecolor = tilecolor;
        if (color.equals(Color.black)) {
            this.textureID = "textures/PNGs/No Shadow/128h/b_bishop_png_128px.png";
        }
        else {
            this.textureID = "textures/PNGs/No Shadow/128h/w_bishop_png_128px.png";
        }
        this.value = 3;
    }
}
