package Pieces;

import Game.Board;
import Game.Tile;

import java.awt.*;
import java.util.ArrayList;

public class Bishop extends Piece {
    public Bishop(Color color, Point tileindex) {
        this.tileindex = tileindex;
        this.color = color;
        if (color.equals(Color.black)) {
            this.textureID = "textures/PNGs/No Shadow/128h/b_bishop_png_128px.png";
        }
        else {
            this.textureID = "textures/PNGs/No Shadow/128h/w_bishop_png_128px.png";
        }
        this.value = 3;
        movabletiles = new ArrayList<>();
    }

    @Override
    public void collectMovableTiles(Board board) {
        int x = tileindex.x;
        int y = tileindex.y;
        ArrayList<Tile> tiles = new ArrayList<>();
        bishopTraverse(board, tiles, x, y, 1, 1);
        bishopTraverse(board, tiles, x, y, 1, -1);
        bishopTraverse(board, tiles, x, y, -1, 1);
        bishopTraverse(board, tiles, x, y, -1, -1);
        movabletiles = tiles;

    }


    private void bishopTraverse(Board board, ArrayList<Tile> tiles, int x, int y, int dirx, int diry) {
        try {
            if (board.isTileInsideBounds(x + dirx, y + diry)) {
                if (board.getTileAt(x + dirx, y + diry).isEmpty()) {
                    tiles.add(board.getTileAt(x + dirx, y + diry));
                    bishopTraverse(board, tiles, x + dirx, y + diry, dirx, diry);
                } else if (!board.getTileAt(x + dirx, y + diry).getPieceontile().getColor().equals(color)) {
                    tiles.add(board.getTileAt(x + dirx, y + diry));
                }
            }
        } catch (IndexOutOfBoundsException ignored){
        }
    }
}
