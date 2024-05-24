package org.example;

public class Main2 implements Runnable {
    @Override
    public void run() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {}
        System.out.println(Main2.class.getName() + " Valor static: " + Main1.PI);
    }
}
