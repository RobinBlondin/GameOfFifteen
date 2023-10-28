import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;


//testtext
public class UI extends JFrame /*implements ActionListener */{  //implement tillfälligt

    Mechanics m = new Mechanics();
    JPanel window = new JPanel();
    JPanel board = new JPanel();
    JPanel buttonPanel = new JPanel();

    JButton[][] buttons = new JButton[4][4];

    JButton shuffle = new JButton("Shuffle");
    JButton godMode = new JButton("God Mode");

    List<List<JButton>> Lista = m.createList(buttons);
    List<List<JButton>> Lista2 = m.createList(buttons);   //kanske kan använda denna senare vid "god mode", denna shufflas aldrig?

    int counter = 1;

    public UI() {
        for (int i = 0; i < buttons.length; i++) {
            for (int j = 0; j < buttons.length; j++) {
                buttons[i][j] = new JButton(counter == 16 ? "" : String.valueOf(counter));
                buttons[i][j].addActionListener(new EventHandler(this, m));
                counter++;
            }
        }

        buttons[3][3].setEnabled(false);        //inaktiverar en button

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

        Lista = m.shuffleBoard(Lista);

        board.setLayout(new GridLayout(4, 4));
        for (List<JButton> buttonList : Lista) {
            for (JButton button : buttonList) {
                board.add(button);
            }
        }
        //Löste det genom att skapa en ny konstruktor i mechanics och skickar in denna instans av UI och Mechanics
        shuffle.addActionListener(new EventHandler(this, m));
    }

    /*@Override
    public void actionPerformed(ActionEvent e) {

        List<List<JButton>> shuffledList = m.shuffleBoard(Lista);
        for (List<JButton> row : shuffledList) {
            for (JButton button : row) {
                board.add(button);
                board.revalidate();
                board.repaint();
            }
        }

    }*/


    public static void main(String[] args) {
        UI ui = new UI();
        Mechanics m = new Mechanics(ui);
        List<List<JButton>> newList = m.createList(ui.buttons);

        for (int i = 0; i < newList.size(); i++) {
            for (int j = 0; j < newList.size(); j++) {

            }
        }
    }
}









/* Tog bort följande för jag skapar vår gridlayout med en lista av listor, istället för en 2d array
  board.setLayout(new GridLayout (4,4));

        for (int i = 0; i < buttons.length; i++) {                      //for loopar som lägger in alla knappar i gridlayout
            for (int j = 0; j < buttons[i].length; j++) {
                board.add(buttons[i][j]);
            }
        }
 */
