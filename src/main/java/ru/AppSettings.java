package ru;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Set;

public class AppSettings {

    private static Properties prop = new Properties();
    private static AppSettings SINGLETON;
    static {
        SINGLETON = new AppSettings();
    }

    private AppSettings() {

    }

    public static void saveElement(String key, String val) {
        prop.setProperty(key, val);
    }

    public static String loadElement(String key) {
        return prop.getProperty(key);
    }


    public static void saveToFile () throws IOException{

        try (FileOutputStream output = new FileOutputStream("config.properties")){
            prop.store(output, null);
        }
    }

    public static Set loadFromFile() {

        try (FileInputStream input = new FileInputStream("config.properties")) {

             prop.load(input);
             Set set = prop.entrySet();

        return set;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
