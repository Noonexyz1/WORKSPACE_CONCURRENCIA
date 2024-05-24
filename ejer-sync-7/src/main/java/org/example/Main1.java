package org.example;

public class Main1 implements Runnable{
    public static double PI = 3.141515;

    @Override
    public void run() {
        System.out.println(Main1.class.getName() + " Valor static: " + Main1.PI);
    }
}
