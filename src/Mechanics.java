import javax.swing.*;
import java.util.List;

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
    public JButton findButtonByText(List<List<JButton>> inputList, String buttonValue) {
        for (List<JButton> list : inputList) {
            for(JButton button : list) {
                if(button.getText().equals(buttonValue)) {
                    return button;
                }
            }
        }
        return null;
    }


    /**
     * Takes a 2d list, and a button as input and swaps the text of the input button with the empty button
     * and returns the new updated list.
     */
    public void swapButtons(List<List<JButton>> inputList, JButton button) {
        List<JButton> flattenedList = utils.createFlatList(inputList);
        JButton clickedButton = findButtonByText(inputList, button.getText());
        JButton emptyButton = findButtonByText(inputList, "");

        swapButtonText(clickedButton, emptyButton);
        ui.swapButtonColor(clickedButton, emptyButton);

        utils.validatePuzzle(flattenedList);
    }

    public void swapButtonText(JButton button1, JButton button2) {
        String temp = button1.getText();
        button1.setText(button2.getText());
        button2.setText(temp);
    }

}
