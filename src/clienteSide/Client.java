package clienteSide;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client implements Runnable {

	private static String message;
	
	public Client(String message) {
		Client.message = message;
	}
	
	public void run() {
		Socket client;
		try {
			client = new Socket("127.0.0.1", 1234);
			try (Scanner scan = new Scanner(message)) {
				PrintWriter write = new PrintWriter(client.getOutputStream(), true);
				write.println(scan.next());
				System.out.println("cliente: "+message);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.err.println("Scan do cliente deu pau");
			e.printStackTrace();
		}
	}

}
