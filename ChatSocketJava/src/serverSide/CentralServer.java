package serverSide;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

public class CentralServer implements Runnable{

	protected static HashMap<String, String> clients= new HashMap<>();
	
	public void run(){
		ServerSocket srv;
		try {
			srv = new ServerSocket(1234);
			System.out.println("The Central Server is running on the port 1234...");
			while(true) {
				Socket newClient=srv.accept();
				System.out.println("New connection. User IP:"+newClient.getInetAddress().getCanonicalHostName());
				Thread t=new Thread(new Request(newClient));
				t.start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}

}
