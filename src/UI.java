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
    private final List<List<JButton>> buttonList;

    public UI() {
        this.utils = new Utils(this);
        this.m = new Mechanics(this, utils);
        this.buttonList = utils.createListFromArray(buttons);

        initializeButtons();
        createBoard();

        shuffle.addActionListener(new EventHandler(this, utils, m));
        godMode.addActionListener(new EventHandler(this, utils, m));
    }

    public void initializeButtons() {
        int counter = 1;
        for (int i = 0; i < buttons.length; i++) {
            for (int j = 0; j < buttons[i].length; j++) {
                buttons[i][j] = new JButton(counter == 16? "": String.valueOf(counter));
                buttons[i][j].setFont(new Font("Arial", Font.BOLD, 30));
                buttons[i][j].setBackground(counter == 16? Color.LIGHT_GRAY: Color.decode("#FEF9E7"));
                buttons[i][j].setForeground(Color.decode("#212F3C"));
                buttons[i][j].setFocusPainted(false);
                buttons[i][j].addActionListener(new EventHandler(this, utils, m));
                counter++;
            }
        }
    }

    public void createBoard() {
        JPanel window = new JPanel();
        add(window);
        setVisible(true);
        setSize(800, 900);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.setPreferredSize(new Dimension(80,80));

        buttonPanel.add(godMode);
        godMode.setPreferredSize(new Dimension(200,60));
        buttonPanel.add(new JLabel("                                          "));
        buttonPanel.add(shuffle);
        shuffle.setPreferredSize(new Dimension(200,60));

        window.setLayout(new BorderLayout());
        window.add(board, BorderLayout.CENTER);
        window.add(buttonPanel, BorderLayout.SOUTH);

        utils.setShuffledLabels(buttonList);

        board.setLayout(new GridLayout(4, 4));
        for (List<JButton> buttonList : buttonList) {
            for (JButton button : buttonList) {
                board.add(button);
            }
        }
    }

    public void revalidateRepaint() {
        board.revalidate();
        board.repaint();
    }


    public void rewriteBoard() {
        board.removeAll();
        for (List<JButton> row : buttonList) {
            for (JButton button : row) {
                board.add(button);
            }
        }
    }

    public void swapButtonColor(JButton clickedButton, JButton emptyButton){
        clickedButton.setBackground(Color.LIGHT_GRAY);
        emptyButton.setBackground(Color.decode("#FEF9E7"));
    }

    public void resetButtonColors(int index, JButton clickedButton){
        if(index < 15) {
            clickedButton.setBackground(Color.decode("#FEF9E7"));
        } else {
            clickedButton.setBackground(Color.LIGHT_GRAY);
        }
    }


    //region Getters och setters
    public JButton getShuffle() {
        return shuffle;
    }

    public JButton getGodMode() {
        return godMode;
    }
    public JPanel getBoard() {
        return board;
    }

    public List<List<JButton>> getButtonList() {
        return buttonList;
    }
    //endregion
}


