package Pieces;

import java.awt.*;

public class Queen extends Piece {
    public Queen(Color color, Color tilecolor) {
        this.color = color;
        this.tilecolor = tilecolor;
        if (color.equals(Color.black)) {
            this.textureID = "textures/PNGs/No Shadow/128h/b_queen_png_128px.png";
        }
        else {
            this.textureID = "textures/PNGs/No Shadow/128h/w_queen_png_128px.png";
        }
        this.value = 9;
    }
}
