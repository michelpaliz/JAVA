package Testing.android;

import Lib.Util;

public class caraCruz {

    public static void main(String[] args) {

        suerte();
    }

    public static String  suerte() {
        String resultado;
        int random = Util.randBetween(0, 10);

        if (entero(random) == true) {
            resultado = ("Es cara");
        } else {
            resultado = ("Es cruz");
        }
        return resultado;
    }

    public static boolean entero(int numero) {
        boolean correcto = false;
        if (numero % 2 == 0) {
            return correcto = true;
        }
        return correcto;
    }

}
