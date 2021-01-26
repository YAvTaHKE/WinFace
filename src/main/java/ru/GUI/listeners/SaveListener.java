package ru.GUI.listeners;

import ru.AppSettings;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Set;

public class SaveListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {

        AppSettings.saveElement("Window_" + Math.random(), "Password_1");



        try {
            Path path = Files.createFile(Paths.get("config.properties"));
            System.out.println("Создан файл: " + path);
        } catch (FileAlreadyExistsException ioException) {
            System.out.println("Файл существует");
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        Set set = AppSettings.loadFromFile();
        System.out.println(set);
    }
}
