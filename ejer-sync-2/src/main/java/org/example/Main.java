package org.example;

class Principal {
    /*Esta variable es comun para todos los hilo*/
    private int contador = 0;

    //Imaginemos que este es el run()
    public void realizarTarea() {
        System.out.println(" " + Thread.currentThread().getName() + "- Empezando proceso");
        for (int i = 0; i < 3; i++) {
            System.out.println(" " + Thread.currentThread().getName() + "- antes de Sync del proceso: " + contador + "\t\t\t\t i = " + i);
                synchronized (this) { /*Aqui solamente entra un proceso a la vez*/
                    /*Vamos a poner unos print() aqui para mostrar el proceso que llego primero y de quien es ese proceso mas el estado actual
                    * del recurso y el estado despues de ser modificado*/
                    System.out.println("  el 1er proc en llegar es del: " + Thread.currentThread().getName() + " -PROCESO CRITICO INICIADO- estado actual del recurso: " + contador + "\t\t\t\tcon i = " + i);
                    contador++;
                    System.out.println("  el 1er proc en llegar es del: " + Thread.currentThread().getName() + " -PROCESO CRITICO TERMINADO- estado actual del recurso: " + contador + "\t\t\t\tcon i = " + i);
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

        Thread.sleep(2000);
        System.out.println(Thread.currentThread().getName() + "- Valor FINAL contador: " + principal.getContador());

    }
}