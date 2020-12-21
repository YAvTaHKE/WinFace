package ru;

import com.sun.jna.Native;
import com.sun.jna.platform.win32.WinDef.HWND;
import com.sun.jna.win32.StdCallLibrary;
import com.sun.jna.win32.W32APIOptions;

public class WinFace {
    public static interface User32 extends StdCallLibrary
    {
        final User32 instance = (User32) Native.loadLibrary ("user32", User32.class,
                W32APIOptions.UNICODE_OPTIONS);
        HWND FindWindow(String className, String windowName);
        boolean SetWindowText(HWND hwnd, String newText);
    }

    public static void main(String[] args) {
        if(args.length != 2) {
            System.out.println("Нужно два аргумента: имя окна (или класс) и новое имя");
            return;
        }
        HWND hwnd = User32.instance.FindWindow(args[0], null);
        if(hwnd == null) {
            hwnd = User32.instance.FindWindow(null, args[0]);
        }
        if(hwnd == null) {
            System.out.println("Окно не найдено");
            return;
        }
        System.out.println("Нашли окно: "+hwnd);
        boolean result = User32.instance.SetWindowText(hwnd, args[1]);
        System.out.println(result ? "Успешно" : "Упс");
    }
}
