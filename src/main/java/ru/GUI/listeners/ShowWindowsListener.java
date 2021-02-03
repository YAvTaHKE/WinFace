package ru.GUI.listeners;

import ru.GUI.WindowsList;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShowWindowsListener implements ActionListener {

    private JFrame frame;

    public ShowWindowsListener(JFrame frame) {
        this.frame = frame;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        JFrame windowsList = new WindowsList(frame);
        windowsList.setVisible(true);
    }
}
