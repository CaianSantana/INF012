package clienteSide;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;
import java.util.Scanner;

public class ClientReceiverServer implements Runnable{

	
	static private String message;
	static private String ipClient;
	static private int newAcessKey;
	
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
	public static int GenRandomNumber() {
		Random keyGenerator = new Random();
		return	keyGenerator.nextInt();
	}
	public static int getAcessKey() {
		return newAcessKey;
	}

	
	public void run() {
		ServerSocket srv;
		
		try {
			srv = new ServerSocket(4321);
			System.out.println("The Receiver Server is running on the port 4321...");
			while(true) {
				Socket newClient = srv.accept();
				if(newClient.isConnected())
					newAcessKey = GenRandomNumber();
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
