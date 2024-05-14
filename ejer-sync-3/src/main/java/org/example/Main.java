package org.example;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

class Principal {
    /*Esta variable es comun para todos los hilo*/
    private int contador = 0;

    private ReentrantLock lock = new ReentrantLock();


    //Imaginemos que este es el run()
    public void realizarTarea() {
        System.out.println(" " + Thread.currentThread().getName() + "- Empezando proceso");
        for (int i = 0; i < 2; i++) {
            System.out.println(" " + Thread.currentThread().getName() + "- antes de Sync del proceso: " + contador + "\t\t\t\t i = " + i);

            try {
                if (lock.tryLock(20, TimeUnit.MILLISECONDS)){

                    /*Aqui solamente entra un proceso a la vez.
                    * Si este proceso tarda mas que la condicion de if, se saltara al else */

                    /*Vamos a poner unos print() aqui para mostrar el proceso que llego primero y de quien es ese proceso mas el estado actual
                     * del recurso y el estado despues de ser modificado*/
                    System.out.println("  el 1er proc en llegar es del: " + Thread.currentThread().getName() + " -PROCESO CRITICO INICIADO- estado actual del recurso: " + contador + "\t\t\t\tcon i = " + i);
                    contador++;
                    System.out.println("  el 1er proc en llegar es del: " + Thread.currentThread().getName() + " -PROCESO CRITICO TERMINADO- estado actual del recurso: " + contador + "\t\t\t\tcon i = " + i);

                } else {
                    System.out.println("  " + Thread.currentThread().getName() + " No logre hacer mi tarea :(");
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }


            /*Aqui i todavia no se ha sumado obviamente, en la primera iter muestra 0*/
            System.out.println(" " + Thread.currentThread().getName() + "- despues de Sync del proceso: " + contador + "\t\t\t i = " + i);
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println(" " + Thread.currentThread().getName() + " Contador:      " + contador);
        System.out.println(" " + Thread.currentThread().getName() + "- Proceso terminado");
    }

    public int getContador(){
        return this.contador;
    }

    public void setContador(int contador) {
        this.contador = contador;
    }
}


public class Main {
    public static void main(String[] args) throws InterruptedException{
        Principal principal = new Principal();
        System.out.println(Thread.currentThread().getName() + "- Valor INICIAL contador: " + principal.getContador());

        Thread th0 = new Thread(() -> {principal.realizarTarea();});
        Thread th1 = new Thread(() -> {principal.realizarTarea();});

        th0.start();
        th1.start();

        //th0.join();
        //th1.join();

        Thread.sleep(3000);
        System.out.println(Thread.currentThread().getName() + "- Valor FINAL contador: " + principal.getContador());

    }
}