import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 1500)) {
            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
            Scanner scanner = new Scanner(System.in);
            scanner.useDelimiter("\n");
            System.out.println("Introduce the folder route to show ");
            String route = scanner.next();
            dataOutputStream.writeUTF(route);
            boolean exits = dataInputStream.readBoolean();

            if (exits){
                int length = dataInputStream.readInt();
                byte[] content = new byte[length];
                for (int i = 0; i < content.length; i++) {
                    content[i] = dataInputStream.readByte();
                }
                String contentStr = new String(content);
                System.out.println(contentStr);
                socket.close();
            }else {
                System.out.println("Error the file does not exists");
            }

            scanner.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
