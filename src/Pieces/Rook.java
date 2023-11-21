package Pieces;

import Game.Board;
import Game.Tile;

import java.awt.*;
import java.util.ArrayList;

public class Rook extends Piece {
    public Rook(Color color, Point tileindex) {
        this.tileindex = tileindex;
        this.color = color;
        if (color.equals(Color.black)) {
            this.textureID = "textures/PNGs/No Shadow/128h/b_rook_png_128px.png";
        }
        else {
            this.textureID = "textures/PNGs/No Shadow/128h/w_rook_png_128px.png";
        }
        this.value = 5;
        movableTiles = new ArrayList<>();
        legalMoves = new ArrayList<>();
    }

    @Override
    public void collectMovableTiles(Board board) {
        int x = tileindex.x;
        int y = tileindex.y;
        ArrayList<Tile> tiles = new ArrayList<>();
        rookTraverse(board, tiles, x, y, 1, 0);
        rookTraverse(board, tiles, x, y, -1, 0);
        rookTraverse(board, tiles, x, y, 0, 1);
        rookTraverse(board, tiles, x, y, 0, -1);
        movableTiles = tiles;
    }

    private void rookTraverse(Board board, ArrayList<Tile> tiles, int x, int y, int dirx, int diry) {
        try {
            if (board.isTileInsideBounds(x + dirx, y + diry)) {
                if (board.getTileAt(x + dirx, y + diry).isEmpty()) {
                    tiles.add(board.getTileAt(x + dirx, y + diry));
                    rookTraverse(board, tiles, x + dirx, y + diry, dirx, diry);
                } else if (!board.getPiece(x + dirx, y + diry).getColor().equals(color)) {
                    if (board.isKingOnTile(board.getTileAt(x + dirx, y + diry))) {
                        if (color.equals(Color.black)) {
                            board.getB_PiecesTargetingKing().add(this);
                        }
                        else {
                            board.getW_PiecesTargetingKing().add(this);
                        }
                    }
                    else {
                        tiles.add(board.getTileAt(x + dirx, y + diry));
                    }
                }
            }
        } catch (IndexOutOfBoundsException ignored){
        }
    }
}
