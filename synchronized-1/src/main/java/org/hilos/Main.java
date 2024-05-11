package org.hilos;

public class Main {

    // valor critico GLOBAL
    private int contador = 0;

    // objeto notificador
    private final Object lock = new Object();


    public void realizarTarea() {
        //variable local para cada hilo
        int i;
        for (i = 0; i < 1000; i++) {
            // valor GLOBAL y proceso seccion CRITICO
            synchronized (lock) {
                contador++;
            }

            try {
                Thread.sleep(1);
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


        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("Contador despues: " + m1.obtenerContador());


    }
    
}
