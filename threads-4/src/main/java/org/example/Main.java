package org.example;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        //estado inicial NEW
        Thread thread = new Thread(() -> {
            System.out.println("Proceso iniciado!");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Proceso terminado!");
        });
        System.out.println("Estado del hilo " + thread.getName() + ":" + thread.getState());

        //estado RUNNABLE
        thread.start();
        System.out.println("Estado del hilo " + thread.getName() + ":" + thread.getState());

        //estado TIMED_WAITING tiempo limitado
        System.out.println("Estado del hilo " + thread.getName() + ":" + thread.getState());

        //estado BLOQUED estado en sincronizacion
        //estado WAITING estado en sincronizacion

        //estado TERMINATE
        thread.join();
        System.out.println("Estado del hilo " + thread.getName() + ":" + thread.getState());

    }

}