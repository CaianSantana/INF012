package serverSide;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Request implements Runnable {

	private Socket newClient;
	
	
	public Request(Socket newClient) {
		this.newClient = newClient;
	}

	public void run() {
		CentralServer.clients.put(this.newClient.getInetAddress().getCanonicalHostName(), this.newClient.getInetAddress().getCanonicalHostName());
		String mensagem = "";
		try {
			Scanner scan = new Scanner(this.newClient.getInputStream());
			mensagem = scan.next();
			for(String ip: CentralServer.clients.values()) {
					//if(ip != newClient.getInetAddress().getCanonicalHostName()){
						Socket resposta=new Socket(ip,4321);
						PrintWriter write=new PrintWriter(resposta.getOutputStream(),true);
						System.out.println("Request: "+mensagem);
						write.println(mensagem);
					//}
				}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
