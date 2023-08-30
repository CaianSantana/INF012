package clienteSide;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;
import java.util.Scanner;


public class ClientReceiverServer implements Runnable{

	
	static private String message;
	static private String ipClient;
	static private Boolean newAcess = false;
	
	public static String getMessage() {
		return ClientReceiverServer.message;
	}
	public static void setMessage(String message) {
		ClientReceiverServer.message = message;
	}
	public static String getIpClient() {
		return ClientReceiverServer.ipClient;
	}
	public static void setIpClient(String ipClient) {
		ClientReceiverServer.ipClient = ipClient;
	}
	public Boolean getAcess() {
		return newAcess;
	}

	
	public void run() {
		ServerSocket srv;
		
		try {
			srv = new ServerSocket(4321);
			System.out.println("The Receiver Server is running on the port 4321...");
			while(true) {
				Socket newClient = srv.accept();
				newAcess = false;
				if(newClient.isConnected())
					newAcess = true;
				setIpClient(newClient.getInetAddress().getCanonicalHostName());
				Scanner scan = new Scanner(newClient.getInputStream());
				setMessage(scan.next());
				
				System.out.println("ReceiverServer: "+message);
				
				}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
}
