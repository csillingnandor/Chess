package Pieces;

import java.awt.*;

public class Rook extends Piece {
    public Rook(Color color, Color tilecolor) {
        this.color = color;
        this.tilecolor = tilecolor;
        if (color.equals(Color.black)) {
            this.textureID = "textures/PNGs/No Shadow/128h/b_rook_png_128px.png";
        }
        else {
            this.textureID = "textures/PNGs/No Shadow/128h/w_rook_png_128px.png";
        }
        this.value = 5;
    }
}
