package Modulo;

public class PingPong implements Runnable {
    private final String nombre;

    public PingPong(String nombre) {
        this.nombre = nombre;
    }

    public synchronized void pingPong() throws  InterruptedException{
        while (true){
            System.out.println(nombre);
            Thread.sleep(1000);
            notify();
        }
    }

    public void run() {
        try {
            pingPong();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            this.wait(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


    }



}
