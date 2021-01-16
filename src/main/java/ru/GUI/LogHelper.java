package ru.GUI;

import javax.swing.*;

public class LogHelper {

    private static JTextArea logArea;
    private static StringBuffer sb;

    public LogHelper(JTextArea logArea) {
        this.logArea = logArea;
        sb = new StringBuffer();
    }

    public static StringBuffer getText(){
        return new StringBuffer(logArea.getText());
    }

    public static void setText(String str){
        logArea.setText(sb.append(str).toString());
    }
}
