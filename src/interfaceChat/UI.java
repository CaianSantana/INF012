package interfaceChat;
import clienteSide.*;
import serverSide.*;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.time.Clock;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class UI {

	private JFrame frame;
	public static JTextArea textAreaServer = new JTextArea();;
	private JTextArea textAreaUser;
	private static int acessKey;
	private static ClientReceiverServer receiver=  new ClientReceiverServer();
	


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UI window = new UI();
					window.frame.setVisible(true);
					Thread threadCentralServer = new Thread(new CentralServer());
					threadCentralServer.start();
					Thread threadReceiverServer = new Thread(receiver);
					threadReceiverServer.start();
					
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
		
		
		JButton btnEnviar = new JButton("Enviar");
		btnEnviar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Thread threadClient=  new Thread(new Client(textAreaUser.getText()));
				threadClient.start();
				textAreaUser.setText(null);
			}
		});
		
		btnEnviar.setBounds(172, 333, 89, 23);
		frame.getContentPane().add(btnEnviar);
	}

}

