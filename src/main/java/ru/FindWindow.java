package ru;

import com.sun.jna.Native;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.win32.StdCallLibrary;
import com.sun.jna.win32.W32APIOptions;
import ru.GUI.LogHelper;

public class FindWindow {
    static final int WM_SETTEXT = 0x000C;//from winuser.h
    public static final int WM_LBUTTONUP = 514;
    public static final int WM_LBUTTONDOWN = 513;
    private String windowName;
    private String pass;

    int count = 1;
    boolean flag = true;

    public static interface User32 extends StdCallLibrary {
        final User32 instance = (User32) Native.loadLibrary ("user32", User32.class,
                W32APIOptions.UNICODE_OPTIONS);

        //поиск окна по имени окна или класса
        WinDef.HWND FindWindow(String className, String windowName);

        //поиск дочернего окна
        WinDef.HWND FindWindowEx(
                WinDef.HWND hwndParent,     // дескриптор родительского окна
                WinDef.HWND hwndChildAfter, // дескриптор дочернего окна
                String lpszClass,   // указатель имени класса
                String lpszWindow   // указатель имени окна
        );
        //Отправка сообщения окну
        int SendMessage(WinDef.HWND hWnd, int Msg, int wParam, String lParam);

        //Здесь можно добавлять методы из Windows API
    }

    public FindWindow(String windowName, String pass) {
        this.pass = pass;
        this.windowName = windowName;
    }

        public FindWindow() {
        this.pass = "Rhfcjnf123";
        this.windowName = "OpenVPN - Token Password (conf-Kuznetsov_TEL_rutoken)";
    }

    public void findAndHook(){

            //находит окно по имени либо по классу
            WinDef.HWND hwnd = User32.instance.FindWindow(null, windowName);

            //окно не найдено
            if(hwnd == null) {
                if (flag) {
                    LogHelper.setText("Окно не найдено, ожидание ");
                    flag = false;
                } else {
                    LogHelper.setText(timeToString(count), true);
                    count++;
                }
            }
            //окно найдено
            else{
                count = 1;
                flag = true;
                LogHelper.setText("Найдено окно ввода пороля");

                WinDef.HWND hEdit = User32.instance.FindWindowEx(hwnd, null, "Edit", null);

                if (hEdit == null) {
                    LogHelper.setText("Не найдено поле ввода пороля");
                    return;
                }

                //Вводим пороль в текстовое поле
                int i = User32.instance.SendMessage(hEdit, WM_SETTEXT, 0, this.pass);

                if (i != 1) {
                    LogHelper.setText("Ошибка ввода пороля");
                    return;
                }

                WinDef.HWND hwndOK = User32.instance.FindWindowEx(hwnd, null, "Button", "OK");

                if (hwndOK == null) {
                    LogHelper.setText("Кнопка \"ОК\" не найдена");
                    return;
                }

                                //Имитируем нажатие кнопки "Ок"
                int down = User32.instance.SendMessage(hwndOK,WM_LBUTTONDOWN,1,null);
                int up = User32.instance.SendMessage(hwndOK,WM_LBUTTONUP,1,null);


                if (down == 0 & up == 0) {
                    LogHelper.setText("Пороль введен");
                } else {
                    LogHelper.setText("Ошибка нажатия кнопки \"ОК\"");
                }
            }

    }
    private static String timeToString(long secs) {
        long hour = secs / 3600,
                min = secs / 60 % 60,
                sec = secs / 1 % 60;
        return String.format("%02d:%02d:%02d", hour, min, sec);
    }
}
