package Pieces;

import Game.Board;
import Game.Tile;

import java.awt.*;
import java.util.ArrayList;

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
        movableTiles = new ArrayList<>();
        legalMoves = new ArrayList<>();
    }

    @Override
    public void collectMovableTiles(Board board) {
        int x = tileindex.x;
        int y = tileindex.y;
        ArrayList<Tile> tiles = new ArrayList<>();
        int[] vertical = {-2, -1, 1, 2};

        for (int i = 0; i < 4; i++) {
            if (Math.abs(vertical[i]) == 2) {
                /// horizontal = -1, 1
                if (board.isTileInsideBounds(x + vertical[i], y - 1)) {
                    if (board.getTileAt(x + vertical[i], y - 1).isEmpty()) {
                        tiles.add(board.getTileAt(x + vertical[i], y - 1));
                    } else if (!board.getTileAt(x + vertical[i], y - 1).getPieceontile().getColor().equals(color)) {
                        if (board.getPieceAt(x + vertical[i], y - 1).getClass().equals(King.class)) {
                            if (color.equals(Color.black)) {
                                board.getBlackPiecesTargetingKing().add(this);
                            }
                            else {
                                board.getWhitePiecesTargetingKing().add(this);
                            }
                        }
                        else {
                            tiles.add(board.getTileAt(x + vertical[i], y - 1));
                        }
                    }
                }
                if (board.isTileInsideBounds(x + vertical[i], y + 1)) {
                    if (board.getTileAt(x + vertical[i], y + 1).isEmpty()) {
                        tiles.add(board.getTileAt(x + vertical[i], y + 1));
                    } else if (!board.getTileAt(x + vertical[i], y + 1).getPieceontile().getColor().equals(color)) {
                        if (board.isKingOnTile(board.getTileAt(x + vertical[i], y + 1))) {
                            if (color.equals(Color.black)) {
                                board.getBlackPiecesTargetingKing().add(this);
                            }
                            else {
                                board.getWhitePiecesTargetingKing().add(this);
                            }
                        }
                        else {
                            tiles.add(board.getTileAt(x + vertical[i], y + 1));
                        }
                    }
                }
            }
            else {
                /// horizontal = -2, 2
                if (board.isTileInsideBounds(x + vertical[i], y - 2)) {
                    if (board.getTileAt(x + vertical[i], y - 2).isEmpty()) {
                        tiles.add(board.getTileAt(x + vertical[i], y - 2));
                    } else if (!board.getTileAt(x + vertical[i], y - 2).getPieceontile().getColor().equals(color)) {
                        if (board.isKingOnTile(board.getTileAt(x + vertical[i], y - 2))) {
                            if (color.equals(Color.black)) {
                                board.getBlackPiecesTargetingKing().add(this);
                            }
                            else {
                                board.getWhitePiecesTargetingKing().add(this);
                            }
                        }
                        else {
                            tiles.add(board.getTileAt(x + vertical[i], y - 2));
                        }
                    }
                }
                if (board.isTileInsideBounds(x + vertical[i], y + 2)) {
                    if (board.getTileAt(x + vertical[i],  y + 2).isEmpty()) {
                        tiles.add(board.getTileAt(x + vertical[i], y + 2));
                    } else if (!board.getTileAt(x + vertical[i], y + 2).getPieceontile().getColor().equals(color)) {
                        if (board.isKingOnTile(board.getTileAt(x + vertical[i], y + 2))) {
                            if (color.equals(Color.black)) {
                                board.getBlackPiecesTargetingKing().add(this);
                            }
                            else {
                                board.getWhitePiecesTargetingKing().add(this);
                            }
                        }
                        else {
                            tiles.add(board.getTileAt(x + vertical[i], y + 2));
                        }
                    }
                }
            }
        }
        movableTiles = tiles;
    }

}
