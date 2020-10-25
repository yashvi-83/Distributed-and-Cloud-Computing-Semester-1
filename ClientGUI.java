import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;



import java.awt.Color;
import javax.swing.JTextPane;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.SystemColor;
import java.io.IOException;
import java.net.BindException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Arrays;
import java.util.List;

import javax.swing.JScrollBar;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.Window.Type;

public class ClientGUI extends JFrame {
	
	private Image img_client = new ImageIcon(ClientGUI.class.getResource("Images/chatroom.png")).getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
	private Image img_conversation = new ImageIcon(ClientGUI.class.getResource("Images/conversation.png")).getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
	private Image img_onlinepeople = new ImageIcon(ClientGUI.class.getResource("Images/online.png")).getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
	private Image img_message = new ImageIcon(ClientGUI.class.getResource("Images/msg.png")).getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
	

	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClientGUI frame = new ClientGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ClientGUI() {
		setTitle("Chat Room");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(400, 150, 700, 475);
		setUndecorated(true);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 222, 173));
		contentPane.setBorder(new LineBorder(Color.BLUE, 3));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTextPane txtpnOnlineUser = new JTextPane();
		txtpnOnlineUser.setBackground(new Color(255, 222, 173));
		txtpnOnlineUser.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		txtpnOnlineUser.setText("Online Users");
		txtpnOnlineUser.setBounds(504, 95, 99, 32);
		contentPane.add(txtpnOnlineUser);
		
		JList listPanel = new JList();
		listPanel.setBackground(SystemColor.control);
		listPanel.setFont(new Font("Arial", Font.PLAIN, 14));
		listPanel.setBounds(491, 130, 174, 248);
		contentPane.add(listPanel);
		
		JScrollBar scrollBar = new JScrollBar();
		scrollBar.setBounds(648, 225, 17, 48);
		contentPane.add(scrollBar);
		
		JTextArea textArea = new JTextArea();
		textArea.setFont(new Font("Arial", Font.PLAIN, 14));
		textArea.setEditable(false);
		textArea.setBackground(SystemColor.control);
		textArea.setBounds(26, 130, 439, 250);
		contentPane.add(textArea);
		
		JScrollBar scrollBar_1 = new JScrollBar();
		scrollBar_1.setBounds(388, 225, 17, 48);
		contentPane.add(scrollBar_1);
		
		textField = new JTextField();
		
		textField.setBackground(SystemColor.info);
		textField.setBounds(160, 407, 354, 32);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JTextPane txtpnMessage = new JTextPane();
		txtpnMessage.setBackground(new Color(255, 222, 173));
		txtpnMessage.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		txtpnMessage.setText("Message :");
		txtpnMessage.setBounds(72, 407, 87, 32);
		contentPane.add(txtpnMessage);
		
		JTextPane txtpnConversation = new JTextPane();
		txtpnConversation.setBackground(new Color(255, 222, 173));
		txtpnConversation.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		txtpnConversation.setText("Conversations ");
		txtpnConversation.setBounds(26, 90, 117, 32);
		contentPane.add(txtpnConversation);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(32, 178, 170));
		panel.setBorder(new LineBorder(new Color(0, 0, 205), 3));
		panel.setBounds(0, 0, 700, 74);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JTextPane txtpnChatRoom = new JTextPane();
		txtpnChatRoom.setBackground(new Color(32, 178, 170));
		txtpnChatRoom.setFont(new Font("Calibri", Font.BOLD, 35));
		txtpnChatRoom.setText("CHAT ROOM");
		txtpnChatRoom.setBounds(270, 10, 193, 41);
		panel.add(txtpnChatRoom);
		
		JPanel panelclient = new JPanel();
		panelclient.setBackground(new Color(32, 178, 170));
		panelclient.setBounds(180, 5, 60, 60);
		
		panel.add(panelclient);
		panelclient.setLayout(null);
		
		JLabel lblclient = new JLabel("");
		lblclient.setHorizontalAlignment(SwingConstants.CENTER);
		lblclient.setBounds(0, 0, 60, 55);
		lblclient.setIcon(new ImageIcon(img_client));
		panelclient.add(lblclient);
		
		JPanel panelX = new JPanel();
		panelX.setBackground(new Color(32, 178, 170));
		panelX.setBounds(669, 10, 25, 25);
		panel.add(panelX);
		
		JLabel lblX = new JLabel("X");
		lblX.setHorizontalAlignment(SwingConstants.CENTER);
		lblX.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		lblX.setForeground(Color.BLACK);
		lblX.setBounds(1070, 2, 34, 16);
		lblX.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				if(JOptionPane.showConfirmDialog(null, "Are you sure you want to close this application?", "Confirmation", JOptionPane.YES_NO_OPTION) == 0) {
					
					ClientGUI.this.dispose();
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
		 
		panelX.add(lblX);
		
		JButton btnlogout = new JButton("Logout");
		/**btnlogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(JOptionPane.showConfirmDialog(null, "Do you want to logout?", "Confirmation", JOptionPane.YES_NO_OPTION) == 0) {
					login log = new login();
					log.setVisible(true);
					ClientGUI.this.dispose();
				}
			}
		});*/
		btnlogout.setBackground(new Color(255, 215, 0));
		btnlogout.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
		btnlogout.setBounds(577, 44, 85, 21);
		panel.add(btnlogout);
		
		JButton btnSend = new JButton("Send");
		btnSend.setBackground(new Color(102, 255, 0));
		btnSend.setFont(new Font("Arial", Font.BOLD, 15));
		btnSend.setBounds(540, 406, 125, 32);
		contentPane.add(btnSend);
		
		JPanel panelConveration = new JPanel();
		panelConveration.setBackground(new Color(255, 222, 173));
		panelConveration.setBounds(142, 80, 50, 50);
		contentPane.add(panelConveration);
		panelConveration.setLayout(null);
		
		JLabel lblconversation = new JLabel("");
		lblconversation.setBackground(new Color(255, 222, 173));
		lblconversation.setBounds(-3, 0, 50, 50);
		lblconversation.setHorizontalAlignment(SwingConstants.CENTER);
		lblconversation.setIcon(new ImageIcon(img_conversation));
		panelConveration.add(lblconversation);
		
		JPanel panelonlinepeople = new JPanel();
		panelonlinepeople.setBackground(new Color(255, 222, 173));
		panelonlinepeople.setBounds(602, 80, 50, 50);
		contentPane.add(panelonlinepeople);
		
		JLabel lblonlinepeople = new JLabel("");
		lblonlinepeople.setBackground(new Color(255, 222, 173));
		lblonlinepeople.setHorizontalAlignment(SwingConstants.CENTER);
		lblonlinepeople.setIcon(new ImageIcon(img_onlinepeople));
		panelonlinepeople.add(lblonlinepeople);
		
		JPanel panelmessage = new JPanel();
		panelmessage.setBackground(new Color(255, 222, 173));
		panelmessage.setBounds(20, 400, 50, 50);
		contentPane.add(panelmessage);
		
		JLabel lblmessage = new JLabel("");
		lblmessage.setBackground(new Color(255, 182, 193));
		lblmessage.setHorizontalAlignment(SwingConstants.CENTER);
		lblmessage.setIcon(new ImageIcon(img_message));
		panelmessage.add(lblmessage);
		
		
	}

}





