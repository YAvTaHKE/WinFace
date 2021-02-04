package ru;

import java.io.*;
import java.util.*;

public class AppSettings {

    private final static String configFile = "config.ini";
    private static final AppSettings SINGLETON = new AppSettings();
    private static LinkedHashMap<String, String> map;

    private AppSettings() {
        map = new LinkedHashMap<>();
    }

    public static void saveElement(String key, String val) {
        map.put(key, val);
        saveToFile();
    }

    public static void saveToFile (){

        try (BufferedWriter output = new BufferedWriter(
                new FileWriter(configFile))) {
            map.forEach((k, v) ->
            {
                try {
                    output.write(k);
                    output.newLine();
                    output.write(v);
                    output.newLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static LinkedHashMap<String, String> loadFromFile() {

        try(BufferedReader input = new BufferedReader(
                new FileReader(configFile))){
            while (input.ready()) {
                map.put(input.readLine(), input.readLine());
            }
        } catch (FileNotFoundException e) {
            LogHelper.setText("Файл с настройками не найден");
            return map;
        } catch (IOException e) {
            e.printStackTrace();
            return map;
        }
        return map;
    }
}
