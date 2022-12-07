package Tema12;

import java.io.File;
import java.io.IOException;

import Lib.Util;

public class Exer05 {

    // Crea los siguientes metodos
    // 1.Crear un Archivo ------ boolean crearArchivo(String directorio, String
    // archivo)
    // 2.Listar un directorio ----- String listarDirectorio(String directorio) nos
    // indicara el tipo de directorio fichero = f , directorio = d, el tamnyo y los
    // permisos de lectura y escritura.
    // 3.Ver la informacion ----- void verInfo(String directorio, String archivo)
    // nos mostrara el nombre, la ruta absoluta, si se puede escribir, si se puede
    // leer, el tamanyo, si es un directorio y si es un archivo.
    // NOTA: Los parametros seran solicitados al usuario/home/michael/Desktop/GitHub

    public static void main(String[] args) {
        String path = System.getProperty("user.dir");
        File file1 = new File(path);
        checkFile(file1);
        System.out.println("Introduce the path");
        String user = Util.myInput.nextLine();
        File file2 = new File(user);
        System.out.println("Introduce the file's name");
        if (createFIle2(file2.getAbsolutePath(), Util.myInput.nextLine())) {
            System.out.println("FIle created sucessfully");
        } else {
            System.out.println("error");
        }
        listDIrectories(user);

    }

    public static String checkRute(String path) {
        File comprobante = new File(path);
        String respuesta;
        if (comprobante.exists()) {
            if (comprobante.isDirectory()) {
                System.out.println("La ruta es correcta");
                return path;
            } else {
                System.out.print(
                        "La ruta es correcta, pero ten encuenta que es un archivo, no podras gestionar nuevas carpetas  deseas cambiar la ruta\nSi/No: ");
                respuesta = Util.myInput.nextLine();
                while (!respuesta.equalsIgnoreCase("Si") && !respuesta.equalsIgnoreCase("No")) {
                    System.out.print("Introduce Si o No\nRespuesta: ");
                    respuesta = Util.myInput.nextLine();
                }
                if (respuesta.equalsIgnoreCase("Si")) {
                    System.out.print("Introduce la nueva ruta\nNueva Ruta: ");
                    return Util.myInput.nextLine();
                } else {
                    return path;
                }
            }
        } else {
            return null;
        }
    }

    /**
     * 
     * @param dir  path
     * @param file name
     * @return the file that we just created
     */

    public static boolean createFIle2(String dir, String file) {
        for (int i = 0; i < file.length(); i++) {
            // primero chekeamos si el nombre es correcto
            if (file.charAt(i) == ' ' || file.charAt(i) == '/' || file.charAt(i) == '\\') {
                System.out.println("The name must have at least one character");
                return false;
            }
        }
        // Creamos el archivo con el constructor de la clase
        File fichero = new File(dir, file);
        if (fichero.exists()) {
            System.out.println("return true");
            return true;
        }
        try {
            return fichero.createNewFile();
        } catch (IOException ex) {
            return false;
        }
    }

    /**
     * 
     * @param dir  path
     * @param file name
     * @return the file that we just created
     */

    public static boolean createFIle(String dir, String file) {

        File f = new File(dir + File.separator + file);
        try {
            if (f.createNewFile()) {
                System.out.println("FIle created");
                return true;
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
            System.out.println("There was a problem");
        }
        return false;
    }

    /**
     * 
     * @param dir path
     */

    public static void listDIrectories(String dir) {
        File f = new File(dir);
        File[] content = f.listFiles();
        if (content != null) {
            for (File file : content) {
                if (file.isDirectory()) {
                    System.out.println(file.getName() + "d");
                    listDIrectories(file.getAbsolutePath());
                } else {
                    System.out.println(file.getName() + "f");
                }
            }
        }

    }

    /**
     * 
     * @param f
     * @return
     */

    public static char checkFile(File f) {
        if (f.isDirectory()) {
            return 'd';
        } else {
            return 'f';
        }
    }

    /**
     * 
     * @param dir  path
     * @param file name
     */

    public static void showInf(String dir, String file) {
        File f = new File(dir + File.separator + file);
        System.out.println("size" + f.length() / Math.pow(1024, 3) + "GB");
    }

    /**
     * 
     * @param file
     * @return the ownerships for our file
     */

    public static String checkOwnership(File file) {
        StringBuilder pms = new StringBuilder();
        if (file.canRead()) {
            pms.insert(pms.length(), "r");
        } else {
            pms.insert(pms.length(), "-");
        }
        if (file.canWrite()) {
            pms.insert(pms.length(), "w");
        } else {
            pms.insert(pms.length(), "-");
        }
        if (file.canExecute()) {
            pms.insert(pms.length(), "x");
        } else {
            pms.insert(pms.length(), "-");
        }
        return pms.toString();
    }

    /**
     * 
     * @param dir
     * @param archive
     */

    public static void showInfo(String dir, String archive) {
        File fichero = new File(dir, archive);
        System.out.printf("%-10s" + "%-65s" + "%-5s" + "%-15s" + "%-1s\n", fichero.getName(), fichero.getAbsolutePath(),
                checkOwnership(fichero), fichero.length() + " bytes", checkFile(fichero));
    }

    // add a new method that has to be read and written
}
