package ru;

import ru.GUI.Form;

import javax.swing.*;
import java.awt.*;

/**
 * @version 1.1 2021-01-16
 * @author Moerti
 */

public class WinFace {

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            JFrame mainFrame = new Form();
            mainFrame.setVisible(true);
        });
    }
}
