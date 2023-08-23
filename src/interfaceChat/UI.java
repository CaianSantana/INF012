package interfaceChat;
import clienteSide.*;
import serverSide.CentralServer;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UI {

	private JFrame frame;
	private static JTextArea textAreaServer = new JTextArea();;
	private JTextArea textAreaUser;
	private static int acessKey;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@SuppressWarnings({ "static-access" })
			public void run() {
				try {
					UI window = new UI();
					window.frame.setVisible(true);
					Thread threadCentralServer = new Thread(new CentralServer());
					threadCentralServer.start();
					Thread threadReceiverServer = new Thread(new ClientReceiverServer());
					threadReceiverServer.start();
//					Thread threadAtualizadora = new Thread(atualizaChat());
//					threadAtualizadora.start();
//					while(true) {
//						threadAtualizadora.sleep(1000);
//						threadAtualizadora.notify();
//					}
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public UI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 456, 411);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		
		textAreaServer.setEditable(false);
		textAreaServer.setBounds(30, 27, 380, 145);
		frame.getContentPane().add(textAreaServer);
	
		JTextArea textAreaUser = new JTextArea();
		textAreaUser.setBounds(30, 280, 380, 42);
		frame.getContentPane().add(textAreaUser);
		
		
		
		JButton btnTempAtualiza = new JButton("Atualizar");
		btnTempAtualiza.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				atualizaChat();
				btnTempAtualiza.setEnabled(false);
			}
		});
		
		JButton btnEnviar = new JButton("Enviar");
		btnEnviar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Thread threadClient=  new Thread(new Client(textAreaUser.getText()));
				threadClient.start();
				textAreaUser.setText(null);
				btnTempAtualiza.setEnabled(true);
				acessKey = ClientReceiverServer.getAcessKey();
				System.out.println("Chave de acesso " + acessKey);
				
			}
		});
		
		btnEnviar.setBounds(172, 333, 89, 23);
		frame.getContentPane().add(btnEnviar);
		
		btnTempAtualiza.setBounds(321, 333, 89, 23);
		frame.getContentPane().add(btnTempAtualiza);
		

	}
	public static void atualizaChat() {
		System.out.println("Chave de acesso " + acessKey +"\nNova Chave de Acesso: "+ ClientReceiverServer.getAcessKey());
		if(acessKey != ClientReceiverServer.getAcessKey()) {
			textAreaServer.setText(textAreaServer.getText()+"\n"+
					   ClientReceiverServer.getIpClient()+": "+
		               ClientReceiverServer.getMessage());
		}
	}
	
}
