import java.io.*;
import java.net.Socket;

public class ServidorHilo extends Thread {
    private final Socket socket;

    public ServidorHilo(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        DataInputStream dataInputStream = null;
        DataOutputStream dataOutputStream = null;
        try {
            dataOutputStream = new DataOutputStream(socket.getOutputStream());
            dataInputStream = new DataInputStream(socket.getInputStream());


            int cont = 0;
            int num = dataInputStream.readInt();
            System.out.println("this is length " + num);

            while (!(cont == num)){
                byte messageType = dataInputStream.readByte();
                for (int i = 0; i < num; i++) {
                    if (i == messageType) {
                        int count = 0 ;
                        String content = dataInputStream.readUTF();
                        for (int j = 0; j < content.length(); j++) {
                            char character = content.charAt(j);
                             count += character;
                        }
                        String result = "Character is " + content + " and the ASCII value is " + count;
                        System.out.println("From the server we sent this data to the client -->  " + result);
                        dataOutputStream.writeUTF(result);
                    }
                }
                cont++;
            }

            socket.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                assert dataInputStream != null;
                dataInputStream.close();
                dataOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }
}
