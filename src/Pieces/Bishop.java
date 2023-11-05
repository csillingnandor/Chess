package Pieces;

import Game.Board;
import Game.Tile;

import java.awt.*;
import java.util.ArrayList;

public class Bishop extends Piece implements Piecemovement {
    public Bishop(Color color, Color tilecolor, Point tileindex) {
        this.tileindex = tileindex;
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

    @Override
    public void collectMovableTiles(Board board) {
        int x = tileindex.x;
        int y = tileindex.y;
        ArrayList<Tile> tiles = new ArrayList<>();
        bishoptraverse(board, tiles, x, y);
        movabletiles = tiles;

    }
    public void bishoptraverse(Board board, ArrayList<Tile> tiles, int dirx, int diry) {
        try {
        if (board.getTiles()[dirx][diry].isEmpty()){
            tiles.add(board.getTiles()[dirx][diry]);
        } else if (!board.coloratposition(dirx, diry).equals(board.coloratposition(tileindex.x, tileindex.y))) {
            tiles.add(board.getTiles()[dirx][diry]);
            return;
        } else if (board.coloratposition(dirx, diry).equals(board.coloratposition(tileindex.x, tileindex.y))) {
            return;
        }
        } catch (IndexOutOfBoundsException ex) {
            return;
        }
        bishoptraverse(board, tiles, dirx + 1, diry + 1);
        bishoptraverse(board, tiles, dirx - 1, diry - 1);
        bishoptraverse(board, tiles, dirx - 1, diry + 1);
        bishoptraverse(board, tiles,  dirx + 1, diry - 1);
    }
}
