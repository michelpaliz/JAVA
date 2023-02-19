import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Servidor {

    public static void main(String[] args) {

        try (ServerSocket serverSocket = new ServerSocket(1700)) {
            System.out.println("Server initiated");
            while (true) {
                Socket socket = serverSocket.accept();
                ServidorHilo servidorHilo = new ServidorHilo(socket);
                servidorHilo.start();
            }
        } catch (IOException e) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, e);
            throw new RuntimeException(e);
        }

    }
}
