<h1>Gestion de Archivos</h1>
<h2>Ejercicio 1. Creacion de un archivo</h2>
<p>Utilizamos la clase File file = new FIle()</p>

```java
        File f1 = new File("/home/michael/Desktop/Tema12");
        System.out.println("Exists: " + f1.exists());
        System.out.println("Directory: " + f1.isDirectory());
        for (String arg : args) {
            System.out.println(args);
        }
        if (args.length != 1) {
            System.out.println("Use: exer1 ");
            System.exit(-1);
        }
```

<h2>Ejercicio 2.Mostrar archivos y directorios que estan en la carpeta</h2>
<p>Utilizaremos la funcion file.list()</p>

``` java
 if (file.isDirectory()) {
            String[] files = file.list();
            for (int i = 0; i < Objects.requireNonNull(files).length; i++) {
                System.out.println(files[i]);
            }
        }
```


<h2>Ejercicio 3 </h2>

```java

FIle file = new File(System.getProperty("user.dir"));


//Obten el nombre de la carpeta

file.getName();

//Obten la ruta absoluta

file.getAbsolutePath();

//Obten los permisos de lecturas

file.canWrite();

//Obten los permisos de escritura

file.canRead();

//Obten si es oculto o no

file.isHidden();

```

<h2>Ejercicio 4. La fecha de la ultima modificacion</h2>
<p>El método lastModified de la clase File devuelve la última fecha de modificación como un long (Unix timestamp milisegundos que han pasado desde el 1 de Enero de de 1970). Por ello utilizamos un SimpleDateFormat para darle el formato que nos interese</p>

```java
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        System.out.println("Fecha última modificación: " + sdf.format(file.lastModified()));
        /** Cambiamos la fecha de modificación */
        System.out.println("Cambiamos la fecha de modificación ...");
        if (file.setLastModified(new Date().getTime())) {
            System.out.println("Fecha de modificación cambiada correctamente");
        }
        /**
         * La volvemos a mostrar para comprobar que se ha efectuado correctamente el
         * cambio
         */
        System.out.println("Fecha última modificación: " + sdf.format(file.lastModified()));

```
<h1>Manipulacion de directorios</h1>
<h2>Ejercicio  5 </h2>
<p>Crea un archivo</p>

```java

   /**
     * Crea un fichero con el nombre archivo en la carpeta directorio.
     * @param directorio Carpeta donde se va a crear el archivo
     * @param archivo Nombre del archivo a crear
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

    //Ejecutamos el programa
    
        String archivo = "prueba.txt";
        if(GestionArchivos.crearArchivo(path, archivo)) {
            System.out.println("Archivo " + archivo + " creado correctamente");
        } else {
            System.out.println("No se pudo crear el archivo " +archivo);
        }
        GestionArchivos.listarDirectorio(path);
        GestionArchivos.verInfo(path, "hola.txt");


```

<p>Lista un directorio donde puedas visualizar el tipo de fichero (fichero = f , directorio = d), el tamanyo y los permisos de letctura y escritura </p>


```java

    /**
     * Muestra los elementos que están en el directorio indicado como parámetro.
     * Esta versión no es recursiva, es decir, no se muestra el contenido de las carpetas internas.
     * @param directorio Carpeta a listar
     */
    public static void listarDirectorio(String directorio) {
        File f1 = new File(directorio);
        File[] ficheros = f1.listFiles();
        UnidadMedida unidad;
        for(int i = 0; i < ficheros.length; i++) {
            unidad = UnidadMedida.BYTE;
            String nombre = ficheros[i].getName();
            char tipo = ficheros[i].isDirectory() ? 'd' : 'f';
            double size = ficheros[i].length();
            while(size > 1024) {
                size = size / 1024;
                unidad = unidad.next();
            }
            String permisos = ficheros[i].canRead() ? "r" : "-";
            permisos += ficheros[i].canWrite() ? "w" : "-";
            permisos += ficheros[i].canExecute() ? "x" : "-";
            System.out.printf("%-20s %c %15.2f %-6s %-3s\n", nombre, tipo, size, unidad.toString(), permisos);
        }
    }

```

<p> Crea un metodo donde puedas ver el nombre, la ruta absoluta, si se puede escribir, si se puede leer, el tamanyo, si es un directorio o si es un archivo.</p>

```java

    /**
     * Muestra información sobre el fichero indicado en la ruta representada por directorio y archivo
     * @param directorio Carpeta donde se encuentra el archivo
     * @param archivo Fichero del que se quiere mostrar la información
     */
    public static void verInfo(String directorio, String archivo) {
        File f1 = new File(directorio, archivo);
        System.out.println("Nombre: " + f1.getName());
        System.out.println("Ruta absoluta: " + f1.getAbsolutePath());
        System.out.println("Permiso lectura: " + f1.canRead());
        System.out.println("Permiso escritura: " + f1.canWrite());
        long bytes = f1.length();
        System.out.printf("Tamaño: %d bytes / %.8f KB / %.8f MB\n", bytes, (bytes/1024f), (bytes/1024f)/1024f);
        System.out.println("Directorio: " + f1.isDirectory());
        System.out.println("Archivo: " + f1.isFile());
    }

```


