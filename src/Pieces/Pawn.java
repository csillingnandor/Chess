package Pieces;

import Game.Board;
import Game.Tile;

import java.awt.*;
import java.util.ArrayList;

public class Pawn extends Piece {
    public Pawn(Color color, Point tileindex) {
        this.tileindex = tileindex;
        this.color = color;
        if (color.equals(Color.black)) {
            this.textureID = "textures/PNGs/No Shadow/128h/b_pawn_png_128px.png";
        }
        else {
            this.textureID = "textures/PNGs/No Shadow/128h/w_pawn_png_128px.png";
        }
        this.value = 1;
        movableTiles = new ArrayList<>();
        legalMoves = new ArrayList<>();
    }

    @Override
    public void collectMovableTiles(Board board) {
        int x = tileindex.x;
        int y = tileindex.y;
        ArrayList<Tile> tiles = new ArrayList<>();
        if (color.equals(Color.black)) {
            if (x == 1) {
                if (board.getTileAt(x + 2, y).isEmpty() && board.getTileAt(x + 1, y).isEmpty()) {
                    tiles.add(board.getTileAt(x + 2, y));
                }
            }
            if (board.isTileInsideBounds(x + 1, y)) {
                if (board.getTileAt(x + 1, y).isEmpty()) {
                    tiles.add(board.getTileAt(x + 1, y));
                }
            }
            if (board.isTileInsideBounds(x + 1, y + 1)) {
                if (!board.getTileAt(x + 1, y + 1).isEmpty()) {
                    if (!board.colorAtPosition(x + 1, y + 1).equals(color)) {
                        if (board.isKingOnTile(board.getTileAt(x + 1, y + 1))) {
                            if (color.equals(Color.black)) {
                                board.getBlackPiecesTargetingKing().add(this);
                            }
                            else {
                                board.getWhitePiecesTargetingKing().add(this);
                            }
                        }
                        else {
                            tiles.add(board.getTileAt(x + 1, y + 1));
                        }
                    }
                }
            }
            if (board.isTileInsideBounds(x + 1, y - 1)) {
                if (!board.getTileAt(x + 1, y - 1).isEmpty()) {
                    if (!board.colorAtPosition(x + 1, y - 1).equals(color)) {
                        if (board.isKingOnTile(board.getTileAt(x + 1, y - 1))) {
                            if (color.equals(Color.black)) {
                                board.getBlackPiecesTargetingKing().add(this);
                            }
                            else {
                                board.getWhitePiecesTargetingKing().add(this);
                            }
                        }
                        else {
                            tiles.add(board.getTileAt(x + 1, y - 1));
                        }
                    }
                }
            }
        }
        else {
            if (x == 6) {
                if (board.getTileAt(x - 2, y).isEmpty() && board.getTileAt(x - 1, y).isEmpty()) {
                    tiles.add(board.getTileAt(x - 2, y));
                }
            }
            if (board.isTileInsideBounds(x - 1, y)) {
                if (board.getTileAt(x - 1, y).isEmpty()) {
                    tiles.add(board.getTileAt(x - 1, y));
                }
            }
            if (board.isTileInsideBounds(x - 1, y + 1)) {
                if (!board.getTileAt(x - 1, y + 1).isEmpty()) {
                    if (!board.colorAtPosition(x - 1, y + 1).equals(color)) {
                        if (board.isKingOnTile(board.getTileAt(x - 1, y + 1))) {
                            if (color.equals(Color.black)) {
                                board.getBlackPiecesTargetingKing().add(this);
                            }
                            else {
                                board.getWhitePiecesTargetingKing().add(this);
                            }
                        }
                        else {
                            tiles.add(board.getTileAt(x - 1, y + 1));
                        }
                    }
                }
            }
            if (board.isTileInsideBounds(x - 1, y - 1)) {
                if (!board.getTileAt(x - 1, y - 1).isEmpty()) {
                    if (!board.colorAtPosition(x - 1, y - 1).equals(color)) {
                        if (board.isKingOnTile(board.getTileAt(x - 1, y - 1))) {
                            if (color.equals(Color.black)) {
                                board.getBlackPiecesTargetingKing().add(this);
                            }
                            else {
                                board.getWhitePiecesTargetingKing().add(this);
                            }
                        }
                        else {
                            tiles.add(board.getTileAt(x - 1, y - 1));
                        }
                    }
                }
            }
        }
        movableTiles = tiles;
    }
}
