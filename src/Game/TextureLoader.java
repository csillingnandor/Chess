package Game;

import javax.swing.*;
import java.awt.*;

public class TextureLoader {
    private Game game;
    public TextureLoader(Game game) {
        this.game = game;
    }

    /**
     * Kirajzolja a sakktáblára a bábukat
     */
    public void loadChessBoard() {
        for (int i = 0; i < 64; i++) {
            ((JPanel) game.getGUI().getBoardGrid().getComponent(i)).remove(0);
            if (game.getBoard().getPieceAt(i / 8, i % 8) != null) {
                ((JPanel) game.getGUI().getBoardGrid().getComponent(i)).add(new JLabel(transformImage(game.getBoard().getPieceAt(i / 8, i % 8).getID(), 40, 40)));
            }
            else {
                ((JPanel) game.getGUI().getBoardGrid().getComponent(i)).add(new JLabel());
            }
        }

        game.getGUI().resetBoardColors();
        game.getGUI().visible();
    }

    /**
     * A bemenetként megkapott kép felbontását átalakítja a paraméterként kapott szélesség-maasság értékekre
     * @param imagepath
     * @param nwidth
     * @param nheight
     * @return
     */
    public static ImageIcon transformImage(String imagepath, int nwidth, int nheight) {
        ImageIcon imageIcon = new ImageIcon(imagepath);
        Image image = imageIcon.getImage();
        Image newimage = image.getScaledInstance(nwidth, nheight, Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(newimage);
        return imageIcon;
    }

}
