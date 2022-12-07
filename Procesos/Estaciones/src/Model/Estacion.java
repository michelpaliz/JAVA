package Model;

import java.util.List;
import java.util.Objects;

public class Estacion implements Runnable {
    private final String nombre;
    private final List<String> meses;

    public Estacion(String nombre, List<String> meses) {
        this.nombre = nombre;
//        Thread thread = new Thread(this, meses.toString());
        this.meses = meses;
//        thread.start();
    }

    public String getNombre() {
        return nombre;
    }

    public List<String> getMeses() {
        return meses;
    }


    @Override
    public String toString() {
        return "Estacion{" +
                "nombre='" + nombre + '\'' +
                ", meses=" + meses +
                '}';
    }

    @Override
    public void run() {
        try {
            for(int i = meses.size(); i > 0; i--) {
                System.out.println( meses+ ": " + i);
                Thread.sleep(1000);
            }
        }catch (InterruptedException e) {
            System.out.println(meses + "Interrupted");
        }
        System.out.println(meses + " exiting.");
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Estacion estacion = (Estacion) obj;
        return nombre.equals(estacion.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre);
    }
}
