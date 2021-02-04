package ru.GUI;

import ru.AppSettings;
import ru.GUI.listeners.ShowWindowsListener;
import ru.GUI.listeners.StartListener;
import ru.GUI.listeners.StopListener;
import ru.LogHelper;

import javax.swing.*;
import javax.swing.text.DefaultCaret;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class Form extends JFrame {
    private JButton startButton;
    private JButton stopButton;
    private JTextArea textArea1;
    private JPanel rootPanel;
    private JButton saveButton;
    private JComboBox comboBox1;
    private JComboBox comboBox2;
    private JButton showWindowsButton;


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

        updateComboBox();
        pack();

        startButton.addActionListener(e -> {
            startButton.setEnabled(false);
            stopButton.setEnabled(true);
            StartListener.action((String)comboBox1.getSelectedItem(), (String)comboBox2.getSelectedItem());
        });

        stopButton.addActionListener(new StopListener());
        stopButton.addActionListener(e -> {
            startButton.setEnabled(true);
            stopButton.setEnabled(false);
        });

        //при нажатии кнопки Save
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AppSettings.saveElement((String)comboBox1.getSelectedItem(), (String)comboBox2.getSelectedItem());
                updateComboBox();
            }
        });

        //при нажатии на кнопку zWin
        showWindowsButton.addActionListener(new ShowWindowsListener(this));
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }

    private void updateComboBox(){
        //Загрузить последние настройки
        LinkedHashMap<String, String> map = AppSettings.loadFromFile();
        //Сортировка реверс
        LinkedHashMap<String, String> reverseMap = reverse(map);

        //Очистить combobox
        comboBox1.removeAllItems();
        comboBox2.removeAllItems();
        //Добавить данные в комбобокс
        reverseMap.forEach((k, v)->{
            comboBox1.addItem(k);
            comboBox2.addItem(v);
        });
    }

    public void setWindowName(String wName) {
        comboBox1.setSelectedItem(wName);
    }

    public static <K, V> LinkedHashMap<K, V> reverse(LinkedHashMap<K, V> map)
    {
        LinkedHashMap<K, V> reversedMap = new LinkedHashMap<K, V>();

        ListIterator<Map.Entry<K, V>> it = new ArrayList<>(map.entrySet()).listIterator(map.entrySet().size());

        while (it.hasPrevious())
        {
            Map.Entry<K, V> el = it.previous();
            reversedMap.put(el.getKey(), el.getValue());
        }

        return reversedMap;
    }

}
