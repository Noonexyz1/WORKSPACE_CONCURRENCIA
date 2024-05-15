package org.example;

import java.util.LinkedList;
import java.util.Queue;

class Cliente implements Runnable{
    private String nombre;
    private boolean estaCortado = false;

    public Cliente(String nombre) {
        this.nombre = nombre;
    }

    public synchronized void solicitarCorte() {
        System.out.println(Thread.currentThread().getName() + " Inicia... corte al cliente: " + nombre);
        try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
        estaCortado = true;
        System.out.println(Thread.currentThread().getName() + " ...Termina corte al cliente: " + nombre);
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " se hace cargo, estado del corte: " + estaCortado);
        solicitarCorte();
        System.out.println(Thread.currentThread().getName() + " se hace cargo, estado del corte: " + estaCortado);
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "nombre='" + nombre + '\'' +
                ", estaCortado=" + estaCortado +
                '}';
    }
}

class Main {
    public static void main(String[] args) {
        /*Creando mis clientes*/
        Queue<Cliente> clientes = new LinkedList<>();
        for (int i = 0; i < 4; i++) {
            clientes.add(new Cliente("Diego-" + i));
        }
        System.out.println("----Mostrando estado actual------------");
        clientes.forEach(System.out::println);
        System.out.println("---------------------------------------");

        /*Creando mis hilos*/
        Thread thread0;
        Thread thread1;
        Thread thread2;
        while (!clientes.isEmpty()) {
            thread0 = new Thread(clientes.poll());
            thread1 = new Thread(clientes.poll());
            thread2 = new Thread(clientes.poll());
            thread0.start();
            thread1.start();
            thread2.start();
            try {
                thread0.join();
                thread1.join();
                thread2.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        /*Esto no va a funcionar como crei, se van a seguir creando mas hilos dentro del bucle
        apuntando a otra parte de la ram y sin punteros al anterior thread lo que hace que se sigan
        craendo y creando threads nuevos, porque cuando termine el programa, recien el GC de Java los
        va a limpiar, mientras tando siguen vivos en la ejecucion*/
        System.out.println("----Mostrando estado actual------------");
        clientes.forEach(System.out::println);
        System.out.println("---------------------------------------");

    }

}