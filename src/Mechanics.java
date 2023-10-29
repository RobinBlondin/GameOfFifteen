import javax.swing.*;
import java.util.*;

public class Mechanics {
    private final UI ui;
    private final Utils utils;


    public Mechanics(UI ui, Utils utils) {
        this.ui = ui;
        this.utils = utils;
    }

    /**
     * Finds a JButton with a specific text in a list of lists and return an int array
     * with x and y coordinates for that button.
     */
    public int[] getPositionOfButton(List<List<JButton>> inputList, String value) {
        for (int i = 0; i < inputList.size(); i++) {
            for (int j = 0; j < inputList.size(); j++) {
                String currentButtonValue = inputList.get(i).get(j).getText();
                if(currentButtonValue.equals(value)){
                    return new int[]{i, j};
                }
            }
        }
        return null;
    }


    /**
     * Takes JButton as input and compares coordinates with the empty button. If the input button is next to
     * the empty button this method will return true.
     */
    public boolean isButtonNextToEmpty(JButton clickedButton) {
        int[] emptyPos = getPositionOfButton(ui.getButtonList(), "");
        int[] clickedButtonPos = getPositionOfButton(ui.getButtonList(), clickedButton.getText());

        int rowDifference = Math.abs(emptyPos[0] - clickedButtonPos[0]);
        int columnDifference = Math.abs(emptyPos[1] - clickedButtonPos[1]);

        return (rowDifference == 0 && columnDifference == 1) || (rowDifference == 1 && columnDifference == 0);
    }


    /**
     * Iterates through a list of buttons and returns the first button that has no text.
     */
    public JButton findEmptyButton(List<List<JButton>> inputList) {
        for (List<JButton> list : inputList) {
            for(JButton button : list) {
                if(button.getText().isEmpty()){
                    return button;
                }
            }
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
