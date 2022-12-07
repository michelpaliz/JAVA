package Modelos;

public class Cuenta {

    private final int numeroCuenta;
    private float importe;


    public Cuenta(int numeroCuenta, float importe) {
        this.numeroCuenta = numeroCuenta;
        this.importe = importe;
    }


    public void setImporte(float importe) {
        this.importe = importe;
    }

    public float getImporte() {
        return importe;
    }

    @Override
    public String toString() {
        return "Cuenta{" +
                "numeroCuenta=" + numeroCuenta +
                ", importe=" + String.format("%,.2f$", importe)+
                '}';
    }
}
