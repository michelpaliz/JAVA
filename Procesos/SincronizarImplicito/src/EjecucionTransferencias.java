import Modelos.Banco;

public class EjecucionTransferencias implements Runnable {

    private final Banco banco;
    private int adeudado;;


    public EjecucionTransferencias(Banco banco, int adeudado) {
        this.banco = banco;
        this.adeudado = adeudado;

    }
    @Override
    public void run(){
        try{
                while (true){
                int cuentaDestino = (int)(banco.getNumeroCuentas() * Math.random());
                int cantidad = ( (int)(banco.getImporteEntrada() * (Math.random())));
                    Thread.sleep((int)(Math.random()*10));
                    banco.confirmarTransferencia(adeudado,cantidad,cuentaDestino);
            }
        }catch (InterruptedException e){
            throw new RuntimeException(e);
        }

    }

}
