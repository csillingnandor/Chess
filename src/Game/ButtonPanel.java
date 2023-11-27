package Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonPanel implements ActionListener {
    private Game game;
    private JPanel buttongrid = new JPanel();
    private JButton forfeitButton = new JButton("Forfeit");
    private JButton resetButton = new JButton("Reset game");
    private JButton enterButton = new JButton("Start game");
    private JTextField whitePlayerName = new JTextField(20);
    private JTextField blackPlayerName = new JTextField(20);

    public ButtonPanel(Game game) {
        this.game = game;
    }

    public void init() {
        buttongrid.setLayout(new GridLayout(8, 1));

        forfeitButton.addActionListener(this);
        forfeitButton.setActionCommand("forfeit");
        resetButton.addActionListener(this);
        resetButton.setActionCommand("reset");
        enterButton.addActionListener(this);
        enterButton.setActionCommand("start");

        buttongrid.add(forfeitButton);
        buttongrid.add(resetButton);
        buttongrid.add(enterButton);
        buttongrid.add(whitePlayerName);
        buttongrid.add(blackPlayerName);

        game.getGUI().getWindow().add(buttongrid, BorderLayout.EAST);

    }
    @Override
    public void actionPerformed(ActionEvent ae) {
        switch (ae.getActionCommand()) {
            case "forfeit":
                game.end("forfeit");
                break;
            case "reset":
                game.reset();
                break;
            case "start":
                game.setPlayerName(Color.white, whitePlayerName.getText());
                game.setPlayerName(Color.black, blackPlayerName.getText());
                break;
        }
    }
}
