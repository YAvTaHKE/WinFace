package ru.GUI.listeners;

import ru.FindWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartListener implements ActionListener {

    private static boolean flag = true;

    public static void setFlag(boolean b) {
        flag = b;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        flag = true;
        FindWindow fw = new FindWindow();
        Thread t = new Thread(() -> {
            while (flag) {
                fw.findAndHook();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        });
        t.start();
    }
}
