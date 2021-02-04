package ru;

import ru.GUI.Form;


import javax.swing.JFrame;
import java.awt.EventQueue;


/**
 * @version 1.1 2021-02-04
 * @author Moerti
 *
 * Программа каждую секунду сканирует окна Windows в поисках целевого окна для ввода пороля.
 * Если окно найдено, программа вводит пороль и эмулирует нажатие кнопки "ОК"
 */

public class WinFace {

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            JFrame mainFrame = new Form();
            mainFrame.setVisible(true);
        });
    }
}
