package Game;

import Pieces.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

public class Board {
    private Tile[][] tiles;
    private ArrayList<Piece> piecesInPlay;
    private ArrayList<Piece> w_Pieces;
    private ArrayList<Piece> b_Pieces;
    private ArrayList<Piece> w_PiecesTargetingKing;
    private ArrayList<Piece> b_PiecesTargetingKing;
    public Board() {
        tiles = new Tile[8][8];
        piecesInPlay = new ArrayList<>();
        w_Pieces = new ArrayList<>();
        b_Pieces = new ArrayList<>();
        w_PiecesTargetingKing = new ArrayList<>();
        b_PiecesTargetingKing = new ArrayList<>();
        int a = 0;
        int b = 1;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                tiles[i][j] = new Tile();
                if (j % 2 == a) {
                    tiles[i][j].setColor(Color.black);
                }
                else {
                    tiles[i][j].setColor(Color.white);
                }
                tiles[i][j].setPosition(new Point(i, j));
            }
            int temp = a;
            a = b;
            b = temp;
        }
        tiles[0][0].setPieceontile(new Rook(Color.black, new Point(0, 0)));
        tiles[0][1].setPieceontile(new Knight(Color.black, new Point(0, 1)));
        tiles[0][2].setPieceontile(new Bishop(Color.black, new Point(0, 2)));
        tiles[0][3].setPieceontile(new Queen(Color.black, new Point(0, 3)));
        tiles[0][4].setPieceontile(new King(Color.black, new Point(0, 4)));
        tiles[0][5].setPieceontile(new Bishop(Color.black, new Point(0, 5)));
        tiles[0][6].setPieceontile(new Knight(Color.black, new Point(0, 6)));
        tiles[0][7].setPieceontile(new Rook(Color.black, new Point(0, 7)));

        tiles[1][0].setPieceontile(new Pawn(Color.black, new Point(1, 0)));
        tiles[1][1].setPieceontile(new Pawn(Color.black, new Point(1, 1)));
        tiles[1][2].setPieceontile(new Pawn(Color.black, new Point(1, 2)));
        tiles[1][3].setPieceontile(new Pawn(Color.black, new Point(1, 3)));
        tiles[1][4].setPieceontile(new Pawn(Color.black, new Point(1, 4)));
        tiles[1][5].setPieceontile(new Pawn(Color.black, new Point(1, 5)));
        tiles[1][6].setPieceontile(new Pawn(Color.black, new Point(1, 6)));
        tiles[1][7].setPieceontile(new Pawn(Color.black, new Point(1, 7)));

        tiles[7][0].setPieceontile(new Rook(Color.white, new Point (7, 0)));
        tiles[7][1].setPieceontile(new Knight(Color.white, new Point(7, 1)));
        tiles[7][2].setPieceontile(new Bishop(Color.white, new Point(7, 2)));
        tiles[7][3].setPieceontile(new Queen(Color.white, new Point(7, 3)));
        tiles[7][4].setPieceontile(new King(Color.white, new Point(7, 4)));
        tiles[7][5].setPieceontile(new Bishop(Color.white, new Point(7, 5)));
        tiles[7][6].setPieceontile(new Knight(Color.white, new Point(7, 6)));
        tiles[7][7].setPieceontile(new Rook(Color.white, new Point(7, 7)));

        tiles[6][0].setPieceontile(new Pawn(Color.white, new Point(6, 0)));
        tiles[6][1].setPieceontile(new Pawn(Color.white, new Point(6, 1)));
        tiles[6][2].setPieceontile(new Pawn(Color.white, new Point(6, 2)));
        tiles[6][3].setPieceontile(new Pawn(Color.white, new Point(6, 3)));
        tiles[6][4].setPieceontile(new Pawn(Color.white, new Point(6, 4)));
        tiles[6][5].setPieceontile(new Pawn(Color.white, new Point(6, 5)));
        tiles[6][6].setPieceontile(new Pawn(Color.white, new Point(6, 6)));
        tiles[6][7].setPieceontile(new Pawn(Color.white, new Point(6, 7)));
        collectPiecesInPlay();
        updateMovableTiles();
    }
    public void collectPiecesInPlay() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (tiles[i][j].getPieceontile() != null) {
                    piecesInPlay.add(tiles[i][j].getPieceontile());
                    if (tiles[i][j].getPieceontile().getColor().equals(Color.white)) {
                        w_Pieces.add(tiles[i][j].getPieceontile());
                    }
                    else {
                        b_Pieces.add(tiles[i][j].getPieceontile());
                    }
                }
            }
        }
    }

    public void updateMovableTiles() {
        for (Piece piece: piecesInPlay) {
            piece.collectMovableTiles(this);
        }




//        if (Game.colorinplay.equals(Color.white)) {
//            ArrayList<Piece> w_Pieces_copy = new ArrayList<>(w_Pieces);
//            for (Iterator<Piece> pieceIterator = w_Pieces_copy.iterator(); pieceIterator.hasNext();) {
//                Piece w_piece = pieceIterator.next();
//                for (Tile tile: w_piece.getMovableTiles()) {
//                    Board board_copy = this;
//                    ArrayList<Piece> b_Pieces_copy = b_Pieces;
//                    board_copy.setPiece(w_piece, tile.getX(), tile.getY());
//                    for (Piece b_piece : b_Pieces_copy) {
//                        b_piece.collectMovableTiles(board_copy);
//                    }
//                    if (b_PiecesTargetingKing.isEmpty()) {
//                        w_piece.getLegalMoves().add(tile);
//                    }
//                }
//            }
//        }
//        else {
//            ArrayList<Piece> b_Pieces_copy = new ArrayList<>(b_Pieces);
//            for (Iterator<Piece> pieceIterator = b_Pieces_copy.iterator(); pieceIterator.hasNext();) {
//                Piece b_piece = pieceIterator.next();
//                for (Tile tile: b_piece.getMovableTiles()) {
//                    Board board_copy = this;
//                    ArrayList<Piece> w_Pieces_copy = w_Pieces;
//                    board_copy.setPiece(b_piece, tile.getX(), tile.getY());
//                    for (Piece w_piece: w_Pieces_copy) {
//                        w_piece.collectMovableTiles(board_copy);
//                    }
//                    if (w_PiecesTargetingKing.isEmpty()) {
//                        b_piece.getLegalMoves().add(tile);
//                    }
//                }
//            }
//
//        }
    }

    public Tile[][] getTiles() {
        return tiles;
    }

    public Tile getTileAt(int x, int y) {
        return getTiles()[x][y];
    }

    public Color colorAtPosition(int x, int y) {
        return tiles[x][y].getPieceontile().getColor();
    }
    public Color tileColorAtPosition(int x, int y) {
        return tiles[x][y].getColor();
    }

    public Piece getPiece(int x, int y) {
        return getTiles()[x][y].getPieceontile();
    }

    public void setPiece(Piece selectedPiece, int x, int y) {
        Piece pieceOnTile = getTileAt(x, y).getPieceontile();
        removePiece(pieceOnTile);
        getTileAt(selectedPiece.getrow(), selectedPiece.getcol()).setPieceontile(null);
        getTileAt(x, y).setPieceontile(selectedPiece);
        selectedPiece.setTileindex(new Point(x, y));
    }

    public void removePiece(Piece piece) {
        if (piece != null) {
            int x = piece.getrow();
            int y = piece.getcol();
            getTileAt(x, y).setPieceontile(null);
            piecesInPlay.remove(piece);
            if (piece.getColor().equals(Color.white)) {
                w_Pieces.remove(piece);
            }
            else {
                b_Pieces.remove(piece);
            }
        }
    }

    public boolean isTileInsideBounds(int x, int y) {
        if (x >= 0 && x <= 7 && y >= 0 && y <= 7) {
            return true;
        }
        else {
            return false;
        }
    }

    public ArrayList<Piece> getB_PiecesTargetingKing() {
        return b_PiecesTargetingKing;
    }

    public ArrayList<Piece> getW_PiecesTargetingKing() {
        return w_PiecesTargetingKing;
    }

    public boolean isKingOnTile(Tile tile) {
        return tile.getPieceontile().getClass().equals(King.class);
    }

    public boolean isKingInCheck(Color color) {
        if (color.equals(Color.black)) {
            return !w_PiecesTargetingKing.isEmpty();
        }
        else {
            return !b_PiecesTargetingKing.isEmpty();
        }
    }
}
