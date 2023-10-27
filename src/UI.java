import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

public class UI extends JFrame {
    JPanel window = new JPanel();
    JPanel board = new JPanel();
    JPanel buttonPanel = new JPanel();

    JButton[][] buttons = new JButton[4][4];

    JButton shuffle = new JButton("Shuffle");
    JButton godMode = new JButton("God Mode");

    int counter = 1;
    public UI () {
        for (int i = 0; i < buttons.length; i++) {
            for (int j = 0; j < buttons.length; j++) {
                buttons[i][j] = new JButton(counter == 16? "":String.valueOf(counter));
                buttons[i][j].addActionListener(new EventHandler());
                counter++;
            }
        }

        add(window);
        window.setLayout(new BorderLayout());
        window.add(board, BorderLayout.CENTER);
        window.add(buttonPanel, BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        UI ui = new UI();
        Mechanics m = new Mechanics(ui);
        List<List<JButton>> newList = m.createList(ui.buttons);

        for (int i = 0; i < newList.size(); i++) {
            for (int j = 0; j < ; j++) {
                
            }
        }
    }
}
