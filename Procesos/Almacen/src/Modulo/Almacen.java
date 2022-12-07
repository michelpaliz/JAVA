package Modulo;

public class Almacen {

    private int numeroProductos;

    public Almacen(int numeroProductos) {
        this.numeroProductos = numeroProductos;
    }

    public boolean cogerProducto(){
        if (numeroProductos != 0){
            numeroProductos--;
            return true;
        }
        return false;
    }

}
