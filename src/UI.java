import javax.swing.*;
import java.awt.*;
import java.util.List;

public class UI extends JFrame {

    private final Mechanics m;
    private final Utils utils;
    private final JPanel board = new JPanel();
    private final JButton shuffle = new JButton("Shuffle");
    private final JButton godMode = new JButton("God Mode");

    private final JButton[][] buttons = new JButton[4][4];
    private List<List<JButton>> buttonList;

    public UI() {
        this.utils = new Utils(this);
        this.m = new Mechanics(this, utils);
        this.buttonList = utils.createListFromArray(buttons);

        add(window);
        setVisible(true);
        setSize(800, 900);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        buttonPanel.add(godMode);
        buttonPanel.add(shuffle);

        window.setLayout(new BorderLayout());
        window.add(board, BorderLayout.CENTER);
        window.add(buttonPanel, BorderLayout.SOUTH);

        board.setLayout(new GridLayout(4, 4));
        for (List<JButton> buttonList : Lista) {
            for (JButton button : buttonList) {
                board.add(button);
            }
        }
        shuffle.addActionListener(new EventHandler(this, utils, m));
        godMode.addActionListener(new EventHandler(this, utils, m));
    }

    //region Getters och setters

    public JButton getShuffle() {
        return shuffle;
    }


    public JButton getGodMode() {
        return godMode;
    }


    public List<List<JButton>> getButtonList() {
        return buttonList;
    }

    public void setButtonList(List<List<JButton>> buttonList) {
        this.buttonList = buttonList;
    }
    public static void main(String[] args) {
        UI ui = new UI();
    }
}

