public class HilosVarios extends Thread {

    public void run(){
        for (int i = 0; i < 15; i++) {
            System.out.println("Ejecutando hilo " + getName());
            try {
                Thread.sleep(700);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

     public static class HilosVarios2 extends Thread{

        private final Thread thread;

        public HilosVarios2(Thread thread){
            this.thread = thread;
        }

        public void run(){

            try {
                thread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            for (int i = 0; i < 15; i++) {
                System.out.println("Ejecutando hilo " + getName());
                try {
                    Thread.sleep(700);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }


    }
}
