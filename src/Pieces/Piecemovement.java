package Pieces;

import Game.Board;
import Game.Tile;

import java.util.ArrayList;

public interface Piecemovement {
    /**
     * Az adott bábuval definiált mozgással bejárja a táblát, és összeyűjti a lehetséges lépéseket
     * @param board
     */
    void collectMovableTiles(Board board);
}
