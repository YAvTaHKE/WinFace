package ru;

import javax.swing.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LogHelper {

    private static JTextArea logArea;
    private static StringBuffer sb;
    private static final SimpleDateFormat formater = new SimpleDateFormat("HH:mm:ss");

    public LogHelper(JTextArea logArea) {
        LogHelper.logArea = logArea;
        sb = new StringBuffer(logArea.getText());
    }

    //Установить текст в JTextArea основного окна
    public static void setText(String str){
            logArea.setText(sb.append("\n").append(formater.format(new Date())).append(" - ").append(str).toString());
    }

    //Установить текст в JTextArea основного окна
    public static void setText(String str, boolean b){
        if (b){
            sb.delete(sb.lastIndexOf(" ")+1, sb.length());
            logArea.setText(sb.append(str).toString());
        }
    }
}
