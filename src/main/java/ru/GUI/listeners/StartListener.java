package ru.GUI.listeners;

import ru.FindWindow;

public class StartListener{

    private static boolean flag = true;

    public static void setFlag(boolean b) {
        flag = b;
    }

    public static void action(String wind, String pass) {

        flag = true;
        FindWindow fw;
        if ("".equals(wind) || "".equals(pass)) {
           fw = new FindWindow();
        } else {
            fw = new FindWindow(wind, pass);
        }

        Thread t = new Thread(() -> {
            while (flag) {
                fw.findAndHook();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        });
        t.start();
    }
}
