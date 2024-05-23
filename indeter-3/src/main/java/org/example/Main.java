package org.example;

import java.util.Arrays;

class Principal implements Runnable{

    private boolean consumidor;

    private static int tarta = 0;
    private static Object lock = new Object();

    public Principal(boolean consumidor) {
        this.consumidor = consumidor;
    }

    @Override //public static void run(){ var p = new Principal(true);}
    public void run() {
        //cuando se ejecuta este metodo, ya hay un ( this, osea, el objeto del estado actual )
        while (true) {
            if (consumidor){
                consumiendo();
            } else {
                cocinando();
            }
        }
    }

    private void consumiendo() {
        System.out.println(Thread.currentThread().getName() + ", estado: {" + Thread.currentThread().getState() + "} ANTES SYNCH(), consumiendo()");
        synchronized (lock) {
            if (tarta > 0) {
                tarta--;
                System.out.println("Quedan: " + tarta + " porciones de tarta");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {}
            } else {
                lock.notifyAll();
                try {
                    lock.wait();
                } catch (InterruptedException e) {}

            }
        }
        System.out.println(Thread.currentThread().getName() + ", estado: {" + Thread.currentThread().getState() + "} DESPUES SYNCH(), consumiendo()");

    }

    private void cocinando() {
        System.out.println(Thread.currentThread().getName() + ", estado: {" + Thread.currentThread().getState() + "} ANTES SYNCH(), cocinando()");
        synchronized (lock) {
            if (tarta == 0) {
                tarta = 10;
                System.out.println("Soy el cocinero y quedan: " + tarta + ".");
                lock.notifyAll();
            }

            try {
                lock.wait();
            } catch (InterruptedException e) {}


        }
        System.out.println(Thread.currentThread().getName() + ", estado: {" + Thread.currentThread().getState() + "} DESPUES SYNCH(), cocinando()");

    }

}


public class Main {
    public static void main(String[] args) {
        int numHilos = 3;
        Thread[] hilos = new Thread[numHilos];
        for (int i = 0; i < hilos.length; i++) {
            Runnable runnable;
            if (i != 0) {
                           //Le estamos pasando un estado incial (this para la otra clase)
                           //a la clase a pesar de enviar un runnable al Thread
                runnable = new Principal(true);
            } else {
                runnable = new Principal(false);
                //no hay metodo por instanciarse a tipo Runnable
                //runnable.consumiendo();
            }

            hilos[i] = new Thread(runnable);
            hilos[i].start();
        }

        Arrays.stream(hilos).forEach(l -> {
            try {
                l.join();
            } catch (InterruptedException e) {}
        });

    }
}