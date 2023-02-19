

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Objects;


public class Cliente {

    public static void main(String[] args) {
        // TODO Auto-generated method stub

        MarcoCliente mimarco = new MarcoCliente();

        mimarco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

}


class MarcoCliente extends JFrame {

    public MarcoCliente() {

        setBounds(600, 300, 280, 350);

        LaminaMarcoCliente milamina = new LaminaMarcoCliente();

        add(milamina);

        setVisible(true);
		addWindowListener(new EnvioOnline());
    }

}

class EnvioOnline extends WindowAdapter {
    public void windowOpened(WindowEvent w) {
        try {
            Socket socket = new Socket("192.168.0.194", 9999);
            PaqueteEnvio paqueteEnvio = new PaqueteEnvio();
            paqueteEnvio.setMensaje("online");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectOutputStream.writeObject(paqueteEnvio);
            objectOutputStream.close();

        } catch (Exception e) {

        }
    }
}


class LaminaMarcoCliente extends JPanel implements Runnable {

    public LaminaMarcoCliente() {

        String nickUsuario = JOptionPane.showInputDialog("Nick: ");

        JLabel nNick = new JLabel("Nick");

        add(nNick);

        nick = new JLabel();

        add(nick);

        nick = new JLabel();

        nick.setText(nickUsuario);

        JLabel texto = new JLabel("CHAT");

        add(texto);

        ip = new JComboBox();

//        ip.addItem("Usuario 1");
//        ip.addItem("Usuario 2");
//        ip.addItem("Usuario 3");

        ip.addItem("192.168.0.197");
        ip.addItem("192.168.0.198");


        add(ip);

        campoChat = new JTextArea(12, 20);

        add(campoChat);

        campo1 = new JTextField(20);

        add(campo1);

        miboton = new JButton("Enviar");

        //Creamos la instacia del texto
        EnviaTexto enviaTexto = new EnviaTexto();
        miboton.addActionListener(enviaTexto);

        add(miboton);

        Thread thread = new Thread(this);

        thread.start();

    }

    @Override
    public void run() {
        try {
            ServerSocket serverSocket = new ServerSocket(9090);
            PaqueteEnvio paqueteEnvio;
            Socket socket;
            while (true) {
                socket = serverSocket.accept();
                ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
                paqueteEnvio = (PaqueteEnvio) objectInputStream.readObject();

                if (!paqueteEnvio.getMensaje().equals("online")){
                    campoChat.append("\n" + paqueteEnvio.getNick() + ": " + paqueteEnvio.getMensaje());
                }

                ArrayList<String> IpsMenu = new ArrayList<>();
                IpsMenu = paqueteEnvio.getIps();

                ip.removeAllItems();

                for (String s: IpsMenu) {
                    ip.addItem(s);
                }
//                campoChat.append("\n" + paqueteEnvio.getIps());
            }

        } catch (Exception e) {

        }

    }

    //Creamos el listener

    private class EnviaTexto implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println(campo1.getText());

            campoChat.append("\n" + campo1.getText());

            try {
                Socket socket = new Socket("192.168.9.127", 9999);

                PaqueteEnvio paqueteEnvio = new PaqueteEnvio();

                paqueteEnvio.setNick(nick.getText());

                paqueteEnvio.setIp(Objects.requireNonNull(ip.getSelectedItem()).toString());

                paqueteEnvio.setMensaje(campo1.getText());

                ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());

                objectOutputStream.writeObject(paqueteEnvio);

                objectOutputStream.close();

                socket.close();

//				DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
//				dataOutputStream.writeUTF(campo1.getText());
//				dataOutputStream.close();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    private JTextField campo1;

    private JComboBox ip;

    private JLabel nick;

    private JTextArea campoChat;

    private JButton miboton;

}

class PaqueteEnvio implements Serializable {

    private String nick, ip, mensaje;

    private ArrayList<String> Ips;

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public ArrayList<String> getIps() {
        return Ips;
    }

    public void setIps(ArrayList<String> ips) {
        Ips = ips;
    }
}