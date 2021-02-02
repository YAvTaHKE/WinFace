package ru.GUI;

import ru.FindWindow;

import javax.swing.*;
import java.awt.*;

public class WindowsList extends JFrame{
    private JList<String> list1;
    private JPanel panel1;
    private DefaultListModel<String> listModel;

    public WindowsList(){
        //setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Windows name list");
        setSize(new Dimension(400, 310));
        listModel = new DefaultListModel();
        list1.setModel(listModel);
        setContentPane(panel1);

        //получить размеры экрана
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;

        //установить положение фрейма
        setLocation(screenWidth / 3 + 608, screenHeight / 3);

        FindWindow.getWindows().forEach((k, v) ->
                listModel.addElement(v.trim()));
        //pack();
    }
}
