package org.example;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

class Tarea implements Runnable{
    private final CyclicBarrier barrera;
    private final String nombre;

    public Tarea(CyclicBarrier barrera, String nombre) {
        this.barrera = barrera;
        this.nombre = nombre;
    }

    @Override
    public void run() {
        try {
            System.out.println(nombre + " esta realizando alguna tarea");
            Thread.sleep(2000);
            System.out.println(nombre + " ha llegado a la barrera");
            barrera.await();
            System.out.println(nombre + " continua con la siguiente tarea");
        } catch (BrokenBarrierException | InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

}


public class Main {
    public static void main(String[] args) {
        CyclicBarrier barrera = new CyclicBarrier(3, () -> {
            System.out.println("Todos los hilos han alcanzado la barrera");
        });

        new Thread(new Tarea(barrera, "H1")).start();
        new Thread(new Tarea(barrera, "H2")).start();
        new Thread(new Tarea(barrera, "H3")).start();
    }

}