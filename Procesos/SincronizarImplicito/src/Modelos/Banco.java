package Modelos;


//Esta clase tiene que crear 100 cuentas corrientes para después cargar cada cuenta con 2000$;

import java.util.ArrayList;
import java.util.List;

public class Banco {


    private final float IMPORTE_ENTRADA = 2000;
    private List<Cuenta> cuentas;
    private final int numeroCuentas;


    public Banco(int numeroCuentas) {
        this.numeroCuentas = numeroCuentas;
        cargarDatos(numeroCuentas);

    }

    private void cargarDatos(int numeroCuentas) {
        cuentas = new ArrayList<>();
        for (int i = 0; i <= numeroCuentas; i++) {
            Cuenta cuenta = new Cuenta(i, IMPORTE_ENTRADA);
            cuentas.add(cuenta);
        }
    }

    public float getImporteEntrada() {
        return IMPORTE_ENTRADA;
    }

    public int getNumeroCuentas() {
        return numeroCuentas;
    }

    /**
     * Dos cuentas que tenemos para hacer transferencias
     * Con synchronized el metodo estara sincronizado (CIERRE IMPLICITO == SOLO 1 CONDICION)
     * @param cuentaOrigen  donde sale el dinero hacia la cuenta destino
     * @param cantidad      la cantidad que se desea hacer la transferencia
     * @param cuentaDestino descontamos la cantidad cuando hacemos una transferencia
     */
    public synchronized void confirmarTransferencia(int cuentaOrigen, int cantidad, int cuentaDestino) throws InterruptedException {
        //Ponemos a nuestro hilo con una condicion que no cumpla con los requisitos para que se quede a la espera.
        while (!(cuentas.get(cuentaOrigen).getImporte() >= cantidad)) {
            wait();
        }
            System.out.println("//**********TRANSFERENCIA REALIZADA **********//");
            System.out.printf("CUENTA ORIGEN %s, CUENTA DESTINO %s, IMPORTE %d$ \n", cuentas.get(cuentaOrigen).toString(), cuentas.get(cuentaDestino).toString(), cantidad);
            System.out.println(Thread.currentThread());
            float importeTransferir = cuentas.get(cuentaOrigen).getImporte() - cantidad;
            cuentas.get(cuentaOrigen).setImporte(importeTransferir);
            System.out.printf("%10.2f de %d para %d ", importeTransferir, cuentaOrigen, cuentaDestino);
            float importeIngreso = cuentas.get(cuentaDestino).getImporte() + cantidad;
            cuentas.get(cuentaDestino).setImporte(importeIngreso);
            System.out.printf("Saldo total: %,.2f$ \n ", getSaldoTotal());
            //Despierta a los hilos que pueden ser ejecutados otra vez
            notifyAll();
    }

    public float getSaldoTotal() {
        float sumaCuenta = 0;

        for (Cuenta cuenta : cuentas) {
            sumaCuenta += cuenta.getImporte();
        }

        return sumaCuenta;

    }


    @Override
    public String toString() {
        return "Banco{" +
                "cuentas=" + cuentas +
                '}';
    }
}
