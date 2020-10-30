package Client;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;
import javax.swing.border.LineBorder;

public class ClientWindow extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	private Image img_titleimage = new ImageIcon(ClientWindow.class.getResource("Images/chatroom.png")).getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
	private Image img_conversationimage = new ImageIcon(ClientWindow.class.getResource("Images/conversation.png")).getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH);
	private Image img_msgimage = new ImageIcon(ClientWindow.class.getResource("Images/msg.png")).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);

	
	private JPanel contentPane;
	private JTextField txtMessage;
	private JTextArea history;
	
	private Client client;
	private Thread recieve;
	
	private boolean running = false;
	private JLabel lblMessage;
	private JPanel paneltop;
	private JLabel lblConversation;
	private JLabel lbltitle;
	private JPanel panelX;
	private JLabel lblX;
	private JButton btnLogout;
	private JPanel paneltitle;
	private JLabel lbltitleimage;
	private JPanel panelconversations;
	private JPanel panelmsg;
	private JLabel lblconversationimage;
	private JLabel lblmessageimage;

	public ClientWindow(String name, String address, int port) {
		client = new Client(name, address, port);
		setTitle("Chat Client - " + client.getName());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		boolean connect = client.openConnection(address);
		if (!connect) {
			System.err.println("Connection failed!");
			console("Connection failed!");
		}
		createWindow();
		console("\tConnecting to " + address + ":" + port);
		String connection = "/c/" + name;
		client.send(connection.getBytes());
		
		running = true;
		recieve = new Thread("Recieve"){
			@Override
			public void run() {
				while(running){
					String message = client.receive();
					process(message);
				}
			}
		};
		recieve.start();
	}
	
	private void process(String message){
		if(message.startsWith("/m/")){
			console(message.substring(3));
		}
	}

	private void createWindow() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		setSize(880, 550);
		setUndecorated(true);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 222, 173));
		contentPane.setBorder(new LineBorder(new Color(0, 0, 255), 4));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		history = new JTextArea();
		history.setEditable(false);
		JScrollPane scroll = new JScrollPane(history);
		scroll.setBounds(40, 125, 800, 375);
		contentPane.add(scroll);

		txtMessage = new JTextField();
		txtMessage.setBounds(180, 515, 550, 20);
		txtMessage.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					send(txtMessage.getText());
				}
			}
		});
		contentPane.add(txtMessage);
		txtMessage.setColumns(10);

		JButton btnSend = new JButton("Send");
		btnSend.setBackground(new Color(0, 255, 0));
		btnSend.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		btnSend.setBounds(760, 514, 75, 25);
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				send(txtMessage.getText());
			}
		});
		contentPane.add(btnSend);
		
		lblMessage = new JLabel("Message :");
		lblMessage.setHorizontalAlignment(SwingConstants.CENTER);
		lblMessage.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		lblMessage.setBounds(80, 508, 100, 30);
		contentPane.add(lblMessage);
		
		paneltop = new JPanel();
		paneltop.setBackground(new Color(32, 178, 170));
		paneltop.setBorder(new LineBorder(new Color(0, 0, 255), 4));
		paneltop.setBounds(0, 0, 880, 68);
		contentPane.add(paneltop);
		paneltop.setLayout(null);
		
		lbltitle = new JLabel("CHAT ROOM");
		lbltitle.setHorizontalAlignment(SwingConstants.CENTER);
		lbltitle.setFont(new Font("Comic Sans MS", Font.BOLD, 25));
		lbltitle.setBounds(352, 9, 203, 49);
		paneltop.add(lbltitle);
		
		panelX = new JPanel();
		panelX.setBackground(new Color(32, 178, 170));
		panelX.setBounds(850, 5, 25, 25);
		paneltop.add(panelX);
		panelX.setLayout(null);
		
		lblX = new JLabel("X");
		lblX.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				if(JOptionPane.showConfirmDialog(null, "Are you sure you want to close this application?", "Confirmation", JOptionPane.YES_NO_OPTION) == 0) {
					
					ClientWindow.this.dispose();
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				lblX.setForeground(Color.RED);
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				lblX.setForeground(Color.BLACK);
			}	
				
		});		
		lblX.setHorizontalAlignment(SwingConstants.CENTER);
		lblX.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		lblX.setBounds(0, 0, 20, 20);
		panelX.add(lblX);
		
		btnLogout = new JButton("Logout");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(JOptionPane.showConfirmDialog(null, "Do you want to logout?", "Confirmation", JOptionPane.YES_NO_OPTION) == 0) {
					Login log = new Login();
					log.setVisible(true);
					ClientWindow.this.dispose();
				}
			}
		});
		btnLogout.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		btnLogout.setBackground(new Color(255, 255, 0));
		btnLogout.setBounds(785, 38, 85, 21);
		paneltop.add(btnLogout);
		
		paneltitle = new JPanel();
		paneltitle.setBackground(new Color(32, 178, 170));
		paneltitle.setBounds(300, 9, 52, 52);
		paneltop.add(paneltitle);
		paneltitle.setLayout(null);
		
		lbltitleimage = new JLabel("");
		lbltitleimage.setHorizontalAlignment(SwingConstants.CENTER);
		lbltitleimage.setBounds(0, -8, 50, 50);
		lbltitleimage.setIcon(new ImageIcon(img_titleimage));
		paneltitle.add(lbltitleimage);
		
		lblConversation = new JLabel("Conversation");
		lblConversation.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		lblConversation.setBounds(40, 94, 120, 25);
		contentPane.add(lblConversation);
		
		panelconversations = new JPanel();
		panelconversations.setBackground(new Color(255, 222, 173));
		panelconversations.setBounds(164, 78, 45, 45);
		contentPane.add(panelconversations);
		panelconversations.setLayout(null);
		
		lblconversationimage = new JLabel("");
		lblconversationimage.setHorizontalAlignment(SwingConstants.CENTER);
		lblconversationimage.setBounds(1, 1, 40, 40);
		lblconversationimage.setIcon(new ImageIcon(img_conversationimage));
		panelconversations.add(lblconversationimage);
		
		panelmsg = new JPanel();
		panelmsg.setBackground(new Color(255, 222, 173));
		panelmsg.setBounds(42, 505, 38, 38);
		contentPane.add(panelmsg);
		panelmsg.setLayout(null);
		
		lblmessageimage = new JLabel("");
		lblmessageimage.setBounds(1, 1, 35, 35);
		lblmessageimage.setHorizontalAlignment(SwingConstants.CENTER);
		lblmessageimage.setIcon(new ImageIcon(img_msgimage));
		panelmsg.add(lblmessageimage);

		setVisible(true);

		txtMessage.requestFocusInWindow();
	}

	private void send(String message) {
		if (message.equals("")) return;
		message = client.getName() + ": " + message;
		console(message);
		message = "/m/" + message;
		client.send(message.getBytes());
		txtMessage.setText("");
	}

	public void console(String message) {
		history.append(message + "\n\r");
		history.setCaretPosition(history.getDocument().getLength());
	}
}