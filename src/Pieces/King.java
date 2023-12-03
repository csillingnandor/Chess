package Pieces;

import Game.Board;
import Game.Tile;

import java.awt.*;
import java.util.ArrayList;

public class King extends Piece {
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
        movableTiles = new ArrayList<>();
        legalMoves = new ArrayList<>();
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
                        if (board.getPieceAt(x + i, y + j).getClass().equals(King.class)) {
                            if (color.equals(Color.black)) {
                                board.getBlackPiecesTargetingKing().add(this);
                                board.getWhitePiecesTargetingKing().add(board.getPieceAt(x + i, y + j));
                            }
                            else {
                                board.getWhitePiecesTargetingKing().add(this);
                                board.getBlackPiecesTargetingKing().add(board.getPieceAt(x + i, y + j));
                            }
                        }
                        else {
                            tiles.add(board.getTileAt(x + i, y + j));
                        }
                    }
                }
            }
        }
        movableTiles = tiles;
    }

}
