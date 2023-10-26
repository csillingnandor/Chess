import Exceptions.RowTooLongException;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Board {
    private Piece[][] pieces = new Piece[8][8];
    public void SetDefaultLayout() throws FileNotFoundException, RowTooLongException {
        Scanner filescanner = new Scanner(new File("layout.txt"));
        for (int i = 0; i < 8 && filescanner.hasNext(); i++) {
            String line = filescanner.nextLine();
            if (line.length() > 8) {
                throw new RowTooLongException();
            }
            for (int j = 0; j < 8; j++) {
                switch (line.charAt(j)) {
                    case 'r':
                        pieces[i][j] = new Rook();
                        break;
                    case 'k':
                        pieces[i][j] = new Knight();
                        break;
                    case 'b':
                        pieces[i][j] = new Bishop();
                        break;
                    case 'q':
                        pieces[i][j] = new Queen();
                        break;
                    case '*':
                        pieces[i][j] = new King();
                        break;
                    case 'p':
                        pieces[i][j] = new Pawn();
                        break;
                }
            }
        }
        filescanner.close();
    }
}
