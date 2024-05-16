package org.example;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class MiTarea implements Runnable {
    private String mensaje;

    public MiTarea(String mensaje) {
        this.mensaje = mensaje;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " estado variable: " + mensaje);
    }
}

public class Main {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 5; i++) {
            Runnable tarea = new MiTarea("Mensaje-" + i);
            executorService.execute(tarea);
        }
        executorService.shutdown();

    }
}