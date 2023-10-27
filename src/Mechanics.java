import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Mechanics {
    private UI ui;

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

    public List<JButton> shuffleBoard() {
        return null;
    }

    public void solvePuzzle() {

    }

    public void validatePuzzle() {

    }


}
