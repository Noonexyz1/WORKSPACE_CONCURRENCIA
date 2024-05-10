package org.example;

import java.util.Random;

public class Peluquera implements Runnable {
    // la peluqueria donde trabaja la Peluquera
    private final Peluqueria peluqueria;
    // el nombre de la peluquera
    private final String nombre;


    public Peluquera(Peluqueria peluqueria, String nombre) {
        this.peluqueria = peluqueria;
        this.nombre = nombre;
    }


    @Override
    public void run() {
        Random random = new Random();
        try {
            while (true) {
                // la peluquera empieza a trabajar
                Cliente cliente = peluqueria.obtenerCliente();
                System.out.println(nombre + " comienza a atender a " + cliente.getName());
                int tiempoAtencion = random.nextInt(10) + 1;
                Thread.sleep(tiempoAtencion * 1000);
                System.out.println(nombre + " ha terminado de atender a " + cliente.getName() +
                        " en " + tiempoAtencion + " segundos.");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
