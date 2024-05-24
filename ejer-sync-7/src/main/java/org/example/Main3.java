package org.example;

public class Main3 implements Runnable{
    @Override
    public void run() {
        System.out.println(Main3.class.getName() + " Valor static: " + Main1.PI);

        Persona persona = new Persona();
        System.out.println(Main3.class.getName() + " Edad persona: " + persona.getEdad());

        //Creando una instancia de clase Main4 para pasar una referncia y esa clase lo modifique
        Main4 main4 = new Main4(persona);   //los dos threads estan COMPARTIENDO el mismo estado inicial de persona
        //puedes tener otro Main5 = new, y mandarle el mismo estado de persona, y eso habre un monton de posibilidades
        //como por ejemplo, si persona tiene dos metodos y quiere que cada metodo lo ejecute un hilo, en este caso
        //dos metodos de persona para dos hilos, y ejecutarlos al mismo tiempo, eso es una solucion. osea todoo esto
        //lo haria con referencias =)

        Thread thread = new Thread(main4); //estamos pasando el estado inicial de Main4
        Thread thread2 = new Thread(main4); //estamos pasando el estado inicial de Main4
        thread.start();
        thread2.start();
        try {
            thread.join();
            thread2.join();
        } catch (InterruptedException e) {}

        System.out.println(Main3.class.getName() + " Edad persona: " + persona.getEdad());

    }
}
