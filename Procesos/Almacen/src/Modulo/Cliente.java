package Modulo;

import java.util.Random;

public class Cliente implements Runnable {

    private final int NUMERO_INTENTOS_PERMITIDOS =10;
    private final Puerta puerta;
    private final Almacen almacen;
    private final String nombre;
    private final Random random ;


    public Cliente(Puerta puerta, Almacen almacen, String nombre) {
        this.puerta = puerta;
        this.almacen = almacen;
        this.nombre = nombre;
        random = new Random();
    }


    public void esperar(){
        try {
                Thread.sleep(random.nextInt(100));
        }catch (InterruptedException e) {
            System.out.println("El pobre cliente no pudo entrar >(");
        }

    }

    @Override
    public void run() {
        for (int i = 0; i < NUMERO_INTENTOS_PERMITIDOS; i++) {
            if (puerta.isOcupado()){
                esperar();
            }else {
                if (puerta.clienteIntentaEntrar()){
                    esperar();
                    puerta.abrirPuerta();
                    if (almacen.cogerProducto()){
                        System.out.println("Nombre " + nombre + " tiene el producto");
                    }else{
                        System.out.println("Nombre" + nombre + " no pude obtener el producto se lo han llevado ");
                    }
                }
            }
            System.out.println("Nombre " + nombre +  " no lo puedo encontrar !!!");
        }
    }
}
