package ru;

import com.sun.jna.Native;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.win32.StdCallLibrary;
import com.sun.jna.win32.W32APIOptions;

public class FindWindow {
    static final int WM_SETTEXT = 0x000C;//from winuser.h
    public static final int WM_LBUTTONUP = 514;
    public static final int WM_LBUTTONDOWN = 513;
    private String windowName;
    private String pass;

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

    public FindWindow(String pass) {
        this.pass = pass;
        this.windowName = "OpenVPN - Token Password (conf-Kuznetsov_TEL_rutoken)";
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
                System.out.println("Окно не найдено");
            }
            //окно найдено
            else{
                System.out.println("Нашли окно ввода пороля "+hwnd);

                WinDef.HWND hEdit = User32.instance.FindWindowEx(hwnd, null, "Edit", null);
                System.out.println("Нашли дочернее окно Edit "+hEdit);
                //Вводим пороль в текстовое поле
                User32.instance.SendMessage(hEdit, WM_SETTEXT, 0, this.pass);

                WinDef.HWND hwndOK = User32.instance.FindWindowEx(hwnd, null, "Button", "OK");
                System.out.println("Нашли дочернее окно кнопки ОК "+ hwndOK);
                //Имитируем нажатие кнопки "Ок"
                User32.instance.SendMessage(hwndOK,WM_LBUTTONDOWN,1,null);
                User32.instance.SendMessage(hwndOK,WM_LBUTTONUP,1,null);
            }

    }
}
