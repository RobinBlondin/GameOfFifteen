import javax.swing.*;
import java.util.*;

public class Mechanics {
    private final UI ui;

    public Mechanics(UI ui) {
        this.ui = ui;
    }

    public List<List<JButton>> createList(JButton[][] array) {
        List<List<JButton>> list = new ArrayList<>();
        for (JButton[] jButtons : array) {
            list.add(Arrays.asList(jButtons));
        }
        return list;
    }

    public List<List<JButton>> shuffleBoard(List<List<JButton>> list) {  //tar in en lista av listor
        List<JButton> shuffleList = new ArrayList<>();                  //gör om detta till en shufflelist, "plattar ut" listan till 1d arraylist
        for (List<JButton> row : list) {
            shuffleList.addAll(row);                                    //lägger in alla buttons i denna shufflelist
        }
        Collections.shuffle(shuffleList.subList(0, 15));                            //shufflar om dessa, randomizar dem med collection
        //La till att man endast shufflar de numrerade knapparna

        int index = 0;
        for (List<JButton> row : list) {                        //återskapar originallistan av listor efter shuffling
            for (int i = 0; i < row.size(); i++) {
                row.set(i, shuffleList.get(index));          //lägger tillbaka varje button i shufflelistan i originallistan
                index++;
            }
        }
        return list;
    }

    /**
     * Takes a 2d list and a button as input and swaps the text of the input button with the empty button
     * and returns the new updated list.
     */
    public List<List<JButton>> swapButtons(List<List<JButton>> inputList, JButton clickedButton) {
        List<JButton> list = createFlatList(inputList);

        int indexClickedButton = list.indexOf(clickedButton);
        int indexOfEmptyButton = list.indexOf(findEmptyButton(inputList));

        list.get(indexOfEmptyButton).setText(clickedButton.getText());
        list.get(indexClickedButton).setText("");
        return create2dList(list);
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
     * Takes JButton as input and compares coordinates with the empty button. If the input button is next to
     * the empty button this method will return true.
     */
    public boolean isButtonNextToEmpty(JButton clickedButton) {
        int[] emptyPos = getPositionOfButton(ui.Lista, "");
        int[] clickedButtonPos = getPositionOfButton(ui.Lista, clickedButton.getText());

        int rowDifference = Math.abs(emptyPos[0] - clickedButtonPos[0]);
        int columnDifference = Math.abs(emptyPos[1] - clickedButtonPos[1]);

        return (rowDifference == 0 && columnDifference == 1) || (rowDifference == 1 && columnDifference == 0);
    }

    public void solvePuzzle() {

    }

    public void validatePuzzle() {

    }


}
