package ru.GUI;

import ru.FindWindow;

import javax.swing.*;
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

        super("LogOn");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        //this.setLocationRelativeTo(null);
        setContentPane(rootPanel);

        //получить размеры экрана
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;
        System.out.println(screenHeight);
        System.out.println(screenWidth);

        //установить положение фрейма
        setLocation(screenWidth / 3, screenHeight / 3);

        //задать пиктограмму для фрейма
        Image img = new ImageIcon("C:\\GIT\\Prog\\WinFace\\src\\main\\resources\\invisible26.png").getImage();
        setIconImage(img);

        pack();

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Thread t = new Thread(() -> {
                    while (true) {
                        new FindWindow().findAndHook();
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException ex) {
                            ex.printStackTrace();
                        }
                    }
                });
                t.start();
            }
        });
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
