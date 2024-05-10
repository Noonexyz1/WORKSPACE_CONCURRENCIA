package org.example;

public class Tarea2WithRunnable implements Runnable{
    private String nombreThread;
    private Thread threadToWait;

    public Tarea2WithRunnable(String nombreThread, Thread threadToWait){
        this.nombreThread = nombreThread;
        this.threadToWait = threadToWait;

    }

    @Override
    public void run() {
        /*Aqui esta la clave para sincronizar dos hilos*/
        try {
            threadToWait.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Lanzando thread Runnable Tarea2: " + Thread.currentThread().getName());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Terminado thread Runnable Tarea2: " + Thread.currentThread().getName());
    }
}
