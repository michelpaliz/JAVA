package Testing.android;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import TestWorlde.Propio.main;

public class tres {

    public static void main(String[] args) {

        List<Integer> numerosDisponibles = new ArrayList<>();
        boolean isComputadora = true;

        List<Integer> numerosPrevios = new ArrayList<>();

        numerosDisponibles.add(0);
        numerosDisponibles.add(1);
        numerosDisponibles.add(2);
        numerosDisponibles.add(3);
        numerosDisponibles.add(4);
        numerosDisponibles.add(5);
        numerosDisponibles.add(6);
        numerosDisponibles.add(7);
        numerosDisponibles.add(8);

        if (isComputadora) {
            int numeroComputadora;
            boolean repetido = false; // esto para el while
            int numero = 0;

            do {
                numeroComputadora = randBetween(0, numerosDisponibles.size());
                for (int j = 0; j < numerosDisponibles.size(); j++) {

                    if (numerosDisponibles.get(j) == (numeroComputadora)) {
                        System.out.println("son iguales");
                        numerosDisponibles.remove(j);
                        repetido = false;
                    }

                    if (numerosDisponibles.get(j) != numeroComputadora) {
                        System.out.println("son distintos");
                        numero = numeroComputadora;
                        repetido = true;
                    }

                }

            } while (!repetido); // aqui no va a pasar has

            numerosPrevios.add(numero);

            System.out.println(numerosPrevios);

        }
    }

    public static int randBetween(int start, int end) {
        return start + (int) Math.round(Math.random() * (end - start));
    }

}
