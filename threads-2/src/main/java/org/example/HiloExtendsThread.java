package org.example;

public class HiloExtendsThread extends Thread {
    private boolean isTreadActive = true;

    public void activeThread(){
        this.isTreadActive = true;
    }

    public void desactiveThread(){
        this.isTreadActive = false;
    }


    @Override
    public void run() {
        System.out.println("Lanzando thread heredando Thread: " + getName());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Terminado thread heredando Thread: " + getName());
    }
}
