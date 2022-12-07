public class Main {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Hello world!");
        HilosVarios hilo1 = new HilosVarios();
        HilosVarios.HilosVarios2 hilo2 = new HilosVarios.HilosVarios2(hilo1);
        hilo2.start();
        hilo1.start();
        //Se ejucatara el hilo principal antes de que los otros hilos terminen.
        System.out.println("Terminadas las tareas");
    }


}