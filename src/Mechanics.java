import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

public class Mechanics extends JFrame {
    private final UI ui;
    private final List<String> numberList = Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "");

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

    public void sortList(List<List<JButton>> inputList) {
        List<JButton> buttons = createFlatList(inputList);
        for (int i = 0; i < buttons.size(); i++) {
                buttons.get(i).setText(numberList.get(i));
        }
        ui.Lista = create2dList(buttons);
    }

    public void shuffleList(List<List<JButton>> inputList) {
        List<JButton> buttons = createFlatList(inputList);
        List<String> shuffledList = createSolvableBoard(new ArrayList<>(numberList));

        for (int i = 0; i < buttons.size(); i++) {
            buttons.get(i).setText(shuffledList.get(i));
        }

        ui.Lista = create2dList(buttons);
    }

    public void initializeButtons(JButton[][] buttons) {
        int counter = 1;
        for (int i = 0; i < buttons.length; i++) {
            for (int j = 0; j < buttons[i].length; j++) {
                buttons[i][j] = new JButton(counter == 16 ? "" : String.valueOf(counter));
                buttons[i][j].addActionListener(new EventHandler(ui, this));
                counter++;
            }
        }
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
        validatePuzzle(list);
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

    /**
     * tar in en lista JButton, denna jämförs med vår final lista NumberList, om de är lika (alltså spelet är löst) så får man
     * notifikation om detta
     * @param inputList
     */
    public void validatePuzzle(List<JButton> inputList) {

        List<String> buttonNamesList = new ArrayList<>();                      //skapar en ny arraylist som ska stora namnet på alla knappar
            for (JButton button : inputList) {
                buttonNamesList.add(button.getText());
            }

        if (buttonNamesList.equals(numberList)) {
            victoryBox();
        }

    }

    public void victoryBox() {

        JPanel jp = new JPanel();
        JLabel jl = new JLabel("You want to play again?");
        JButton jb = new JButton("Ofcourse");

        setVisible(true);
        setSize(200,100);
        setLocationRelativeTo(null);           //kanske borde vara bound till programmet?
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        add(jp);
        jp.setLayout(new FlowLayout());
        jp.add(jl);
        jp.add(jb);

            jb.addActionListener(l -> {            //lambda
                this.shuffleList(ui.Lista);
                dispose();
            });

        }

    }





