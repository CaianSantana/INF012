package clienteSide;


import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import interfaceChat.UI;

public class Client implements Runnable {

	private static String message;
	private static String ip;
	
	public Client(String message) {
		Client.message = message;
	}
	public static String getMessage(){
		return message;
	}
	//127.0.0.1
	public void run() {
		Socket client;
		try {
			client = new Socket("10.135.3.249", 4321);
			
			try (Scanner scan = new Scanner(message)) {
				PrintWriter write = new PrintWriter(client.getOutputStream(), true);
				write.println(scan.next());
				UI.textAreaServer.append("\nMe: "+message);
				System.out.println("cliente: "+message);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.err.println("Scan do cliente deu pau");
			e.printStackTrace();
		}
	}

}
