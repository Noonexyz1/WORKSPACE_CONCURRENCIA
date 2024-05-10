package org.example;

import java.util.LinkedList;
import java.util.Queue;

public class Peluqueria {
    private static final int NUM_PELUQUERAS = 3;
    private static final int NUM_CLIENTES = 10;

    private Queue<Cliente> colaClientes = new LinkedList<>();
    private Thread[] peluqueras;

    private Object lock = new Object();

    public Peluqueria() {
        // asignamos a la lista de Threads los comportamientos de cada Peluquera
        peluqueras = new Thread[NUM_PELUQUERAS];
        for (int i = 0; i < NUM_PELUQUERAS; i++) {
            peluqueras[i] = new Thread(new Peluquera(this, "Peluquera" + (i + 1)));
        }
    }

    public void iniciar() {
        // iniciamos el pool de threads
        for (Thread peluquera : peluqueras) {
            peluquera.start();
        }

        // creamos los clientes aleatorios y agregamos a la cola de Clientes
        for (int i = 1; i <= NUM_CLIENTES; i++) {
            Cliente cliente = new Cliente("Cliente" + i);
            agregarCliente(cliente);
        }
    }

    public void agregarCliente(Cliente cliente) {
        synchronized (lock) {
            colaClientes.offer(cliente);
            lock.notify();
        }
    }

    public Cliente obtenerCliente() throws InterruptedException {
        synchronized (lock) {
            while (colaClientes.isEmpty()) {
                lock.wait();
            }
            return colaClientes.poll();
        }
    }


}
