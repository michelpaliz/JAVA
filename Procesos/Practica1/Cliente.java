import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Cliente extends Thread {


    public Cliente() {
    }


    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 2000)) {
            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
            Scanner scanner = new Scanner(System.in);

            boolean leaving = false;

            do {

                String message = dataInputStream.readUTF();
                System.out.println(message);

                int num = scanner.nextInt();
                dataOutputStream.writeInt(num);

                message = dataInputStream.readUTF();
                System.out.println(message);
                leaving = dataInputStream.readBoolean();

            } while (!leaving);
            scanner.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


//    @Override
//    public void run() {
//        //Read what the client has written
//        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
//        try (Socket socket = new Socket("localhost", 2000)){
//            //We create the client's socket with a local connection and using the 2000 port.
//            //We create buffers to be able to connect the client with our server
//            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
//            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
//            //Variable that we create and it starts with -1
//            int clientNumber = -1;
//            int correct = -1;
//            int tries = 0;
//            System.out.println("Try to guess the secret number");
//            //We keep asking the client until our server sends number 1 which is the key for our goal.
//            while (clientNumber != 1) {
//                //We reset the client number
//                clientNumber = -1;
//                //Check if it's a valid number
//                while (clientNumber == -1) {
//                    try {
//                        System.out.println("Choose number ");
//                        //We store the input number in our buffer
//                        clientNumber = Integer.parseInt(bufferedReader.readLine());
//                    } catch (Exception e) {
//                        System.out.println("It's not a valid number");
//                    }
//                }
//                //Send the data into our buffer
//                dataOutputStream.writeInt(clientNumber);
//                correct = dataInputStream.readInt();
//                if (correct == 0) {
//                    //if we receive 0 is a lesser number
//                    System.out.println("The secret number is less than " + clientNumber);
//                } else if (correct == 2) {
//                    //if we receive 2 is a greater number
//                    System.out.println("The secret number is greater than " + clientNumber);
//                }
//                //we count a new intent
//                tries++;
//            }
//            //Thus we're outside the while statement we assume that the client has won the game
//            System.out.println("Congratulations you guessed the number which was " + clientNumber);
//            System.out.println("You won the game with a number this number of guesses " + tries);
//            System.out.println("The server is going offline");
//
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//
//        super.run();
//    }
}
