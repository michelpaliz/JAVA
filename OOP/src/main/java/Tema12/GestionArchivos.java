package Tema12;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;

public class GestionArchivos {

    public static void main(String[] args) {
    }

    enum UnidadMedida {
        BYTE, KB, MB, GB, TB, PB, EB, ZB, YB;

        /**
         * Proporciona la siguiente unidad de medida
         * 
         * @return Siguiente unidad de medida
         */
        public UnidadMedida next() {
            UnidadMedida[] unidades = UnidadMedida.values();
            int ordinal = this.ordinal();
            ordinal = ++ordinal % unidades.length;
            return unidades[ordinal];
        }

        /**
         * Obtiene la representación de la unidad de medida como un String
         * 
         * @return String con la representación de la unidad de medida
         */
        @Override
        public String toString() {
            switch (this) {
                case BYTE:
                    return "bytes";
                case KB:
                    return "KB";
                case MB:
                    return "MB";
                case GB:
                    return "GB";
                case TB:
                    return "TB";
                case PB:
                    return "PB";
                case EB:
                    return "EB";
                case ZB:
                    return "ZB";
                case YB:
                    return "YB";
            }
            return "";
        }
    }

    /**
     * Constructor privado para evitar que la clase sea instanciada
     * ya que todos sus métodos son estáticos
     */
    private GestionArchivos() {
        // Para evitar que se puedan crear instancias
    }

    /**
     * Crea un fichero con el nombre archivo en la carpeta directorio.
     * 
     * @param directorio Carpeta donde se va a crear el archivo
     * @param archivo    Nombre del archivo a crear
     * @return true si se ha creado, false en caso de error
     */
    public static boolean crearArchivo(String directorio, String archivo) {
        File f1 = new File(directorio, archivo);
        try {
            return f1.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Muestra los elementos que están en el directorio indicado como parámetro.
     * Esta versión no es recursiva, es decir, no se muestra el contenido de las
     * carpetas internas.
     * 
     * @param directorio Carpeta a listar
     */
    public static void listarDirectorio(String directorio) {
        File f1 = new File(directorio);
        File[] ficheros = f1.listFiles();
        UnidadMedida unidad;
        for (int i = 0; i < ficheros.length; i++) {
            unidad = UnidadMedida.BYTE;
            String nombre = ficheros[i].getName();
            char tipo = ficheros[i].isDirectory() ? 'd' : 'f';
            double size = ficheros[i].length();
            while (size > 1024) {
                size = size / 1024;
                unidad = unidad.next();
            }
            String permisos = ficheros[i].canRead() ? "r" : "-";
            permisos += ficheros[i].canWrite() ? "w" : "-";
            permisos += ficheros[i].canExecute() ? "x" : "-";
            System.out.printf("%-20s %c %15.2f %-6s %-3s\n", nombre, tipo, size, unidad.toString(), permisos);
        }
    }

    /**
     * Muestra información sobre el fichero indicado en la ruta representada por
     * directorio y archivo
     * 
     * @param directorio Carpeta donde se encuentra el archivo
     * @param archivo    Fichero del que se quiere mostrar la información
     */
    public static void verInfo(String directorio, String archivo) {
        File f1 = new File(directorio, archivo);
        System.out.println("Nombre: " + f1.getName());
        System.out.println("Ruta absoluta: " + f1.getAbsolutePath());
        System.out.println("Permiso lectura: " + f1.canRead());
        System.out.println("Permiso escritura: " + f1.canWrite());
        long bytes = f1.length();
        System.out.printf("Tamaño: %d bytes / %.8f KB / %.8f MB\n", bytes, (bytes / 1024f), (bytes / 1024f) / 1024f);
        System.out.println("Directorio: " + f1.isDirectory());
        System.out.println("Archivo: " + f1.isFile());
    }

    /**
     * Muestra el contenido del fichero de texto indicado por la ruta representada
     * por directorio y archivo
     * 
     * @param directorio Carpeta donde se encuentra el fichero
     * @param archivo    Fichero a mostrar
     */
    public static void mostrarContenido(String directorio, String archivo) {
        FileReader fr = null;
        try {
            fr = new FileReader(directorio + File.separator + archivo);
            int c;
            while ((c = fr.read()) >= 0) {
                System.out.printf("%c", c);
            }
            System.out.println();
        } catch (FileNotFoundException e) {
            System.out.println("Archivo " + archivo + " no encontrado");
            e.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } finally {
            if (fr != null) {
                try {
                    fr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Muestra el contenido del fichero de texto indicado por el parámetro path.
     * Utiliza un buffer para realizar la lectura más rápida.
     * 
     * @param path Ruta del archivo
     */
    public static void mostrarContenidoBuffered(String path) {
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                System.out.println(linea);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Muestra el contenido del fichero de texto indicado por la ruta representada
     * por directorio y archivo
     * Utiliza un buffer para realizar la lectura más rápida.
     * 
     * @param directorio Carpeta donde se encuentra el fichero
     * @param archivo    Fichero a mostrar
     */
    public static void mostrarContenidoBuffered(String directorio, String archivo) {
        mostrarContenidoBuffered(directorio + File.separator + archivo);
    }

    /**
     * Muestra el contenido del fichero binario indicado por la ruta representada
     * por directorio y archivo.
     * Utiliza la clase FileInputStream para realizar una lectura binaria.
     * 
     * @param directorio Carpeta donde se encuentra el fichero
     * @param archivo    Fichero a mostrar
     */
    public static void mostrarContenidoHexadecimal(String directorio, String archivo) {
        /**
         * En este caso se ha utilizado la versión de try que permite indicar entre
         * paréntesis
         * operaciones de entrada/salida que serán automáticamente cerradas al finalizar
         * el try.
         * Por tanto no es necesario hacer una llamada a fis.close().
         */
        /*
         * try (
         * FileInputStream fis = new FileInputStream(directorio + File.separator +
         * archivo);
         * ) {
         */
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(directorio + File.separator + archivo);
            int c;
            int cont = 0;
            while ((c = fis.read()) >= 0) {
                cont++;
                System.out.printf("%02X ", c);
                if (cont % 20 == 0) {
                    System.out.println();
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
