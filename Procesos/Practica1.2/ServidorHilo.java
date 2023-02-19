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

            String route = dataInputStream.readUTF();
            File file = new File(route);

            if (file.exists()){
                dataOutputStream.writeBoolean(true);
                BufferedReader bufferedReader = new BufferedReader(new FileReader(route));
                String line = "";
                StringBuilder text = new StringBuilder();
                while ((line = bufferedReader.readLine()) != null){
                    text.append(line).append("\r\n");
                }
                bufferedReader.close();
                byte[] fileContent = String.valueOf(text).getBytes();
                dataOutputStream.writeInt(fileContent.length);
                for (byte b : fileContent) {
                    dataOutputStream.writeByte(b);
                }
                socket.close();
//                for (int i = 0; i < fileContent.length; i++) {
//                    dataOutputStream.writeByte(fileContent[i]);
//                }

            }else {
                dataOutputStream.writeBoolean(false);

            }


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
