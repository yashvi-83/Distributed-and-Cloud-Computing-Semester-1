package client;

import server.InterfaceServer;
import java.awt.Toolkit;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.awt.Color;
import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;

import java.awt.SystemColor;


public class LoginView extends javax.swing.JFrame {
	
	private Image img_logo = new ImageIcon(LoginView.class.getResource("image/login.png")).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);	
	
	
    private InterfaceServer server;
    
    public LoginView() {
    	getContentPane().setFont(new Font("Comic Sans MS", Font.PLAIN, 10));
    	getContentPane().setBackground(SystemColor.activeCaption);
        initComponents();
        this.setLocationRelativeTo(null);
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("login.png")));

        try {
            server = (InterfaceServer) Naming.lookup("rmi://192.168.100.98:4321/remote");
        } catch (NotBoundException | MalformedURLException | RemoteException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
   
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jButton1.setBackground(new Color(124, 252, 0));
        jLabel1 = new javax.swing.JLabel();
        jLabel1.setHorizontalAlignment(SwingConstants.CENTER);
        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Log in");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jButton1.setFont(new Font("Comic Sans MS", Font.BOLD, 15)); 
        jButton1.setText("Login");
        jButton1.setToolTipText("");
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
        jLabel1.setText("Username :");

        JPanel panel = new JPanel();
        panel.setForeground(new Color(255, 255, 255));
        panel.setBorder(new LineBorder(new Color(0, 0, 255), 3));
        panel.setBackground(new Color(30, 144, 255));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        layout.setHorizontalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(layout.createSequentialGroup()
        			.addGroup(layout.createParallelGroup(Alignment.LEADING)
        				.addGroup(layout.createSequentialGroup()
        					.addContainerGap()
        					.addGroup(layout.createParallelGroup(Alignment.TRAILING)
        						.addComponent(jLabel2)
        						.addComponent(jLabel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addComponent(jTextField1, 116, 116, 116)
        					.addPreferredGap(ComponentPlacement.RELATED))
        				.addGroup(layout.createSequentialGroup()
        					.addGap(124)
        					.addComponent(jButton1, GroupLayout.PREFERRED_SIZE, 92, GroupLayout.PREFERRED_SIZE)))
        			.addGap(70))
        		.addComponent(panel, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 320, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(layout.createSequentialGroup()
        			.addComponent(panel, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
        			.addGap(23)
        			.addGroup(layout.createParallelGroup(Alignment.TRAILING)
        				.addComponent(jLabel2)
        				.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        					.addComponent(jLabel1)
        					.addComponent(jTextField1, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)))
        			.addPreferredGap(ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
        			.addComponent(jButton1)
        			.addGap(20))
        );
        panel.setLayout(null);
        
        JLabel lblNewLabel = new JLabel("Login");
        lblNewLabel.setForeground(new Color(0, 0, 0));
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
        lblNewLabel.setBounds(165, 8, 46, 20);
        panel.add(lblNewLabel);
        
        panelicon = new JPanel();
        panelicon.setBackground(new Color(30, 144, 255));
        panelicon.setBounds(135, 4, 30, 30);
        panel.add(panelicon);
        panelicon.setLayout(null);
        
        lbliconimage = new JLabel("");
        lbliconimage.setBounds(0, 0, 30, 30);
        lbliconimage.setIcon(new ImageIcon(img_logo));
        panelicon.add(lbliconimage);
        getContentPane().setLayout(layout);

        pack();
        setLocationRelativeTo(null);
    }

    
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            if(!server.checkUsername(jTextField1.getText())){
                if(!jTextField1.getText().equals("") && !jTextField1.getText().contains(" ")){
                    new ChatView(jTextField1.getText(),server).setVisible(true);
                    this.dispose();
                }
            }else{
                JOptionPane.showMessageDialog(new JFrame(),"this username has been taken","Alert",JOptionPane.WARNING_MESSAGE);
            }
        } catch (RemoteException ex) {
            JOptionPane.showMessageDialog(this,"Error!! a remoteException occurred in the server. \n\ntry to: \n- Restart the server \n- Change the port","Alert",JOptionPane.WARNING_MESSAGE);
            System.out.println("Error: " + ex.getMessage());
        }
    }

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {

    }

    
    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LoginView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        
        java.awt.EventQueue.invokeLater(() -> {
            new LoginView().setVisible(true);
        });
    }

   
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JTextField jTextField1;
    private JPanel panelicon;
    private JLabel lbliconimage;
}
