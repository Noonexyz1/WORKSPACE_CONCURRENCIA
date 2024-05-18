package org.example;

import java.util.Arrays;

class Principal extends Thread {
    private int id;

    public Principal(int id) {
        this.id = id;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " estado de las var: " + id);
    }
}

public class Main {
    public static void main(String[] args) {
        metodoUno();
    }

    public static void metodoUno(){
        System.out.println("Hilo principal iniciado");

        Principal[] vec = new Principal[5];
        for (int i = 0; i < vec.length; i++) {
            vec[i] = new Principal(i);
            vec[i].start();
        }

        /*Hace que el hilo principal y demas hilos espere a que termine de ejecutar este hilo
         *
         * Para ejecutar los hilos en paralelo y esperar a que todos terminen, debes iniciar
         * todos los hilos primero y luego llamar a join() en cada hilo fuera del bucle de inicio.
         * Esto permite que todos los hilos se ejecuten simultáneamente */
        Arrays.stream(vec).forEach(i -> {
            try {
                i.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        System.out.println("Hilo principal terminado");
    }

}