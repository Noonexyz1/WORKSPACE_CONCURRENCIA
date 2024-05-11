package org.example;

public class Main {
    public static void main(String[] args) throws InterruptedException{
        // REGLAS NATURALES
        // 1.- El hilo "main" es el principal hilo
        // 2.- De forma natural, todos los hilos (incluso "main") terminan por su propia cuenta
        // 3.- Es imposible saber cuál de los hilos se lanzará primero y/o cuál termina primero
        // 4.- Todos los hilos creados comparten los mismos recursos, como cpu y memoria

        /*Mostrando el hilo del Main*/
        showTheMainThread();

        /*Implementando la interfaz Runnable*/
        implementingByRunnable();

        showTheMainThreadEnd();
    }

    public static void showTheMainThread(){
        System.out.println("Lanzando thread Main: " + Thread.currentThread().getName());
    }

    public static void showTheMainThreadEnd(){
        /*try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }*/
        System.out.println("Terminado thread Main: " + Thread.currentThread().getName());
    }

    public static void implementingByRunnable() throws InterruptedException {
        HiloWithRunnable tarea1 = new HiloWithRunnable("Tarea1");
        Thread thread0 = new Thread(tarea1);
        Thread thread1 = new Thread(tarea1);
        Thread thread2 = new Thread(tarea1);

        /*Este thread0, con join(), se une al tread "main"*/
        /*El hilo "main espera hasta que el thread0 haya terminado"*/
        thread0.start();
        thread0.join();

        /*Este thread1 espera a que el thread0 termine*/
        thread1.start();
        thread2.start();
    }


}