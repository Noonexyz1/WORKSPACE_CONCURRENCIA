package org.example;

public class Main4 implements Runnable {
    //Esto se a combertido en mi recurso CRITICO
    private Persona persona;

    public Main4(Persona persona) {
        this.persona = persona;
    }
    public Main4(){

    }

    @Override
    public void run() {
        System.out.println(Main4.class.getName() + " Valor static: " + Main1.PI);
        Main1.PI = 4;
        System.out.println(Main4.class.getName() + " Valor static: " + Main1.PI);

        //Modificando el valor de Persona enviado por referencia
        //Me parece que la referencia solamente se puede pasar desde Main como valor pordefecto
        //para esta clase Main4
        persona.setEdad(persona.getEdad() + 1); //este recurso debe ser sincronico por que al mayor hilos, mas perdida de datos
        /*Si o si debo iniciar el valor inicial o estado inicial de una clase que tiene run()
        * que voy a utilizar para un Thread, en este caso tengo dos constructores, esto lo debo iniciar
        * cada vez que voy a crear un nuevo Thread, porque de thread a thread que no son ambos origines, al
        * parecer no se puede*/

    }
}
