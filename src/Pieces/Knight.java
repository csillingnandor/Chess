package Pieces;

import Pieces.Piece;

import java.awt.*;

public class Knight extends Piece {
    public Knight(Color color, Point tileindex) {
        this.tileindex = tileindex;
        this.color = color;
        if (color.equals(Color.black)) {
            this.textureID = "textures/PNGs/No Shadow/128h/b_knight_png_128px.png";
        }
        else {
            this.textureID = "textures/PNGs/No Shadow/128h/w_knight_png_128px.png";
        }
        this.value = 3;
    }
}
