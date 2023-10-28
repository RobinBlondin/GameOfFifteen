import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
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

        if(m.createFlatList(ui.Lista).contains((JButton) e.getSource())) {
            if(m.isButtonNextToEmpty((JButton) e.getSource())) {
                ui.Lista = m.swapButtons(ui.Lista, (JButton) e.getSource());
                ui.board.revalidate();
                ui.board.repaint();
            }
        } else if(e.getSource().equals(ui.shuffle)) {
            m.shuffleList(ui.Lista);
            ui.board.removeAll();
            for (List<JButton> row : ui.Lista) {
                for (JButton button : row) {
                    ui.board.add(button);
                }
            }
            ui.board.revalidate();
            ui.board.repaint();
        } else if (e.getSource().equals(ui.godMode)) {
            m.sortList(ui.Lista);
            ui.board.removeAll();
            for (List<JButton> row : ui.Lista) {
                for (JButton button : row) {
                    ui.board.add(button);
                }
            }
            ui.board.revalidate();
            ui.board.repaint();
        } else if (e.getSource().equals(ui.godMode)) {
            m.initializeButtons(ui.buttons);
            ui.board.removeAll();
            ui.board.revalidate();
            ui.board.repaint();
            for (List<JButton> buttonList : ui.sortedList) {
                for (JButton button : buttonList) {
                    ui.board.add(button);
                    ui.board.revalidate();
                    ui.board.repaint();
                }
            }
            //inaktiverar en button


        }
    }
}
