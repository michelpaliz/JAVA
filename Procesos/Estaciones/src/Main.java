import Model.Estacion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final Scanner sc = new Scanner(System.in);
    private static final int MAX_SIZE = 4;
    private static final List<Estacion> estaciones = new ArrayList<>(MAX_SIZE);
    private static Estacion miEstacion;

    public static void main(String[] args) {

        try {
            Thread.sleep(1000);
            crearEstaciones();
            System.out.println(seleccionarEstacion());
            printarEstacion();
        } catch (InterruptedException e) {
            System.out.println("Main thread Interrupted");
        }
        System.out.println("Main thread exiting.");
    }

    //seleccionamos la estacion
    public static boolean seleccionarEstacion(){
        System.out.println("Introduce el nombre de la estacion ");
        String respuesta = sc.nextLine();
        for (Estacion estacion : estaciones) {
                if (estacion.getNombre().equalsIgnoreCase(respuesta)){
                    miEstacion = estacion;
                    return true;
                }
        }
        return false;
    }

    //Utilizamos el runnable para despues utilizar el thread
    public static void printarEstacion(){
        for ( String mes: miEstacion.getMeses()) {
            Thread thread = new Thread(miEstacion, mes);
            thread.start();
        }

//        for (int i = 0; i < miEstacion.getMeses().size(); i++) {
//            for (int j = 0; j < i; j++) {
//                Thread thread = new Thread(miEstacion.getMeses().get(j).toString());
//                thread.start();
//            }
//
//        }

//        for (int i = 0; i < miEstacion.getMeses().size() ; i++) {
//            Thread thread = new Thread(miEstacion.getMeses().get(i));
//            thread.start();
//        }


            Thread thread = new Thread(miEstacion);
            thread.start();
    }


    //Creamos los datos podrian haber sido creados en el mismo modulo puesto que son final.
    public static void crearEstaciones(){
        miEstacion = new Estacion("Primavera", Arrays.asList("Marzo", "Abril", "Mayo"));
        estaciones.add(miEstacion);
        miEstacion = new Estacion("Otonyo", Arrays.asList("Septiembre", "Agosto", "Octubre"));
        estaciones.add(miEstacion);
        miEstacion = new Estacion("Invierno", Arrays.asList("Diciembre", "Enero", "Febrero"));
        estaciones.add(miEstacion);
        miEstacion = new Estacion("Verano", Arrays.asList("Junio", "Julio", "Agosto"));
        estaciones.add(miEstacion);

    }







}