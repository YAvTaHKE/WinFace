package ru.GUI.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StopListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        StartListener.setFlag(false);
    }
}
