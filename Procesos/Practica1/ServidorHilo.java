import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Random;

public class ServidorHilo extends Thread {
    private Socket socket;

    public ServidorHilo(Socket sock) {
        this.socket = sock;
    }

    @Override
    public void run() {
        System.out.println("Client connected");
        DataOutputStream dataOutputStream = null;
        DataInputStream dataInputStream = null;

        try {
            dataOutputStream = new DataOutputStream(socket.getOutputStream());
            dataInputStream = new DataInputStream(socket.getInputStream());
            Random random = new Random();
            int randomNumber = random.nextInt(100);
            int userNumber = 0;
            System.out.println("Num generated" + randomNumber);

            do {
                dataOutputStream.writeUTF("Write a number between 1 to 100");

                userNumber = dataInputStream.readInt();
                System.out.println("Number received is " + userNumber);

                if (userNumber == randomNumber) {
                    dataOutputStream.writeUTF("You won the game ");
                } else if (userNumber < randomNumber) {
                    dataOutputStream.writeUTF("The number must be greater ");
                } else {
                    dataOutputStream.writeUTF("The number must be smaller ");
                }

                dataOutputStream.writeBoolean(userNumber == randomNumber);
            } while (randomNumber != userNumber);
            socket.close();
            System.out.println("Client disconnected ");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

}

