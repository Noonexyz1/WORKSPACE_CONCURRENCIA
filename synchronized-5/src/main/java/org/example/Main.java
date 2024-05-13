package org.example;

import java.util.concurrent.Semaphore;

class AccesoVentanillas implements Runnable {
    //cambiamos el valor de 2 para un valor de 1 para sincronizar un hilo tras otro
    //para procesos criticos donde yo lo requiera
    private final Semaphore semaforo = new Semaphore(2);

    @Override
    public void run() {
        try {
            System.out.println(Thread.currentThread().getName() + " iniciando proceso...");

                //ingresando al recurso critico, notificar al semaforo(Publisher) que esperen los demas hilos
                semaforo.acquire();
                System.out.println(Thread.currentThread().getName() + " iniciando proceso critico...");
                //Thread.sleep(200)-------; //no descomentar si quieres ver el proceso real de los hilos
                System.out.println(Thread.currentThread().getName() + " ...terminando proceso critico");
                //notificar al semaforo(Publisher) que ya termine el proceso critico, los demas pueden entrar
                semaforo.release();

            System.out.println(Thread.currentThread().getName() + " ...proceso terminado");

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}



public class Main {
    public static void main(String[] args) {
        /*Utilizando Semaforos*/
        for (int i = 0; i < 5; i++) {
            Thread t = new Thread(new AccesoVentanillas());
            t.start();
        }

    }
}