import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;


//testtext
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

        buttons[3][3].setEnabled(false);        //inaktiverar en button

        add(window);
        setVisible(true);
        setSize(800,900);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        buttonPanel.add(godMode);
        buttonPanel.add(shuffle);

        window.setLayout(new BorderLayout());
        window.add(board, BorderLayout.CENTER);
        window.add(buttonPanel, BorderLayout.SOUTH);

        board.setLayout(new GridLayout (4,4));

        for (int i = 0; i < buttons.length; i++) {                      //for loopar som lägger in alla knappar i gridlayout
            for (int j = 0; j < buttons[i].length; j++) {
                board.add(buttons[i][j]);
            }
        }

        //window.setSize(500,500);
        //window.setVisible(true);
    }

    public static void main(String[] args) {
        UI ui = new UI();
        Mechanics m = new Mechanics(ui);
        List<List<JButton>> newList = m.createList(ui.buttons);

        for (int i = 0; i < newList.size(); i++) {
            for (int j = 0; j < newList.size(); j++) {
                
            }
        }
    } //testigen
}
