import javax.swing.*;
import java.awt.*;

public class Game {
    JFrame frame;
    JPanel panel;
    JPanel main;
    JPanel first;
    JPanel second;
    boolean selected;
    JLabel tempFirst=null;
    int inx=0;
    int iny=0;
    JPanel kingPan;
    private GUI gui = new GUI();
    private Board board = new Board();
    private TextureLoader textureLoader = new TextureLoader(this);
    public void start() {
//        gui.initWindow();
//        textureLoader.LoadChessBoard();
        /*
         * 	Initialize the frames and the panels
         * */
        frame =new JFrame("Chess Game");
        panel= new JPanel(new GridLayout(8,8));
        //panel.setPreferredSize(new Dimension(800,700));

        int n=0;int a=1;int b=0;

        /*
         * Create the checkered board using alternating colors
         * */


        for (int i=0;i<64;i++){
            JPanel label= new JPanel(new GridLayout(1,1));
            label.add(new JLabel(new ImageIcon()));
            //label.add(new JLabel("chess" + n));

            if(i%2==a)
                label.setBackground(Color.cyan);
            if(i%2==b)
                label.setBackground(Color.white);

            if(i%8==7){
                int temp=a;
                a=b;
                b=temp;
            }
            panel.add(label, n);
            n++;
        }
        /*
         * 	Set the images at the proper location
         * */
        ((JPanel) panel.getComponent(0)).remove(0);
        ((JPanel) panel.getComponent(0)).add(new JLabel(new ImageIcon("textures/PNGs/No shadow/1x/b_knight_1x_ns.png")));
        ((JPanel) panel.getComponent(1)).remove(0);
        ((JPanel) panel.getComponent(1)).add(new JLabel(new ImageIcon("textures/PNGs/No shadow/1x/b_knight_1x_ns.png")));
        ((JPanel) panel.getComponent(2)).remove(0);
        ((JPanel) panel.getComponent(2)).add(new JLabel(new ImageIcon("textures/PNGs/No shadow/1x/b_knight_1x_ns.png")));




        /*
         * 		Add a mouse Listener:
         * */


        /*
         * 	Initialize the frame:
         * */

        frame.add(panel);
        frame.setSize(600, 600);
        frame.getContentPane().setPreferredSize(new Dimension(800,700));
        frame.setVisible(true);



        /*
         * 	Set the color of the starting side:
         * */


    }

    public Board getBoard() {
        return board;
    }

    public GUI getGui() {
        return gui;
    }
}
