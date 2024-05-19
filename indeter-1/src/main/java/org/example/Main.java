package org.example;

import java.util.Arrays;

class Principal implements Runnable {
    private static int cont = 0;
    /*Todos los hilos tienen que ver el cerrojo*/
    private static final Object cerrojo = new Object();

    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            /*Este objeto tiene que ser statica si la variable es statica o el que sea*/
            /*Region critica controlada*/
            synchronized (cerrojo){ //hay una cola y los demas duermen
                cont++;
            }
        }
    }

    public static int getCont(){
       return cont;
    }
}

public class Main {
    public static void main(String[] args) {
        System.out.println(Principal.getCont());

        int runtime = Runtime.getRuntime().availableProcessors();
        Thread[] threads = new Thread[runtime];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(new Principal());
            threads[i].start();
        }

        Arrays.stream(threads).forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        System.out.println(Principal.getCont());

    }
}