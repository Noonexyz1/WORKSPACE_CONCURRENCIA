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
        /*Haremos que el thread1 espere al thread0 sin thread "main" tenga que esperar por thread0*/
        Tarea1WithRunnable tarea1 = new Tarea1WithRunnable("Tarea1");
        Thread thread0 = new Thread(tarea1);

        Tarea2WithRunnable tarea2 = new Tarea2WithRunnable("Tarea2", thread0);
        Thread thread1 = new Thread(tarea2);

        /*Estos hilos ya estan sincronizados */
        thread0.start();
        thread1.start();
    }


}