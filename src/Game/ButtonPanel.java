package Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class ButtonPanel implements ActionListener {
    private Game game;
    private JPanel buttongrid = new JPanel();
    private JComboBox<Object> saveList;
    private JButton forfeitButton = new JButton("Forfeit");
    private JButton resetButton = new JButton("Reset game");
    private JButton saveButton = new JButton("Save");
    private JButton loadButton = new JButton("Load");
    private JButton deleteButton = new JButton("Delete");

    public ButtonPanel(Game game) {
        this.game = game;
    }

    public void init() {
        Integer[] integers = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        saveList = new JComboBox<>(integers);
        buttongrid.setLayout(new GridLayout(9, 3));

        forfeitButton.addActionListener(this);
        resetButton.addActionListener(this);
        saveButton.addActionListener(this);
        loadButton.addActionListener(this);
        deleteButton.addActionListener(this);

        forfeitButton.setActionCommand("forfeit");
        resetButton.setActionCommand("reset");
        saveButton.setActionCommand("save");
        loadButton.setActionCommand("load");
        deleteButton.setActionCommand("delete");

        buttongrid.add(forfeitButton);
        buttongrid.add(resetButton);
        buttongrid.add(saveButton);
        buttongrid.add(loadButton);
        buttongrid.add(deleteButton);
        buttongrid.add(saveList);

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
            case "save":
                try {
                    game.save((Integer) saveList.getSelectedItem());
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                break;
            case "load":
                try {
                    game.load((Integer) saveList.getSelectedItem());
                } catch (Exception ex) {
                    System.out.println("Save doesn't exist");
                }
                break;
            case "delete":
                try {
                    game.delete((Integer) saveList.getSelectedItem());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                break;
        }
    }
}
