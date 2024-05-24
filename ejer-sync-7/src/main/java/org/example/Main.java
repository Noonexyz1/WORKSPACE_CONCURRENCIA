package org.example;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new Main1(/*Sin estado inicial*/));
        Thread t2 = new Thread(new Main2());
        Thread t3 = new Thread(new Main3());
        /*Aqui debo pasar el estado inicial de la Clase Main4 si quiero psar una referencia
        * de Persona con la intencion de modificar el valor de cada referencia con dos o mas hilos diferentes*/
        //Thread t4 = new Thread(new Main4());

        t1.start();
        t2.start();
        t3.start();
        //t4.start();

        t1.join();
        t2.join();
        t3.join();
        //t4.join();
        System.out.println("Fin de main() valor final: " + Main1.PI); //se espera que sea 4

    }
}