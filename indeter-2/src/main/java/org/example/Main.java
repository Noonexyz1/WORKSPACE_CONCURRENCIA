package org.example;

class Principal implements Runnable {
    private static int contador = 0;
    private static final Object cerrojo = new Object();
    private static boolean estadoCambiado = false; // Analogía al estado del sujeto

    @Override
    public void run() {
        //En paralelo antes del synchronized()
        System.out.println(" " + Thread.currentThread().getName() + " INICIO SYNCHR() valorCritico: " + contador);
        //nostros ponemos un synchro aqui porque cualquiera de los hilos se paren
        //debemos pensar en que en este metodo, todos los hilos los tienen y, asi que este
        //metodo es para todos los hilos, por eso hacemos esas locas condiciones de wait y notify
        synchronized (cerrojo) {// hay hilos quienes han llegado primero al este bloque, entonces hay una cola
            // Observadores esperan hasta que el estado cambie
            while (!estadoCambiado && !Thread.currentThread().getName().equals("Thread-3")) {
                try {
                    System.out.println(" " + Thread.currentThread().getName() + " " + Thread.currentThread().getState() + " ANTES WAIT valorCritico: " + contador);
                    cerrojo.wait(); // Observador esperando la notificación, van a volver a competir despues del notify
                    System.out.println(" " + Thread.currentThread().getName() + " " + Thread.currentThread().getState() + " DESPUS WAIT valorCritico: " + contador);
                } catch (InterruptedException e) {}
            }

            // Sujeto cambia el estado y notifica a los observadores
            if (Thread.currentThread().getName().equals("Thread-3")) {
                estadoCambiado = true; // Cambio de estado
                //cerrojo.notifyAll(); // Notificación a todos los observadores, van a volver a competir
            }

            // Sección crítica de todos los hilos
            System.out.println("  " + Thread.currentThread().getName() + " ANTES valorCritico: " + contador);
            contador++;
            System.out.println("  " + Thread.currentThread().getName() + " DESPUES valorCritico: " + contador);

            // Notificación a todos para asegurar que los demás hilos puedan continuar
            cerrojo.notifyAll();
        }
        /*Termina el cuello de botella y empiezan todos en paralelo*/
        System.out.println(" " + Thread.currentThread().getName() + " FINAL SYNCHR() valorCritico: " + contador);
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