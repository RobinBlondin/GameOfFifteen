import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class EventHandler extends JFrame implements ActionListener {
    UI ui;
    Mechanics m;

    //Skapade en konstruktor där man kan skicka in en UI och en Mechanics
    public EventHandler(UI ui, Mechanics m) {
        this.ui = ui;
        this.m = m;
    }
    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource().equals(ui.shuffle)) {
            List<List<JButton>> shuffledList = m.shuffleBoard(ui.Lista);
            for (List<JButton> row : shuffledList) {
                for (JButton button : row) {
                    ui.board.add(button);
                    ui.board.revalidate();
                    ui.board.repaint();
                }
            }
        }
    }
}
