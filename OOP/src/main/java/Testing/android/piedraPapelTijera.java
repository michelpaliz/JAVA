package Testing.android;

import Lib.Util;

public class piedraPapelTijera {

    public static void main(String[] args) {
        System.out.println(suerte());
    }



    

    public static int suerte() {
        int numero = Util.randBetween(1, 3);
        String resultado = "";
        // switch (numero) {
        // case 1:
        // resultado = "piedra";
        // break;
        // case 2:
        // resultado = "papel";
        // break;
        // case 3:
        // resultado = "tijera";
        // break;
        // }

        return numero;
    }

}
