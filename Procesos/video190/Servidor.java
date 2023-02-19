

import javax.swing.*;

import java.awt.*;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Servidor  {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		MarcoServidor mimarco=new MarcoServidor();
		
		mimarco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
	}	
}

class MarcoServidor extends JFrame implements Runnable {
	
	public MarcoServidor(){
		
		setBounds(1200,300,280,350);				
			
		JPanel milamina= new JPanel();
		
		milamina.setLayout(new BorderLayout());
		
		areatexto=new JTextArea();
		
		milamina.add(areatexto,BorderLayout.CENTER);
		
		add(milamina);
		
		setVisible(true);

		Thread 	thread = new Thread(this);

		thread.start();
		
		}
	
	private	JTextArea areatexto;

	@Override
	public void run() {
		try {

			ServerSocket serverSocket = new ServerSocket(9999);

			String nick, ip, mensaje;

			ArrayList<String> listaIp = new ArrayList<>();

			PaqueteEnvio paqueteEnvio;

			while (true){

				Socket socket = serverSocket.accept();


//				DETECTA ONLINE

//				InetAddress inetAddress = socket.getInetAddress();
//				String IpRemota = inetAddress.getHostAddress();
//				System.out.println(IpRemota);

				ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());

				paqueteEnvio = (PaqueteEnvio) objectInputStream.readObject();

				nick = paqueteEnvio.getNick();

				ip = paqueteEnvio.getIp();

				mensaje = paqueteEnvio.getMensaje();


//				DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
//
//				String mensajeTexto = dataInputStream.readUTF();
//
//				areatexto.append("\n" + mensajeTexto);
//

				if (!mensaje.equals(" online")){
					areatexto.append("\n" + nick + ": " + mensaje + " para " + ip);

					Socket enviaDestinatario = new Socket(ip, 9090);

					ObjectOutputStream objectOutputStream = new ObjectOutputStream(enviaDestinatario.getOutputStream());

					objectOutputStream.writeObject(socket);

					enviaDestinatario.close();

					socket.close();
				}else{
//					DETECTA ONLINE

					InetAddress inetAddress = socket.getInetAddress();
					String IpRemota = inetAddress.getHostAddress();
					System.out.println(IpRemota);

					listaIp.add(IpRemota);

					paqueteEnvio.setIps(listaIp);

					for (String s: listaIp) {
						System.out.println(s);
					}
				}


			}

		} catch (IOException | ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}
}
