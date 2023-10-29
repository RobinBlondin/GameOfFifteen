import javax.swing.*;
import java.util.*;

public class Mechanics {
    private final UI ui;
    private final List<String> numberList = Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "");

    public Mechanics(UI ui) {
        this.ui = ui;
    }

    /**
     * Finds a JButton with a specific text in a list of lists and return an int array
     * with x and y coordinates for that button.
     */
    public int[] getPositionOfButton(List<List<JButton>> inputList, String value) {
        int x = 0;
        for(List<JButton> list: inputList) {
            int y = 0;
            for(JButton button : list) {
                if(button.getText().equals(value)){
                    /*System.out.println(value + ":" + x + ", " + y);*/
                    return new int[]{x, y};
                }
                y++;
            }
            x++;
        }
        return null;
    }

    /**
     * Takes a 2d list and a button as input and swaps the text of the input button with the empty button
     * and returns the new updated list.
     */
    public List<List<JButton>> swapButtons(List<List<JButton>> inputList, JButton clickedButton) {
        List<JButton> list = utils.createFlatList(inputList);

        int indexClickedButton = list.indexOf(clickedButton);
        int indexOfEmptyButton = list.indexOf(findEmptyButton(inputList));

        list.get(indexOfEmptyButton).setText(clickedButton.getText());
        list.get(indexClickedButton).setText("");
        return utils.create2dList(list);
    }
}
