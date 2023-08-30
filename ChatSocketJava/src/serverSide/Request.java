package serverSide;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import interfaceChat.*;

public class Request implements Runnable {

	private Socket newClient;
	
	
	public Request(Socket newClient) {
		this.newClient = newClient;
	}

	public void run() {
		CentralServer.clients.put(this.newClient.getInetAddress().getCanonicalHostName(), this.newClient.getInetAddress().getCanonicalHostName());
		String message = "";
		try {
			Scanner scan = new Scanner(this.newClient.getInputStream());
			message = scan.next();
			System.out.println("Batata");
			for(String ip: CentralServer.clients.values()) {
						System.out.println("pao");
						Socket resposta=new Socket(ip, 4321);
						PrintWriter write=new PrintWriter(resposta.getOutputStream(),true);
						UI.textAreaServer.append("\n"+newClient.getInetAddress().getCanonicalHostName()+": "+message);
						System.out.println("Request: "+message);
						write.println(message);
				}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
