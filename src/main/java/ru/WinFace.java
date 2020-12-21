package ru;

import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.HWND;
import com.sun.jna.platform.win32.WinUser;
import com.sun.jna.win32.StdCallLibrary;
import com.sun.jna.win32.W32APIOptions;


public class WinFace {

    static final int WM_SETTEXT = 0x000C;//from winuser.h
    public static final int WM_LBUTTONUP = 514;
    public static final int WM_LBUTTONDOWN = 513;
    private final static String windowName = "OpenVPN - Token Password (conf-Kuznetsov_TEL_rutoken)";

    public static interface User32 extends StdCallLibrary {
        final User32 instance = (User32) Native.loadLibrary ("user32", User32.class,
                W32APIOptions.UNICODE_OPTIONS);

        //поиск окна по имени окна или класса
        HWND FindWindow(String className, String windowName);

        //поиск дочернего окна
        HWND FindWindowEx(
                HWND hwndParent,     // дескриптор родительского окна
                HWND hwndChildAfter, // дескриптор дочернего окна
                String lpszClass,   // указатель имени класса
                String lpszWindow   // указатель имени окна
        );
        //Отправка сообщения окну
        int SendMessage(HWND hWnd, int Msg, int wParam, String lParam);

        //Здесь можно добавлять методы из Windows API
    }

    public static void main(String[] args) {

        while (true) {
            //находит окно по имени либо по классу
            HWND hwnd = User32.instance.FindWindow(null, windowName);
            if(hwnd == null) {
                System.out.println("Окно не найдено");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else{
                System.out.println("Нашли окно ввода пороля "+hwnd);

                HWND hEdit = User32.instance.FindWindowEx(hwnd, null, "Edit", null);
                System.out.println("Нашли дочернее окно Edit "+hEdit);
                //Вводим пороль в текстовое поле
                User32.instance.SendMessage(hEdit, WM_SETTEXT, 0, "Rhfcjnf123");

                HWND hwndOK = User32.instance.FindWindowEx(hwnd, null, "Button", "OK");
                System.out.println("Нашли дочернее окно кнопки ОК "+ hwndOK);
                //Имитируем нажатие кнопки "Ок"
                User32.instance.SendMessage(hwndOK,WM_LBUTTONDOWN,1,null);
                User32.instance.SendMessage(hwndOK,WM_LBUTTONUP,1,null);
            }
        }
    }
}
