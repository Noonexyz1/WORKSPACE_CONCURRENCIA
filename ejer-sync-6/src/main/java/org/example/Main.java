package org.example;

class MiTarea implements Runnable{
    private int valor;
    public MiTarea(int valor){
        this.valor = valor;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " Valor: " + valor);
    }
}

public class Main {
    public static void main(String[] args) {
        /*Mostrar el numero de hilos que tengo disponible*/
        Runtime runtime = Runtime.getRuntime();
        System.out.println(runtime.availableProcessors());

        /*Creamos los hilos depende de nuestros procesadores*/
        Thread[] threads = new Thread[runtime.availableProcessors()];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(new MiTarea(i));
            threads[i].start();
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }
}