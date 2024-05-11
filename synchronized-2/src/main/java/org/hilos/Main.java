package org.hilos;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class Main {

    // valor critico GLOBAL
    private int contador = 0;

    // con clase Reentrantlock
    private ReentrantLock lock = new ReentrantLock();


    public void realizarTarea() {
        //variable local para cada hilo
        int i;
        for (i = 0; i < 1000; i++) {
            // valor GLOBAL y proceso seccion CRITICO
            try {
                if (lock.tryLock(20, TimeUnit.MILLISECONDS)) {
                    System.out.println("Cuantos hilos estan esperando? : " + lock.getQueueLength());
                    try {
                        contador++;
                        try {
                            Thread.sleep(1);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }

                    } finally {
                        lock.unlock();
                    }

                } else {
                    System.out.println("No logre hacer mi tarea :(");
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }


        }

    }

    public int obtenerContador() {
        return this.contador;
    }

    public static void main(String[] args) throws InterruptedException {
        Main m1 = new Main();
        System.out.println("Contador inicial: " + m1.obtenerContador());

        Thread t1 = new Thread(m1::realizarTarea);
        Thread t2 = new Thread(m1::realizarTarea);
        Thread t3 = new Thread(m1::realizarTarea);


        t1.start();
        t2.start();
        t3.start();

        t1.join();
        t2.join();
        t3.join();

        System.out.println("Contador despues: " + m1.obtenerContador());


    }
    
}
