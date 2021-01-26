package ru.GUI;

import ru.GUI.listeners.SaveListener;
import ru.GUI.listeners.StartListener;
import ru.GUI.listeners.StopListener;

import javax.swing.*;
import javax.swing.text.DefaultCaret;
import java.awt.*;

public class Form extends JFrame {
    private JTextField textField1;
    private JTextField textField2;
    private JButton startButton;
    private JButton stopButton;
    private JTextArea textArea1;
    private JPanel rootPanel;
    private JButton saveButton;

    public Form() {

        //задать пиктограмму для фрейма
        Image img = new ImageIcon("src\\main\\resources\\invisible26.png").getImage();
        setIconImage(img);

        setTitle("LogOn");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(rootPanel);

        //получить размеры экрана
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;

        //установить положение фрейма
        setLocation(screenWidth / 3, screenHeight / 3);
        stopButton.setEnabled(false);

        //установить автоматическую прокрутку вниз
        DefaultCaret caret = (DefaultCaret)textArea1.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);

        textArea1.setText("===========================Ready====(Введите имя окна и пороль)=======================");
        new LogHelper(textArea1);

        pack();

        startButton.addActionListener(e -> {
            startButton.setEnabled(false);
            stopButton.setEnabled(true);
            StartListener.action(textField1.getText(), textField2.getText());
        });
        startButton.addActionListener(e -> {

        });
        stopButton.addActionListener(new StopListener());
        stopButton.addActionListener(e -> {
            startButton.setEnabled(true);
            stopButton.setEnabled(false);
        });

        saveButton.addActionListener(new SaveListener());
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
