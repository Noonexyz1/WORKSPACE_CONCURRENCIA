package org.example;

class Principal {
    private int contador = 0;

    //Imaginemos que este es el run()
    public void realizarTarea() {
        System.out.println(" " + Thread.currentThread().getName() + "- Empezando proceso");
        for (int i = 0; i < 1000; i++) {
            contador++;
            //Thread.sleep(1);
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
        principal.setContador(5);
        System.out.println(Thread.currentThread().getName() + "- Valor INICIAL contador: " + principal.getContador());

            Thread th0 = new Thread(() -> {
                    principal.realizarTarea();
            });

            th0.start();
            //th0.join();

        Thread.sleep(3000);
        System.out.println(Thread.currentThread().getName() + "- Valor FINAL contador: " + principal.getContador());

    }
}