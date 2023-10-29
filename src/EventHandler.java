import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class EventHandler implements ActionListener {
    private final UI ui;
    private final Utils utils;
    private final Mechanics m;

    public EventHandler(UI ui, Mechanics m) {
        this.ui = ui;
        this.m = m;
    }
    @Override
    public void actionPerformed(ActionEvent e) {

        if(m.createFlatList(ui.Lista).contains((JButton) e.getSource())) {
            if(m.isButtonNextToEmpty((JButton) e.getSource())) {
                ui.Lista = m.swapButtons(ui.Lista, (JButton) e.getSource());
                revalidateRepaint();
            }
        } else if(e.getSource().equals(ui.shuffle)) {
            m.shuffleList(ui.Lista);
            rewriteBoard();
            revalidateRepaint();
        } else if (e.getSource().equals(ui.godMode)) {
            m.sortList(ui.Lista);
            rewriteBoard();
            revalidateRepaint();
        }
    }

    public void revalidateRepaint() {
        ui.board.revalidate();
        ui.board.repaint();
    }

    public void rewriteBoard() {
        ui.board.removeAll();
        for (List<JButton> row : ui.Lista) {
            for (JButton button : row) {
                ui.board.add(button);
            }
        }
    }
}
