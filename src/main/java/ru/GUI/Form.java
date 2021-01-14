package ru.GUI;

import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;
import ru.FindWindow;

import javax.swing.*;
import javax.swing.border.TitledBorder;
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
        this.setLocationRelativeTo(null);
        setContentPane(rootPanel);
        //this.setLocationByPlatform(true);
        pack();
        setVisible(true);

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
