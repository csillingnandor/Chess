import Game.*;
import Pieces.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;

public class GameTest {
    private Game game;

    @Before
    public void init() {
        game = new Game();
    }

    @Test
    public void gameInitTest() {
        Assert.assertNotNull(game.getGUI());
        Assert.assertNotNull(game.getBoard());
        Assert.assertNotNull(game.getTextureLoader());
        Assert.assertNull(game.getSelectedpiece());

        Assert.assertFalse(game.isSelected());
        Assert.assertFalse(game.isGameOver());
    }

    @Test
    public void pieceTest() {
        for (int i = 0; i < 16; i++) {
            Assert.assertNotNull(game.getBoard().getPieceAt(i / 8, i % 8));
        }

        for (int i = 48; i < 64; i++) {
            Assert.assertNotNull(game.getBoard().getPieceAt(i / 8, i % 8));
        }
    }

    @Test
    public void pieceConstructorTest() {
         Piece w_knight = new Knight(Color.white, new Point(1, 1));
         Assert.assertEquals(Color.white, w_knight.getColor());
         Assert.assertEquals(new Point(1, 1), w_knight.getTileindex());
    }

    @Test
    public void tileTest() {
        Tile tile = new Tile();
        tile.setPieceontile(new King(Color.black, new Point(1, 1)));
        Assert.assertNotNull(tile.getPieceontile());
    }

    @Test
    public void knightMoveTest() {
        Piece knight = game.getBoard().getPieceAt(7, 1);
        Assert.assertFalse(knight.getMovableTiles().contains(game.getBoard().getTileAt(6, 1)));
    }

    @Test
    public void pawnMoveTest() {
        Piece pawn1 = game.getBoard().getPieceAt(6, 0);
        Piece pawn2 = new Pawn(Color.white, new Point(0, 0));
        game.getBoard().setPiece(pawn2, 5, 7);
        pawn2.collectMovableTiles(game.getBoard());

        Assert.assertTrue(pawn1.getMovableTiles().contains(game.getBoard().getTileAt(5, 0)));
        Assert.assertTrue(pawn1.getMovableTiles().contains(game.getBoard().getTileAt(4, 0)));

        Assert.assertTrue(pawn2.getMovableTiles().contains(game.getBoard().getTileAt(4, 7)));
        Assert.assertFalse(pawn2.getMovableTiles().contains(game.getBoard().getTileAt(3, 7)));
    }

    @Test
    public void checkTest() {
        game.getBoard().clearBoard();
        game.getBoard().setPiece(new Rook(Color.white, new Point(0, 0)), 5, 0);
        game.getBoard().setPiece(new King(Color.black, new Point(0, 0)), 6, 7);
        game.getBoard().setPiece(new Rook(Color.white, new Point(0, 0)), 6, 0);
        game.setColorinplay(Color.black);
        game.getBoard().collectPiecesInPlay();
        game.getBoard().updateMovableTiles();

        Assert.assertTrue(game.getBoard().isKingInCheck(Color.black));
        Assert.assertFalse(game.getBoard().isMate());
    }

    @Test
    public void mateTest() {
        game.getBoard().clearBoard();
        game.getBoard().setPiece(new Rook(Color.white, new Point(0, 0)), 6, 0);
        game.getBoard().setPiece(new King(Color.black, new Point(0, 0)), 7, 7);
        game.getBoard().setPiece(new Rook(Color.white, new Point(0, 0)), 7, 0);
        game.setColorinplay(Color.black);
        game.getBoard().collectPiecesInPlay();
        game.getBoard().updateMovableTiles();

        Assert.assertTrue(game.getBoard().isMate());
        Assert.assertFalse(game.getBoard().isStalemate());
    }

    @Test
    public void staleMateTest() {
        game.getBoard().clearBoard();
        game.getBoard().setPiece(new Rook(Color.white, new Point(0, 0)), 6, 6);
        game.getBoard().setPiece(new King(Color.black, new Point(0, 0)), 7, 7);
        game.getBoard().setPiece(new Bishop(Color.white, new Point(0, 0)), 5, 5);
        game.setColorinplay(Color.black);
        game.getBoard().collectPiecesInPlay();
        game.getBoard().updateMovableTiles();

        Assert.assertFalse(game.getBoard().isMate());
        Assert.assertTrue(game.getBoard().isStalemate());
    }

    @Test
    public void kingMoveTest() {
        game.getBoard().clearBoard();
        game.getBoard().setPiece(new King(Color.white, new Point(0, 0)), 0, 0);
        game.getBoard().setPiece(new King(Color.black, new Point(2, 2)), 2, 2);
        game.setColorinplay(Color.white);
        game.getBoard().collectPiecesInPlay();
        game.getBoard().updateMovableTiles();

        Assert.assertFalse(game.getBoard().getPieceAt(0, 0).getLegalMoves().contains(game.getBoard().getTileAt(1, 1)));
        Assert.assertTrue(game.getBoard().getPieceAt(0, 0).getLegalMoves().contains(game.getBoard().getTileAt(0, 1)));
        Assert.assertTrue(game.getBoard().getPieceAt(0, 0).getLegalMoves().contains(game.getBoard().getTileAt(1, 0)));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void outOfBoundsTest() {
        game.getBoard().clearBoard();
        game.getBoard().setPiece(new King(Color.white, new Point(8,8)), 8, 8);
    }
}
