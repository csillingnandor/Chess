package Game;

import javax.swing.*;
import java.awt.*;

public class TextureLoader {
    private Game game;
    public TextureLoader(Game game) {
        this.game = game;
    }
    public void loadChessBoard() {
//        clearRemainingTextures();
        for (int i = 0; i < 16; i++) {
            ((JPanel) game.getGUI().getBoardGrid().getComponent(i)).remove(0);
            ((JPanel) game.getGUI().getBoardGrid().getComponent(i)).add(new JLabel(transformImage(game.getBoard().getTiles()[i / 8][i % 8].getPieceontile().getID(), 40, 40)));
        }

        for (int i = 16; i < 48; i++) {
            ((JPanel) game.getGUI().getBoardGrid().getComponent(i)).remove(0);
            ((JPanel) game.getGUI().getBoardGrid().getComponent(i)).add(new JLabel());
        }

        for (int i = 48; i < 64; i++) {
            ((JPanel) game.getGUI().getBoardGrid().getComponent(i)).remove(0);
            ((JPanel) game.getGUI().getBoardGrid().getComponent(i)).add(new JLabel(transformImage(game.getBoard().getTiles()[i / 8][i % 8].getPieceontile().getID(), 40, 40)));
        }
        game.getGUI().rePaintBoard();
        game.getGUI().visible();
    }

    public static ImageIcon transformImage(String imagepath, int nwidth, int nheight) {
        ImageIcon imageIcon = new ImageIcon(imagepath);
        Image image = imageIcon.getImage();
        Image newimage = image.getScaledInstance(nwidth, nheight, Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(newimage);
        return imageIcon;
    }

    public void clearRemainingTextures() {
        for (int i = 0; i < 64; i++) {
            ((JPanel) game.getGUI().getBoardGrid().getComponent(i)).remove(0);
        }
    }
}
