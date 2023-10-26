import javax.swing.*;
import java.awt.*;

public class GUI {
    private JFrame window = new JFrame("Chess");
    int width, height;
    public GUI() {
        width = 800;
        height = 800;
    }

    public GUI(int width, int height) {
        this.width = width;
        this.height = height;
    }
    public JFrame getWindow() {
        return window;
    }

    public void initWindow() {
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(width, height);
        JMenuBar options = new JMenuBar();
        JMenu menu1 = new JMenu("Start game");
        JMenu menu2 = new JMenu("Forfeit");
        options.add(menu1);
        options.add(menu2);
        window.getContentPane().add(BorderLayout.NORTH, options);
        window.setVisible(true);
    }

}
