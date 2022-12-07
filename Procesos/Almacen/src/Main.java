import Modulo.Tienda;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        int numeroClientes =  300;
        int numeroProductos = 100;
        Tienda tienda = new Tienda(numeroClientes,numeroProductos);
        tienda.empezar();
    }


}