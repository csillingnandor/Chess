package Pieces;

import Pieces.Piece;

import java.awt.*;

public class King extends Piece {
    public King(Color color, Color tilecolor, Point tileindex) {
        this.tileindex = tileindex;
        this.color = color;
        this.tilecolor = tilecolor;
        if (color.equals(Color.black)) {
            this.textureID = "textures/PNGs/No Shadow/128h/b_king_png_128px.png";
        }
        else {
            this.textureID = "textures/PNGs/No Shadow/128h/w_king_png_128px.png";
        }
        this.value = 0;
    }
}
