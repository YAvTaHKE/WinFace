package ru;

import ru.GUI.MainFrame;

import java.awt.*;


public class WinFace {

    public static void main(String[] args) {

            EventQueue.invokeLater(() -> new MainFrame().setVisible(true));
    }
}
