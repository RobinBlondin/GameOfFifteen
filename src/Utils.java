import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Utils extends JFrame {
    UI ui;
    private final List<String> numberList = Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "");

    public Utils(UI ui) {
        this.ui = ui;
    }

    /**
     * Takes a 2d array as input and creates and returns a list of lists.
     */
    public List<List<JButton>> createListFromArray(JButton[][] array) {
        List<List<JButton>> list = new ArrayList<>();
        for (JButton[] jButtons : array) {
            list.add(Arrays.asList(jButtons));
        }
        return list;
    }


    /**
     * Takes a list of lists as input replace the text of each button in the order of the
     * sorted list.
     */
    public void sortList(List<List<JButton>> inputList) {
        List<JButton> buttons = createFlatList(inputList);
        for (int i = 0; i < buttons.size(); i++) {
            buttons.get(i).setText(numberList.get(i));
            ui.resetButtonColor(i, buttons.get(i));
        }
        ui.setButtonList(create2dList(buttons));
    }


    /**
     * Takes a list of strings as input and shuffles it until it is solvable.
     */
    public List<String> createSolvableOrder(List<String> list) {
        while(true) {
            Collections.shuffle(list.subList(0, 15));
            int inversions = 0;
            for (int i = 0; i < list.size() - 2; i++) {
                for (int j = i + 1; j < list.size() - 1; j++) {
                    if (Integer.parseInt(list.get(i)) > Integer.parseInt(list.get(j))) {
                        inversions++;
                    }
                }
            }
            if(inversions % 2 == 0) {
                break;
            }
        }
        return list;
    }


    /**
     * Takes a list of lists as input and replace the text of the buttons
     * with a new shuffled order.
     */
    public void shuffleList(List<List<JButton>> inputList) {
        List<JButton> buttons = createFlatList(inputList);
        List<String> shuffledList = createSolvableOrder(new ArrayList<>(numberList));

        for (int i = 0; i < buttons.size(); i++) {
            buttons.get(i).setText(shuffledList.get(i));
            ui.resetButtonColor(i, buttons.get(i));
        }

        ui.setButtonList(create2dList(buttons));
    }

    /**
     * Takes a list of lists as input and flatten it to a 1d list.
     */
    public List<JButton> createFlatList(List<List<JButton>> inputList) {
        List<JButton> list = new ArrayList<>();
        for(List<JButton> l : inputList) {
            list.addAll(l);
        }
        return list;
    }

    /**
     * Takes a list of JButton as input and creates and returns a 2d list.
     */
    public List<List<JButton>> create2dList(List<JButton> flatList) {
        List<List<JButton>> list = new ArrayList<>();
        for (int i = 0; i < flatList.size(); i += 4) {
            list.add(flatList.subList(i, i+4).stream().toList());
        }
        return list;
    }


    /**
     * Takes a list of buttons as input and compares the text of each button with the
     * text of the buttons in the numberList. If the lists are equal the player has won.
     */
    public void validatePuzzle (List<JButton> inputList) {

        List<String> buttonNamesList = new ArrayList<>();                      //skapar en ny arraylist som ska stora namnet på alla knappar
        for (JButton button : inputList) {
            buttonNamesList.add(button.getText());
        }

        if (buttonNamesList.equals(numberList)) {
            victoryBox();
        }
    }

    /**
     * Creates a new JFrame with a victory message and a button to start a new game.
     */
    public void victoryBox() {

        JPanel jp = new JPanel();
        JLabel jl = new JLabel("Good Job! You want to play again?");
        JButton jb = new JButton("Ofcourse");

        setVisible(true);
        setSize(230,100);
        setLocationRelativeTo(ui.getBoard());           //kanske borde vara bound till programmet?
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        add(jp);
        jp.setLayout(new FlowLayout());
        jp.add(jl);
        jp.add(jb);

        jb.addActionListener(l -> {            //lambda
            this.shuffleList(ui.getButtonList());
            dispose();
        });
    }

}
