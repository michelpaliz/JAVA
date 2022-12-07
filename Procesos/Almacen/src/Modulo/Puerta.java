package Modulo;

public class Puerta {

    private boolean ocupado;

    public Puerta() {
        this.ocupado = false;
    }


    public boolean isOcupado() {
        return ocupado;
    }

    public synchronized void abrirPuerta(){
        ocupado = false;
    }

    public synchronized boolean clienteIntentaEntrar(){
        if (ocupado){
            return false;
        }
        return ocupado = true;
    }


}
