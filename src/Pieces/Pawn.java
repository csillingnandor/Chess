package Pieces;

import Pieces.Piece;

import java.awt.*;

public class Pawn extends Piece {
    public Pawn(Color color, Color tilecolor, Point tileindex) {
        this.tileindex = tileindex;
        this.color = color;
        this.tilecolor = tilecolor;
        if (color.equals(Color.black)) {
            this.textureID = "textures/PNGs/No Shadow/128h/b_pawn_png_128px.png";
        }
        else {
            this.textureID = "textures/PNGs/No Shadow/128h/w_pawn_png_128px.png";
        }
        this.value = 1;
    }
}
