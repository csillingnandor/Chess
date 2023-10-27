package Game;

import Exceptions.RowTooLongException;
import Pieces.*;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Board {
    private Tile[][] tiles = new Tile[8][8];
    public void SetBoard() {
        int a = 0;
        int b = 1;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                tiles[i][j] = new Tile();
                if (j % 2 == 0) {
                    tiles[i][j].setColor(Color.black);
                }
                else {
                    tiles[i][j].setColor(Color.white);
                }
            }
            int temp = a;
            a = b;
            b = temp;
        }
        tiles[0][0].setPieceontile(new Rook(Color.black, tiles[0][0].getColor()));
        tiles[0][1].setPieceontile(new Knight(Color.black, tiles[0][1].getColor()));
        tiles[0][2].setPieceontile(new Bishop(Color.black, tiles[0][2].getColor()));
        tiles[0][3].setPieceontile(new Queen(Color.black, tiles[0][3].getColor()));
        tiles[0][4].setPieceontile(new King(Color.black, tiles[0][4].getColor()));
        tiles[0][5].setPieceontile(new Bishop(Color.black, tiles[0][5].getColor()));
        tiles[0][6].setPieceontile(new Knight(Color.black, tiles[0][6].getColor()));
        tiles[0][7].setPieceontile(new Rook(Color.black, tiles[0][7].getColor()));

        tiles[1][0].setPieceontile(new Pawn(Color.black, tiles[1][0].getColor()));
        tiles[1][1].setPieceontile(new Pawn(Color.black, tiles[1][1].getColor()));
        tiles[1][2].setPieceontile(new Pawn(Color.black, tiles[1][2].getColor()));
        tiles[1][3].setPieceontile(new Pawn(Color.black, tiles[1][3].getColor()));
        tiles[1][4].setPieceontile(new Pawn(Color.black, tiles[1][4].getColor()));
        tiles[1][5].setPieceontile(new Pawn(Color.black, tiles[1][5].getColor()));
        tiles[1][6].setPieceontile(new Pawn(Color.black, tiles[1][6].getColor()));
        tiles[1][7].setPieceontile(new Pawn(Color.black, tiles[1][7].getColor()));

        tiles[7][0].setPieceontile(new Rook(Color.white, tiles[7][0].getColor()));
        tiles[7][1].setPieceontile(new Knight(Color.white, tiles[7][1].getColor()));
        tiles[7][2].setPieceontile(new Bishop(Color.white, tiles[7][2].getColor()));
        tiles[7][3].setPieceontile(new Queen(Color.white, tiles[7][3].getColor()));
        tiles[7][4].setPieceontile(new King(Color.white, tiles[7][4].getColor()));
        tiles[7][5].setPieceontile(new Bishop(Color.white, tiles[7][5].getColor()));
        tiles[7][6].setPieceontile(new Knight(Color.white, tiles[7][6].getColor()));
        tiles[7][7].setPieceontile(new Rook(Color.white, tiles[7][7].getColor()));

        tiles[6][0].setPieceontile(new Pawn(Color.white, tiles[6][0].getColor()));
        tiles[6][1].setPieceontile(new Pawn(Color.white, tiles[6][1].getColor()));
        tiles[6][2].setPieceontile(new Pawn(Color.white, tiles[6][2].getColor()));
        tiles[6][3].setPieceontile(new Pawn(Color.white, tiles[6][3].getColor()));
        tiles[6][4].setPieceontile(new Pawn(Color.white, tiles[6][4].getColor()));
        tiles[6][5].setPieceontile(new Pawn(Color.white, tiles[6][5].getColor()));
        tiles[6][6].setPieceontile(new Pawn(Color.white, tiles[6][6].getColor()));
        tiles[6][7].setPieceontile(new Pawn(Color.white, tiles[6][7].getColor()));
    }

    public Tile[][] getTiles() {
        return tiles;
    }
}
