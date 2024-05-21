package org.example;

class Principal implements Runnable {
    private static int contador = 0;
    private static final Object cerrojo = new Object();
    private static boolean estadoCambiado = false; // Analogía al estado del sujeto

    @Override
    public void run() {
        System.out.println(" " + Thread.currentThread().getName() + " INICIO RUN() valorCritico: " + contador);
        //nostros ponemos un synchro aqui porque cualquiera de los hilos se paren
        //debemos pensar en que en este metodo, todos los hilos los tienen y, asi que este
        //metodo es para todos los hilos, por eso hacemos esas locas condiciones de wait y notify
        synchronized (cerrojo) {
            // Observadores esperan hasta que el estado cambie
            while (!estadoCambiado && !Thread.currentThread().getName().equals("Thread-3")) {
                try {
                    cerrojo.wait(); // Observador esperando la notificación
                } catch (InterruptedException e) {}
            }

            // Sujeto cambia el estado y notifica a los observadores
            if (Thread.currentThread().getName().equals("Thread-3")) {
                estadoCambiado = true; // Cambio de estado
                cerrojo.notifyAll(); // Notificación a todos los observadores
            }

            // Sección crítica de todos los hilos
            System.out.println("  " + Thread.currentThread().getName() + " INICIO sync() valorCritico: " + contador);
            contador++;
            System.out.println("  " + Thread.currentThread().getName() + " FINAL sync() valorCritico: " + contador);

            // Notificación a todos para asegurar que los demás hilos puedan continuar
            cerrojo.notifyAll();
        }
        System.out.println(" " + Thread.currentThread().getName() + " FINAL RUN() valorCritico: " + contador);
    }
}

public class Main {
    public static void main(String[] args){
        System.out.println(Thread.currentThread().getName() + " INICIO");

        /*Para que un hilo que querramos ejecute antes que todos
        * poniendo a los que llegan primero en pausa*/
        Runtime runtime = Runtime.getRuntime();
        int nNucleos = runtime.availableProcessors();

        Thread[] hilos = new Thread[nNucleos];
        for(int i = 0; i < hilos.length; i++){
            Runnable runnable = new Principal();
            hilos[i] = new Thread(runnable);
            hilos[i].start();
        }

        for(int i = 0; i < hilos.length; i++){
            try{
                hilos[i].join();
            }catch(Exception ex){}
        }

        System.out.println(Thread.currentThread().getName() + " FINAL");
    }
}