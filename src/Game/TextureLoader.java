package Game;

import javax.swing.*;
import java.awt.*;

public class TextureLoader {
    private Game game;
    public TextureLoader(Game game) {
        this.game = game;
    }
    public void LoadChessBoard() {
        for (int i = 0; i < 16; i++) {
            ((JPanel) game.getGui().getBoardgrid().getComponent(i)).remove(0);
            ((JPanel) game.getGui().getBoardgrid().getComponent(i)).add(new JLabel(transformimage(game.getBoard().getTiles()[i / 8][i % 8].getPieceontile().getID(), 50, 50)));
        }

        for (int i = 48; i < 64; i++) {
            ((JPanel) game.getGui().getBoardgrid().getComponent(i)).remove(0);
            ((JPanel) game.getGui().getBoardgrid().getComponent(i)).add(new JLabel(transformimage(game.getBoard().getTiles()[i / 8][i % 8].getPieceontile().getID(), 50, 50)));
        }
        game.getGui().visible();
    }

    public static ImageIcon transformimage(String imagepath, int nwidth, int nheight) {
        ImageIcon imageIcon = new ImageIcon(imagepath);
        Image image = imageIcon.getImage();
        Image newimage = image.getScaledInstance(nwidth, nheight, Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(newimage);
        return imageIcon;
    }
}
