package Testing.android;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import Lib.Util;
import java.util.Scanner;

public class Ahorcado {

    private static int NUMERO_INTENTOS = 6;
    private static final List<String> PALABRAS = Arrays.asList("hola", "palabra", "metodo");

    static String palabra;
    private static List<Character> lista;
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        String palabraRecogida = "";

        int numero = Util.randBetween(0, PALABRAS.size() - 1);
        palabraRecogida = PALABRAS.get(numero);

        palabra = palabraRecogida;

        play();

    }

    public static String preguntar() {

        boolean correcto = false;
        String input = null;

        do {
            System.out.println("Introduce una letra");
            input = sc.nextLine();
            if (input.length() == 1) {
                correcto = true;
            }

        } while (!correcto);

        // char respuesta = input.charAt(0);

        return input;

    }

    public static void play() {
        boolean juegoPerdido = false;
        String letra;

        do {
            for (int i = 0; i < palabra.length(); i++) {
                letra = preguntar();
                if (palabra.contains(letra)) {
                    rellenarPalabra(letra.charAt(0));
                } else {

                    --NUMERO_INTENTOS;
                    System.out.println("Palabra no encontrada " + NUMERO_INTENTOS);
                    if (NUMERO_INTENTOS == 0) {
                        System.out.println("Lo siento has perdido");
                        juegoPerdido = true;
                    }
                }
            }
        } while (!juegoPerdido);

    }

    public static void rellenarPalabra(char elemento) {
        Character ch;
        int longitud = palabra.length();

        if (lista == null) {
            lista = new ArrayList<>();
            for (int i = 0; i < longitud; i++) {
                lista.add('_');
            }
        }

        System.out.println(lista.size());
        System.out.println(longitud);

        for (int i = 0; i < longitud; i++) {

            if (lista.get(i) == '_' && palabra.charAt(i) == elemento) {
                ch = lista.get(i);
                ch = elemento;
                lista.set(i, ch);
            }

        }

        System.out.println(lista.toString());
    }

}
