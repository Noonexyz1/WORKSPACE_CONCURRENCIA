package org.example;

public class Main {
    public static void main(String[] args) {
        // REGLAS NATURALES
        // 1.- El hilo "main" es el principal hilo
        // 2.- De forma natural, todos los hilos (incluso "main") terminan por su propia cuenta

        /*Mostrando el hilo del Main*/
        showTheMainThread();

        /*Heredando la clase Thread*/
        exdentingByThreadClass();

        /*Implementando la interfaz Runnable*/
        implementingByRunnable();

        /*Implementando con interfaz funcional*/
        implementingByFunctional();
    }

    public static void showTheMainThread(){
        System.out.println("Lanzando thread Main: " + Thread.currentThread().getName());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void exdentingByThreadClass(){
        HiloExtendsThread hilo1 = new HiloExtendsThread();
        hilo1.start();

        HiloExtendsThread hilo2 = new HiloExtendsThread();
        hilo2.start();

        HiloExtendsThread hilo3 = new HiloExtendsThread();
        hilo3.start();
    }

    public static void implementingByRunnable(){
        HiloWithRunnable tarea1 = new HiloWithRunnable("Tarea1");
        Thread thread1 = new Thread(tarea1);
        thread1.start();

        Thread thread2 = new Thread(tarea1);
        thread2.start();

        Thread thread3 = new Thread(tarea1);
        thread3.start();
    }

    public static void implementingByFunctional(){
        Thread tread1 = new Thread(() -> {
            System.out.println("Lanzando thread Functional: " + Thread.currentThread().getName());
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Terminado thread Functional: " + Thread.currentThread().getName());
        });

        tread1.start();
    }

}