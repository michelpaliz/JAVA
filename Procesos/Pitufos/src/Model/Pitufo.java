package Model;

public class Pitufo extends Thread {

    private static final int MAX_BOCADILLOS = 3;
    private final  String nombre;
    private final  Long velocidad;

    public Pitufo(String nombre,Long velocidad) {
        this.nombre = nombre;
        this.velocidad = velocidad;
    }
    public void run(){
        for (int i = 1; i <= MAX_BOCADILLOS; i++) {
            try {
                sleep(velocidad);
                System.out.println("Pitufo " + nombre + " come " + i + " bocadillos de jamon");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }
        System.out.printf("%s \n (%s) \n", "/*******************/", "Pitufo " + nombre + " ha terminado");
    }

}