import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class TextureLoader {
    private Game game;
    private BufferedImage[] textures = new BufferedImage[6];
    public void LoadChessBoard() {
        JPanel panel = new JPanel(new GridLayout(8, 8));
        for (int i = 0; i < 64; i++) {
            if (i % 2 == 0) {
                ((JPanel) panel.getComponent(i)).remove(0);
                ((JPanel) panel.getComponent(i)).add(new JLabel(new ImageIcon("textures/PNGs/No Shadow/1x/b_queen_1x_ns.png")));
            }
        }

        game.getGui().getWindow().add(panel);
        game.getGui().getWindow().setVisible(true);
    }

    public TextureLoader (Game game) {
        this.game = game;
    }
    public BufferedImage LoadTexture(String textureID) throws IOException {
        BufferedImage image = ImageIO.read(new File(textureID));
        return image;
    }

}
