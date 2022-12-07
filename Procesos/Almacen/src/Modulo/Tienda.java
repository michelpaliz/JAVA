package Modulo;

public class Tienda {
    private final Cliente[] cliente;
    private final Almacen almacen;
    private final Puerta puerta;

    public Tienda(int numeroClientes, int numeroProductos) {
        this.cliente = new Cliente[numeroClientes];
        this.almacen = new Almacen(numeroProductos);
        this.puerta = new Puerta();
    }


    public void empezar() throws InterruptedException {
        Thread[] hilosAsociados = new Thread[cliente.length];

        for (int i = 0; i < cliente.length; i++) {
            String nombreHilo = "Cliente " + i;
            cliente[i] = new Cliente(puerta, almacen,nombreHilo);
            hilosAsociados[i] = new Thread(cliente[i]);
            hilosAsociados[i].start();
        }


        for (int i = 0; i < cliente.length; i++) {
            hilosAsociados[i].join();
        }

    }


}
