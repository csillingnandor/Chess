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
        movableTiles = new ArrayList<>();
        legalMoves = new ArrayList<>();
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
        movableTiles = tiles;

    }

    /**
     * Egy adott átlós irányban bejárja a táblát, és összegyűjti azokat a mezőket, amikre tud lépni
     * @param board
     * @param tiles
     * @param x
     * @param y
     * @param dirx
     * @param diry
     */
    private void bishopTraverse(Board board, ArrayList<Tile> tiles, int x, int y, int dirx, int diry) {
        try {
            if (board.isTileInsideBounds(x + dirx, y + diry)) {
                if (board.getTileAt(x + dirx, y + diry).isEmpty()) {
                    tiles.add(board.getTileAt(x + dirx, y + diry));
                    bishopTraverse(board, tiles, x + dirx, y + diry, dirx, diry);
                } else if (!board.getPieceAt(x + dirx, y + diry).getColor().equals(color)) {
                    if (board.isKingOnTile(board.getTileAt(x + dirx, y + diry))) {
                        if (color.equals(Color.black)) {
                            board.getBlackPiecesTargetingKing().add(this);
                        }
                        else {
                            board.getWhitePiecesTargetingKing().add(this);
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
