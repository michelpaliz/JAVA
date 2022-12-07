import Modulo.PingPong;

public class Main {

    public static void main(String[] args) {
        PingPong ping = new PingPong("Ping");
        PingPong pong = new PingPong("Pong");

        Thread thread1 = new Thread(ping);
        Thread thread2 = new Thread(pong);
        thread1.start();
        thread2.start();


    }
}