import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Mechanics {
    private UI ui;

    public Mechanics(UI ui) {
        this.ui = ui;
    }

    public Mechanics() {}   //skapde default konstruktor

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

    public void solvePuzzle() {

    }

    public void validatePuzzle() {

    }


}
