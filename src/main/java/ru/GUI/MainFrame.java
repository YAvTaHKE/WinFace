package ru.GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {

    JTextField loginField;
    JPasswordField passwordField;

    public MainFrame(){
        //Standart Frame operations
        this.setSize(220, 130);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setTitle("Logon");
        this.setLayout(null);
        this.setResizable(false);
        this.setLocationRelativeTo(null);

        //Creating and adding components
        JLabel lblLogon = new JLabel("Login:");
        lblLogon.setBounds(25, 11, 46, 20);
        this.add(lblLogon);

        JLabel lblPass = new JLabel("Pass:");
        lblPass.setBounds(25, 38, 35, 14);
        this.add(lblPass);

        loginField = new JTextField();
        loginField.setBounds(76, 11, 115, 20);
        this.add(loginField);
        loginField.setColumns(10);

        passwordField = new JPasswordField();
        passwordField.setBounds(76, 35, 115, 20);
        this.add(passwordField);

        JButton btnConnect = new JButton("Connect");
        btnConnect.setBounds(85, 65, 90, 24);
        this.add(btnConnect);
        //Установить кнопку по умолчанию
        JRootPane rootPane = SwingUtilities.getRootPane(btnConnect);
        rootPane.setDefaultButton(btnConnect);

        btnConnect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

            }
        });
    }
}
