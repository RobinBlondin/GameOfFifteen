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
     * Takes a list of lists as input and flatten it to a 1d list.
     */
    public List<JButton> createFlatList(List<List<JButton>> inputList) {
        List<JButton> flattenedList = new ArrayList<>();
        for(List<JButton> list : inputList) {
            flattenedList.addAll(list);
        }
        return flattenedList;
    }


    /**
     * Takes a list of strings as input and shuffles it until it solvable.
     */
    public List<String> shuffleListToSolvableOrder(List<String> list) {
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
     * Takes a list of lists as input replace the text of each button in the order of the
     * sorted list.
     */
    public void setSortedLabels(List<List<JButton>> inputList) {
        updateButtonLabels(numberList, inputList);
    }


    /**
     * Takes a list of lists as input and replace the text of the buttons
     * with a new shuffled order.
     */
    public void setShuffledLabels(List<List<JButton>> inputList) {
        List<String> shuffledList = shuffleListToSolvableOrder(new ArrayList<>(numberList));
        updateButtonLabels(shuffledList, inputList);
    }


    /**
     * Updates the labels of the buttons in a list of Buttons with values from a list of strings.
     */
    public void updateButtonLabels(List<String> labelList, List<List<JButton>> inputList) {
        for (int i = 0; i < inputList.size(); i++) {
            for (int j = 0; j < inputList.size(); j++) {
                JButton currentButton = inputList.get(i).get(j);
                int index = i * inputList.size() + j;

                currentButton.setText(labelList.get(index));
                ui.resetButtonColors(index, currentButton);
            }
        }
    }


    /**
     * Takes a list of buttons as input and compares the text of each button with the
     * text of the buttons in the numberList. If the lists are equal the player has won.
     */
    public void validatePuzzle (List<JButton> inputList) {

        List<String> buttonNamesList = new ArrayList<>();
        for (JButton button : inputList) {
            buttonNamesList.add(button.getText());
        }

        if (buttonNamesList.equals(numberList)) {
            victoryBox();
        }
    }

    /**
     * Creates a new JFrame with a victory message, and a button to start a new game.
     */
    public void victoryBox() {

        JPanel jp = new JPanel();
        JLabel jl = new JLabel("Good Job! You want to play again?");
        JButton jb = new JButton("Of course");

        setVisible(true);
        setSize(230,100);
        setLocationRelativeTo(ui.getBoard());
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        add(jp);
        jp.setLayout(new FlowLayout());
        jp.add(jl);
        jp.add(jb);

        jb.addActionListener(l -> {            //lambda
            setShuffledLabels(ui.getButtonList());
            dispose();
        });
    }

}
