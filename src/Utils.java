import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Utils {
    UI ui;
    private final List<String> numberList = Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "");

    public Utils(UI ui) {
        this.ui = ui;
    }

    public List<List<JButton>> createListFromArray(JButton[][] array) {
        List<List<JButton>> list = new ArrayList<>();
        for (JButton[] jButtons : array) {
            list.add(Arrays.asList(jButtons));
        }
        return list;
    }

    public void sortList(List<List<JButton>> inputList) {
        List<JButton> buttons = createFlatList(inputList);
        for (int i = 0; i < buttons.size(); i++) {
            buttons.get(i).setText(numberList.get(i));
        }
        ui.setButtonList(create2dList(buttons));
    }

    public void shuffleList(List<List<JButton>> inputList) {
        List<JButton> buttons = createFlatList(inputList);
        List<String> shuffledList = createSolvableBoard(new ArrayList<>(numberList));

        for (int i = 0; i < buttons.size(); i++) {
            buttons.get(i).setText(shuffledList.get(i));
        }

        ui.setButtonList(create2dList(buttons));
    }

    /**
     * Takes a 2d list as input and returns it as a normal list.
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
     * Takes a list of strings as input and shuffles it until it is solvable.
     */
    public List<String> createSolvableBoard(List<String> list) {
        while(true) {
            Collections.shuffle(list.subList(0, 15));
            int inversions = 0;
            for (int i = 0; i < list.size() - 2; i++) {
                for (int j = i + 1; j < list.size() - 1; j++) {
                    if (Integer.parseInt(list.get(i)) > Integer.parseInt(list.get(i))) {
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

    public void validatePuzzle() {

    }
}
