package ru.GUI;

import ru.GUI.listeners.StartListener;
import ru.GUI.listeners.StopListener;

import javax.swing.*;
import javax.swing.text.DefaultCaret;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Form extends JFrame {
    private JTextField textField1;
    private JTextField textField2;
    private JButton startButton;
    private JButton stopButton;
    private JTextArea textArea1;
    private JPanel rootPanel;

    public Form() {

        //задать пиктограмму для фрейма
        Image img = new ImageIcon("src\\main\\resources\\invisible26.png").getImage();
        setIconImage(img);
        new LogHelper(textArea1);

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

        LogHelper.setText("===========================Ready====(Введите имя окна и пороль)=======================");

        pack();

        startButton.addActionListener(new StartListener());
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startButton.setEnabled(false);
                stopButton.setEnabled(true);
            }
        });
        stopButton.addActionListener(new StopListener());
        stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startButton.setEnabled(true);
                stopButton.setEnabled(false);
            }
        });
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
