package Pieces;

import Game.Board;
import Game.Tile;
import Pieces.Piece;

import java.awt.*;
import java.util.ArrayList;

public class King extends Piece {
    ArrayList<Piece> piecesTargetingKing;
    public King(Color color, Point tileindex) {
        this.tileindex = tileindex;
        this.color = color;
        if (color.equals(Color.black)) {
            this.textureID = "textures/PNGs/No Shadow/128h/b_king_png_128px.png";
        }
        else {
            this.textureID = "textures/PNGs/No Shadow/128h/w_king_png_128px.png";
        }
        this.value = 0;
        movabletiles = new ArrayList<>();
        piecesTargetingKing = new ArrayList<>();
    }

    @Override
    public void collectMovableTiles(Board board) {
        int x = tileindex.x;
        int y = tileindex.y;
        ArrayList<Tile> tiles = new ArrayList<>();
        for (int i = -1; i < 2; i++){
            for (int j = -1; j < 2; j++){
                if (i == 0 && j == 0) {
                    continue;
                }
                if (board.isTileInsideBounds(x + i, y + j)) {
                    if (board.getTileAt(x + i, y + j).isEmpty()) {
                        tiles.add(board.getTileAt(x + i, y + j));
                    } else if (!board.getTileAt(x + i, y + j).getPieceontile().getColor().equals(color)) {
                        tiles.add(board.getTileAt(x + i, y + j));
                    }
                }
            }
        }
        movabletiles = tiles;
    }


}
