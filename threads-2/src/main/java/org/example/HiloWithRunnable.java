package org.example;

public class HiloWithRunnable implements Runnable{
    private boolean isTreadActive;
    private String nombreThread;

    public HiloWithRunnable(String nombreThread){
        this.isTreadActive = true;
        this.nombreThread = nombreThread;

    }

    public void activeThread(){
        this.isTreadActive = true;
    }

    public void desactiveThread(){
        this.isTreadActive = false;
    }


    @Override
    public void run() {
        System.out.println("Lanzando thread Runnable: " + Thread.currentThread().getName());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Terminado thread Runnable: " + Thread.currentThread().getName());
    }
}
