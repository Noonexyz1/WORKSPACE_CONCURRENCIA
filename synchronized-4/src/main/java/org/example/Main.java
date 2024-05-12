package org.example;

class HostParty {
    public synchronized void procesoUno() throws InterruptedException {
        System.out.println("El anfitrión dice: ¡Esperen un momento, por favor!");
        System.out.println("antes del wait()");
        /*El anfitrión le pide a todos los invitados que esperen su turno para hablar*/
        wait();
        System.out.println("despues del wait()");
        System.out.println("¡El anfitrión dice: ¡Se puede continuar!");
    }

    public synchronized void procesoDos() throws InterruptedException {
        System.out.println("  El anfitrión dice: ¡Voy a liberar la pista de baile!");
        System.out.println("  antes del notify()");
        Thread.sleep(2000);
        notify();
        /*Despues del notify() tanto el procesoUno() y procesoDos() se ejecutan y compiten por la cpu*/
        System.out.println("  despues del notify()");
        System.out.println("  El anfitrión dice: ¡La pista de baile está libre!");
    }
}

public class Main {
    public static void main(String[] args) {
        HostParty en = new HostParty();

        Thread thread0 = new Thread(() -> {
            try {
                en.procesoUno();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        Thread thread1 = new Thread(() -> {
            try {
                en.procesoDos();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        thread0.start();
        thread1.start();

    }

}