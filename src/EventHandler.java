import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EventHandler implements ActionListener {
    private final UI ui;
    private final Utils utils;
    private final Mechanics m;

    public EventHandler(UI ui, Utils utils, Mechanics m) {
        this.ui = ui;
        this.utils = utils;
        this.m = m;
    }
    @Override
    public void actionPerformed(ActionEvent e) {

        if(utils.createFlatList(ui.getButtonList()).contains((JButton) e.getSource())) {
            if(m.isButtonNextToEmpty((JButton) e.getSource())) {
                ui.setButtonList(m.swapButtons(ui.getButtonList(), (JButton) e.getSource()));
                ui.revalidateRepaint();
            }
        } else if(e.getSource().equals(ui.getShuffle())) {
            utils.shuffleList(ui.getButtonList());
            ui.rewriteBoard();
            ui.revalidateRepaint();
        } else if (e.getSource().equals(ui.getGodMode())) {
            utils.sortList(ui.getButtonList());
            ui.rewriteBoard();
            ui.revalidateRepaint();
        }
    }
}
