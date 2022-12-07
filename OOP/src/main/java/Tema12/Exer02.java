package Tema12;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.Objects;

public class Exer02 {

    // Muestra los archivos y directorios que estan en la carpeta del ejercicio.Solo
    // los del primer nivel(no recursivo)

    public static void main(String[] args) {
        File f1 = new File("/home/michael/Desktop/GitHub/OOP");
        System.out.println("Opcion 1");
        mostrarArchivoPrimerNivel(f1);
        System.out.println("Opcion 2");
        mostrarArchivoPrimerNivel2(f1);
        System.out.println("Opcion 3");
        String path = "/home/michael/Desktop/GitHub/OOP";
        String extension = ".java";
        mostrarArchivoPrimerNivel3(path, extension);
    }

    public static void mostrarArchivoPrimerNivel(File file) {
        if (file.isDirectory()) {
            String[] files = file.list();
            for (int i = 0; i < Objects.requireNonNull(files).length; i++) {
                System.out.println(files[i]);
            }
        }
    }

    public static void mostrarArchivoPrimerNivel2(File file) {
        // Convertimos el path en una array de tipo String
        String[] ficheros = file.list();
        System.out.println(ficheros.length);
        for (int i = 0; i < ficheros.length; i++) {
            System.out.println(ficheros[i]);
        }
    }

    public static void mostrarArchivoPrimerNivel3(String path, String extension) {
        File f1 = new File(path);
        System.out.println(Arrays.toString(f1.list(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return path.toLowerCase().endsWith(extension);
            }
        })));

    }

 

}
