package Game;

import Pieces.*;

import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;

public class Board implements Serializable {
    private Tile[][] tiles;
    private ArrayList<Piece> piecesInPlay;
    private ArrayList<Piece> whitePieces;
    private ArrayList<Piece> blackPieces;
    private ArrayList<Piece> whitePiecesTargetingKing;
    private ArrayList<Piece> blackPiecesTargetingKing;
    public Board() {
        tiles = new Tile[8][8];
        piecesInPlay = new ArrayList<>();
        whitePieces = new ArrayList<>();
        blackPieces = new ArrayList<>();
        whitePiecesTargetingKing = new ArrayList<>();
        blackPiecesTargetingKing = new ArrayList<>();
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
        placePieces();
        collectPiecesInPlay();
        updateMovableTiles();
    }

    /**
     * Végigmegy a mezőkön és összegyűjti a játékban lévő bábukat, amiket berak egy közös piecesInPlay listába.
     * Színtől függően vagy a blackPieces, vagy a whitePieces listába is bekerülnek
     */
    public void collectPiecesInPlay() {
        piecesInPlay = new ArrayList<>();
        whitePieces = new ArrayList<>();
        blackPieces = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (tiles[i][j].getPieceontile() != null) {
                    piecesInPlay.add(tiles[i][j].getPieceontile());
                    if (tiles[i][j].getPieceontile().getColor().equals(Color.white)) {
                        whitePieces.add(tiles[i][j].getPieceontile());
                    }
                    else {
                        blackPieces.add(tiles[i][j].getPieceontile());
                    }
                }
            }
        }
    }

    /**
     * Beállítja az alapfelállást a táblán
     */
    private void placePieces() {
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

//        tiles[7][7].setPieceontile(new King(Color.white, new Point(7, 7)));
//        tiles[5][7].setPieceontile(new Pawn(Color.white, new Point(5, 7)));
//        tiles[6][6].setPieceontile(new Rook(Color.black, new Point(6, 6)));
//        tiles[5][5].setPieceontile(new Bishop(Color.black, new Point(5, 5)));
    }

    /**
     * Minden lépés után összegyűjti a soron következő játékos bábuinak lehetséges lépéseit.
     * A függvény először a bábuk összes szabályos lépését összegyűjti, majd kiszűri azokat,
     * melyek megtehetők anélkül, hogy a lépés után a játékos sakkot kapjon/sakkban maradjon.
     * Ehhez a bábukat elmozgatjuk a lehetséges mezőkre, megállapítjuk megtehető-e a lépés,
     * majd visszaállítjuk az eredeti felállást
     */
    public void updateMovableTiles() {
        for (Piece piece : piecesInPlay) {
            piece.collectMovableTiles(this);
        }
        if (Game.colorinplay.equals(Color.white)) {
            for (Piece piece: whitePieces) {
                Point origin = piece.getTileindex();
                ArrayList<Tile> legalTiles = new ArrayList<>();
                for (Tile tile: piece.getMovableTiles()) {
                    blackPiecesTargetingKing.clear();
                    Piece removedPiece = null;
                    if (!getTileAt(tile.getX(), tile.getY()).isEmpty()) {
                        removedPiece = getPieceAt(tile.getX(), tile.getY());
                    }
                    setPiece(piece, tile.getX(), tile.getY());
                    collectTiles(Color.black);
                    testPosition(piece, origin, legalTiles, tile, removedPiece, blackPiecesTargetingKing, blackPieces);
                }
                piece.setLegalMoves(legalTiles);
            }
            blackPiecesTargetingKing.clear();
            collectTiles(Color.black);
        }
        else if (Game.colorinplay.equals(Color.black)) {
            for (Piece piece: blackPieces) {
                Point origin = piece.getTileindex();
                ArrayList<Tile> legalTiles = new ArrayList<>();
                for (Tile tile: piece.getMovableTiles()) {
                    whitePiecesTargetingKing.clear();
                    Piece removedPiece = null;
                    if (!getTileAt(tile.getX(), tile.getY()).isEmpty()) {
                        removedPiece = getPieceAt(tile.getX(), tile.getY());
                    }
                    setPiece(piece, tile.getX(), tile.getY());
                    collectTiles(Color.white);
                    testPosition(piece, origin, legalTiles, tile, removedPiece, whitePiecesTargetingKing, whitePieces);
                }
                piece.setLegalMoves(legalTiles);
            }
            whitePiecesTargetingKing.clear();
            collectTiles(Color.white);
        }
    }

    /**
     * Segédfüggvény az updateMovableTiles() olvashatóbbá tételéhez
     * @param piece
     * @param origin
     * @param legalTiles
     * @param tile
     * @param removedPiece
     * @param piecesTargetingKing
     * @param pieces
     */
    private void testPosition(Piece piece, Point origin, ArrayList<Tile> legalTiles, Tile tile, Piece removedPiece, ArrayList<Piece> piecesTargetingKing, ArrayList<Piece> pieces) {
        if (piecesTargetingKing.isEmpty()) {
            legalTiles.add(tile);
        }
        setPiece(piece, origin.x, origin.y);
        if (removedPiece != null) {
            setPiece(removedPiece, tile.getX(), tile.getY());
            piecesInPlay.add(removedPiece);
            pieces.add(removedPiece);
        }
    }

    public Tile[][] getTiles() {
        return tiles;
    }

    public void setTiles(Tile[][] tiles) {
        this.tiles = tiles;
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

    public Piece getPieceAt(int x, int y) {
        return getTiles()[x][y].getPieceontile();
    }

    /**
     * Elhelyez egy bábut a táblán a megadott x és y indexen
     * Ha a megadott mezőn van az ellenfélnek bábuja, kitörli a játékból
     * @param selectedPiece
     * @param x
     * @param y
     */
    public void setPiece(Piece selectedPiece, int x, int y) {
        Piece pieceOnTile = getTileAt(x, y).getPieceontile();
        removePiece(pieceOnTile);
        getTileAt(selectedPiece.getrow(), selectedPiece.getcol()).setPieceontile(null);
        getTileAt(x, y).setPieceontile(selectedPiece);
        selectedPiece.setTileindex(new Point(x, y));
    }

    /**
     * Kitöröl egy bábut a játékból
     * @param piece
     */
    public void removePiece(Piece piece) {
        if (piece != null) {
            int x = piece.getrow();
            int y = piece.getcol();
            getTileAt(x, y).setPieceontile(null);
            piecesInPlay.remove(piece);
            if (piece.getColor().equals(Color.white)) {
                whitePieces.remove(piece);
            }
            else {
                blackPieces.remove(piece);
            }
        }
    }

    /**
     * Megállapítja, egy x és y koordinátával adott pozíció a táblán belül van-e
     * @param x
     * @param y
     * @return true, ha benne van, false, ha nincs benne
     */
    public boolean isTileInsideBounds(int x, int y) {
        if (x >= 0 && x <= 7 && y >= 0 && y <= 7) {
            return true;
        }
        else {
            return false;
        }
    }

    public ArrayList<Piece> getBlackPiecesTargetingKing() {
        return blackPiecesTargetingKing;
    }

    public ArrayList<Piece> getWhitePiecesTargetingKing() {
        return whitePiecesTargetingKing;
    }

    /**
     * Megállapítja egy adott mezőről, hogy van-e rajta király
     * @param tile
     * @return true, ha van rajta király, false, ha nincs rajta király
     */
    public boolean isKingOnTile(Tile tile) {
        return tile.getPieceontile().getClass().equals(King.class);
    }

    /**
     * Eldönti, a megadott színű király sakkban van-e
     * @param color
     * @return true, ha sakkban van, false, ha nincs sakkban
     */
    public boolean isKingInCheck(Color color) {
        if (color.equals(Color.black)) {
            return !whitePiecesTargetingKing.isEmpty();
        }
        else {
            return !blackPiecesTargetingKing.isEmpty();
        }
    }

    /**
     * A megadott színű bábuk lehetséges lépéseit összegyűjti
     * @param color
     */
    public void collectTiles(Color color) {
        if (color.equals(Color.white)) {
            for (Piece piece: whitePieces) {
                piece.collectMovableTiles(this);
            }
        }
        else if (color.equals(Color.black))
            for (Piece piece: blackPieces) {
                piece.collectMovableTiles(this);
            }
        }

    /**
     * Eldönti, hogy matt van-e
     * @return true, ha matt van, false, ha nincs matt
     */
    public boolean isMate() {
            if (isKingInCheck(Game.colorinplay)) {
                if (Game.colorinplay.equals(Color.white)) {
                    if (hasMovableTiles(whitePieces)) {
                        return false;
                    }
                }
                else {
                    if (hasMovableTiles(blackPieces)) {
                        return false;
                    }
                }
                return true;
            }
            return false;
        }

    /**
     * Eldönti, hogy patt van-e
     * @return true, ha patt van, false, ha nincs patt
     */
    public boolean isStalemate() {
            if (!isKingInCheck(Game.colorinplay)) {
                if (Game.colorinplay.equals(Color.white)) {
                    if (hasMovableTiles(whitePieces)) {
                        return false;
                    }
                }
                else {
                    if (hasMovableTiles(blackPieces)) {
                        return false;
                    }
                }
                return true;
            }
            return false;
        }

    /**
     * Eldönti, hogy a megadott bábuk között van-e olyan, aminek van szabályos lépése
     * @param pieces
     * @return true, ha van legalább egy bábu, aminek van legalább egy szabályos lépése, false, ha nincs egy bábunak se szabályos lépése
     */
    private boolean hasMovableTiles(ArrayList<Piece> pieces) {
            for (Piece piece: pieces) {
                if (!piece.getLegalMoves().isEmpty()) {
                    return true;
                }
            }
            return false;
        }

    /**
     * Kitöröl minden bábut a táblából
     */
    public void clearBoard() {
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    removePiece(getPieceAt(i, j));
                }
            }
        }
    }

