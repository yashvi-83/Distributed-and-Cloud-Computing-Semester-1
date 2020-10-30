package Client;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import java.awt.Image;
import java.awt.Color;
import javax.swing.border.LineBorder;
import java.awt.SystemColor;
import javax.swing.SwingConstants;

public class Login extends JFrame {
	

	
	private static final long serialVersionUID = 1L;
	
	private Image img_logo = new ImageIcon(Login.class.getResource("Images/login.png")).getImage().getScaledInstance(40, 30, Image.SCALE_SMOOTH);	
	
	
	private JPanel contentPane;
	private JTextField txtName;
	private JTextField txtAddress;
	private JTextField txtPort;
	private JLabel lblIpAddress;
	private JLabel lblPort;
	private JLabel lblAddressDesc;
	private JLabel lblPortDesc;
	private JLabel lblLogin;
	private JPanel panelLogo;
	private JLabel lbllogo;

	public Login() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		setResizable(false);
		setTitle("Login");
		setUndecorated(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(300, 380);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new LineBorder(new Color(0, 0, 255), 3, true));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		txtName = new JTextField();
		txtName.setBounds(67, 78, 165, 28);
		contentPane.add(txtName);
		txtName.setColumns(10);

		JLabel lblName = new JLabel("Name:");
		lblName.setBounds(127, 60, 61, 16);
		lblName.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
		contentPane.add(lblName);

		txtAddress = new JTextField();
		txtAddress.setBounds(67, 150, 165, 28);
		contentPane.add(txtAddress);
		txtAddress.setColumns(10);

		lblIpAddress = new JLabel("IP Address:");
		lblIpAddress.setBounds(107, 130, 95, 16);
		lblIpAddress.setHorizontalAlignment(SwingConstants.CENTER);
		lblIpAddress.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		contentPane.add(lblIpAddress);

		txtPort = new JTextField();
		txtPort.setBounds(67, 235, 165, 28);
		txtPort.setColumns(10);
		contentPane.add(txtPort);

		lblPort = new JLabel("Port:");
		lblPort.setBounds(121, 215, 51, 16);
		lblPort.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		lblPort.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblPort);

		lblAddressDesc = new JLabel("(eg. 192.168.0.2)");
		lblAddressDesc.setBounds(96, 180, 112, 16);
		lblAddressDesc.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		contentPane.add(lblAddressDesc);

		lblPortDesc = new JLabel("(eg. 8192)");
		lblPortDesc.setBounds(120, 264, 68, 16);
		lblPortDesc.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
		contentPane.add(lblPortDesc);

		JButton btnLogin = new JButton("Login");
		btnLogin.setBounds(91, 330, 117, 29);
		btnLogin.setBackground(new Color(102, 255, 0));
		btnLogin.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!txtName.getText().isEmpty()
						&& !txtAddress.getText().isEmpty()
						&& !txtPort.getText().isEmpty()) {
					String name = txtName.getText();
					String address = txtAddress.getText();
					int port = Integer.parseInt(txtPort.getText());
					login(name, address, port);
				}
			}
		});

		txtPort.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				btnLogin.doClick();
			}
		});
		contentPane.add(btnLogin);
		
		JPanel panel = new JPanel();
		panel.setBounds(270, 5, 26, 21);
		panel.setBackground(new Color(30, 144, 255));
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblX = new JLabel("X");
		lblX.setBounds(8, 5, 10, 21);
		lblX.setHorizontalAlignment(SwingConstants.CENTER);
		lblX.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		lblX.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				if(JOptionPane.showConfirmDialog(null, "Are you sure you want to close this application?", "Confirmation", JOptionPane.YES_NO_OPTION) == 0) {
					
					Login.this.dispose();
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
		panel.add(lblX);
		
		JPanel panelTop = new JPanel();
		panelTop.setBounds(0, 0, 300, 50);
		panelTop.setBackground(new Color(30, 144, 255));
		panelTop.setBorder(new LineBorder(new Color(0, 0, 255), 3));
		contentPane.add(panelTop);
		panelTop.setLayout(null);
		
		lblLogin = new JLabel("LOGIN");
		lblLogin.setBounds(135, 10, 65, 30);
		lblLogin.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogin.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		panelTop.add(lblLogin);
		
		panelLogo = new JPanel();
		panelLogo.setBackground(new Color(30, 144, 255));
		panelLogo.setBounds(100, 10, 30, 30);
		panelTop.add(panelLogo);
		panelLogo.setLayout(null);
		
		lbllogo = new JLabel("");
		lbllogo.setBackground(new Color(30, 144, 255));
		lbllogo.setHorizontalAlignment(SwingConstants.CENTER);
		lbllogo.setBounds(0, 0, 30, 30);
		lbllogo.setIcon(new ImageIcon(img_logo));
		panelLogo.add(lbllogo);
	}

	private void login(String name, String address, int port) {
		dispose();
		new ClientWindow(name, address, port);
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
