import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {

    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 1700)) {
            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
            Scanner scanner = new Scanner(System.in);
            scanner.useDelimiter("\n");
            System.out.println("Introduce the number of elements you'd like to insert ");
            int length = scanner.nextInt();
            dataOutputStream.writeInt(length);
            int cont = 0;


            while (!(cont == length)) {
                System.out.println("Now introduce the letters that you'd like to insert");

                String character = scanner.next();
                try {

                    dataOutputStream.writeByte(cont);
                    dataOutputStream.writeUTF(character);
                    System.out.println(dataInputStream.readUTF());

                } catch (IOException e) {
                    socket.close();
                }
                cont++;
            }

            dataOutputStream.flush();
            dataOutputStream.close();
            scanner.close();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
