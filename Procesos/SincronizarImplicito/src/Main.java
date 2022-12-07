import Modelos.Banco;

public class Main {

    private static Banco banco;

    public static void main(String[] args) {

        int numeroCuentas = 3;
        EjecucionTransferencias ejecucionTransferencias;
        banco = new Banco(numeroCuentas);

        for (int i = 0; i < numeroCuentas; i++) {
            ejecucionTransferencias = new EjecucionTransferencias(banco,i);
            Thread thread = new Thread(ejecucionTransferencias);
            thread.start();
        }
//        System.out.println(banco);
        
//        if (  banco.requestTransferencia(2,500)){
//            banco.confirmarTransferencia(5);
//            System.out.println(banco);
//        }
//        banco.printSaldoTotal();

    }






}