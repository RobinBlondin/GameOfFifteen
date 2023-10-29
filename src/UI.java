import javax.swing.*;
import java.awt.*;
import java.util.List;

public class UI extends JFrame {

    Mechanics m = new Mechanics(this);
    JPanel window = new JPanel();
    JPanel board = new JPanel();
    JPanel buttonPanel = new JPanel();

    JButton[][] buttons = new JButton[4][4];
    JButton shuffle = new JButton("Shuffle");
    JButton godMode = new JButton("God Mode");

    List<List<JButton>> Lista = m.createList(buttons);

    public UI() {
        m.initializeButtons(buttons);

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

        m.shuffleList(Lista);

        board.setLayout(new GridLayout(4, 4));
        for (List<JButton> buttonList : Lista) {
            for (JButton button : buttonList) {
                board.add(button);
            }
        }
        shuffle.addActionListener(new EventHandler(this, m));
        godMode.addActionListener(new EventHandler(this, m));
    }
    public static void main(String[] args) {
        UI ui = new UI();
    }
}

