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


    public void solvePuzzle() {

    }

    public void validatePuzzle() {

    }


}
