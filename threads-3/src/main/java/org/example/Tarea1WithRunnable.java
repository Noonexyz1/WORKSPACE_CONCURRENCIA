package org.example;

public class Tarea1WithRunnable implements Runnable{
    private String nombreThread;

    public Tarea1WithRunnable(String nombreThread){
        this.nombreThread = nombreThread;

    }

    @Override
    public void run() {
        System.out.println("Lanzando thread Runnable Tarea1: " + Thread.currentThread().getName());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Terminado thread Runnable Tarea1: " + Thread.currentThread().getName());
    }
}
