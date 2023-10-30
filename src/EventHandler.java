import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

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
        JButton clickedButton = (JButton) e.getSource();
        List<List<JButton>> buttonList = ui.getButtonList();

        boolean buttonIsNumberTile = utils.createFlatList(buttonList).contains(clickedButton);
        boolean buttonIsShuffle = clickedButton.equals(ui.getShuffle());
        boolean buttonIsGodMode = clickedButton.equals(ui.getGodMode());

        if(buttonIsNumberTile) {
            if(m.isButtonNextToEmpty(clickedButton)) {
                m.swapButtons(buttonList, clickedButton);
                ui.revalidateRepaint();
            }
        } else if(buttonIsShuffle) {
            utils.setShuffledLabels(buttonList);
            ui.rewriteBoard();
            ui.revalidateRepaint();
        } else if(buttonIsGodMode) {
            utils.setSortedLabels(buttonList);
            ui.rewriteBoard();
            ui.revalidateRepaint();
        }
    }
}
