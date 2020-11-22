package client;

import server.InterfaceServer;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileSystemView;



import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.ListSelectionModel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.SwingConstants;
import javax.swing.JButton;


public class ChatView extends JFrame implements Runnable{
	
	private Image img_titleimage = new ImageIcon(ChatView.class.getResource("image/chatroom.png")).getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
	private Image img_conversationimage = new ImageIcon(ChatView.class.getResource("image/conversation.png")).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
	private Image img_online = new ImageIcon(ChatView.class.getResource("image/online.png")).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
	
	
    private ChatClient client;
    private InterfaceServer server;
    private DefaultListModel<String> model = new DefaultListModel<>();
    private Vector<String> listClients;
    private String name;
    private GroupLayout groupLayout;
    
    
    public ChatView(String name,InterfaceServer server) {
    	getContentPane().setBackground(new Color(255, 222, 173));
        initComponents();
        
        this.server = server;
        this.name = name;
        
        this.setLocationRelativeTo(null);
        this.setTitle("Chat (" + name + ")");
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("chat.png")));
        
        groupLayout = new GroupLayout(jPanel1);
        jPanel1.setLayout(new GridLayout(100,1));
        jPanel1.setBorder(new EmptyBorder(5, 10, 10, 10));
    
        this.addWindowListener(new java.awt.event.WindowAdapter() {    
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                if (JOptionPane.showConfirmDialog(new JFrame(), 
                    "Are you sure you want to close this chat ?", "Close chat?", 
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
                    try {
                        server.removeClient(name);
                    } catch (RemoteException ex) {
                        System.out.println("Error: " + ex.getMessage());
                    }
                    System.exit(0);
                }else{
                   
                }
            }
        });
        
     
        inputMsg.setForeground(Color.GRAY);
        inputMsg.setText("Enter your Message ...");
        inputMsg.addFocusListener(new FocusListener() {
        @Override
         public void focusGained(FocusEvent e) {
            if (inputMsg.getText().equals("Enter your Message ...")) {
                inputMsg.setText("");
                inputMsg.setForeground(Color.BLACK);
            }
        }
        @Override
         public void focusLost(FocusEvent e) {
            if (inputMsg.getText().isEmpty()) {
                inputMsg.setForeground(Color.GRAY);
                inputMsg.setText("Enter your Message ...");
            }
        }
        });
        
       
        listClients = new Vector<>();
        listConnect.setListData(listClients);
        
        try{
            client = new ChatClient(name,server,inputMsg,listMessage,jPanel1);
        } catch (RemoteException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        
     
        Timer minuteur = new Timer();
        TimerTask tache = new TimerTask() {
            @Override
            public void run() {
                try {
                    int[] indices = listConnect.getSelectedIndices();
                    model.clear();
                    listClients = server.getListClientByName(name);
                    int i=0;
                    while(i<listClients.size()){
                        model.addElement(listClients.get(i));
                        i++;
                    }
                    listConnect.setModel(model);
                    listConnect.setSelectedIndices(indices);
                } catch (RemoteException ex) {
                    System.out.println("Error: " + ex.getMessage());
                }
            }
        };
        minuteur.schedule(tache,0,20000);
    }
    
    @SuppressWarnings("unchecked")
    
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jScrollPane1 = new javax.swing.JScrollPane();
        listConnect = new javax.swing.JList<>();
        listConnect.setBackground(new Color(220, 220, 220));
        jScrollPane2 = new javax.swing.JScrollPane();
        inputMsg = new javax.swing.JTextArea();
        btnSend = new javax.swing.JButton();
        btnSend.setBackground(new Color(0, 0, 255));
        btnSend.setIcon(new ImageIcon(ChatView.class.getResource("/client/send.PNG")));
        jScrollPane3 = new javax.swing.JScrollPane();
        listMessage = new javax.swing.JTextArea();
        listMessage.setBackground(new Color(230, 230, 250));
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton1.setBackground(new Color(124, 252, 0));
        jButton1.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
       
        jPanel1 = new javax.swing.JPanel();

        jMenuItem1.setText("Remove Users");
        jMenuItem1.setActionCommand("");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jPopupMenu1.add(jMenuItem1);

        jMenuItem2.setText("Block Users");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jPopupMenu1.add(jMenuItem2);

        jMenuItem3.setText("Reactive Users");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jPopupMenu1.add(jMenuItem3);

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setResizable(false);

        listConnect.setFont(new java.awt.Font("Dialog", 0, 14)); 
        listConnect.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        listConnect.setToolTipText("");
        jScrollPane1.setRowHeaderView(listConnect);

        inputMsg.setColumns(20);
        inputMsg.setRows(5);
        inputMsg.setToolTipText("Enter your Message ...");
        inputMsg.setMargin(new java.awt.Insets(6, 0, 0, 16));
        jScrollPane2.setViewportView(inputMsg);
        inputMsg.getAccessibleContext().setAccessibleName("Enter your Message ...");

        btnSend.setFont(new java.awt.Font("Dialog", 1, 14));
        btnSend.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSend.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSendActionPerformed(evt);
            }
        });

        listMessage.setEditable(false);
        listMessage.setColumns(20);
        listMessage.setFont(new java.awt.Font("Dialog", 1, 12));
        listMessage.setRows(5);
        listMessage.setRequestFocusEnabled(false);
        jScrollPane3.setViewportView(listMessage);

        jLabel2.setFont(new Font("Comic Sans MS", Font.BOLD, 16)); 
        jLabel2.setText("Onlline Clients :");

        jButton1.setText("Refresh");
        jButton1.setActionCommand("");
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });


        jPanel1.setFont(new java.awt.Font("Dialog", 0, 14)); 

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 113, Short.MAX_VALUE)
        );
        
        panelmain = new JPanel();
        panelmain.setBorder(new LineBorder(new Color(0, 0, 205), 2));
        panelmain.setBackground(new Color(32, 178, 170));
        
        panelcon = new JPanel();
        panelcon.setBackground(new Color(255, 222, 173));
        
        lblcon = new JLabel("Conversation");
        lblcon.setHorizontalAlignment(SwingConstants.CENTER);
        lblcon.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
        
        panelonlinepeople = new JPanel();
        panelonlinepeople.setBackground(new Color(255, 222, 173));
        
        btnLogout = new JButton("Logout");
        btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(JOptionPane.showConfirmDialog(null, "Do you want to logout?", "Confirmation", JOptionPane.YES_NO_OPTION) == 0) {
					LoginView log = new LoginView();
					log.setVisible(true);
					ChatView.this.dispose();
				}
			}
		});
        btnLogout.setBackground(new Color(255, 255, 0));
        btnLogout.setFont(new Font("Comic Sans MS", Font.BOLD, 14));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        layout.setHorizontalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(layout.createSequentialGroup()
        			.addContainerGap()
        			.addGroup(layout.createParallelGroup(Alignment.LEADING)
        				.addGroup(layout.createSequentialGroup()
        					.addGroup(layout.createParallelGroup(Alignment.LEADING)
        						.addComponent(jScrollPane3, GroupLayout.PREFERRED_SIZE, 556, GroupLayout.PREFERRED_SIZE)
        						.addGroup(layout.createSequentialGroup()
        							.addGap(157)
        							.addComponent(panelcon, GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        							.addGap(359)))
        					.addGroup(layout.createParallelGroup(Alignment.LEADING)
        						.addGroup(layout.createSequentialGroup()
        							.addPreferredGap(ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
        							.addGroup(layout.createParallelGroup(Alignment.LEADING)
        								.addGroup(layout.createSequentialGroup()
        									.addComponent(jLabel2)
        									.addPreferredGap(ComponentPlacement.RELATED)
        									.addComponent(panelonlinepeople, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE))
        								.addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 207, GroupLayout.PREFERRED_SIZE)))
        						.addGroup(layout.createSequentialGroup()
        							.addGap(63)
        							.addGroup(layout.createParallelGroup(Alignment.TRAILING, false)
        								.addComponent(btnLogout, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        								.addComponent(jButton1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE))
        							.addPreferredGap(ComponentPlacement.RELATED, 33, Short.MAX_VALUE)))
        					.addGap(35))
        				.addGroup(layout.createSequentialGroup()
        					.addComponent(jScrollPane2, GroupLayout.PREFERRED_SIZE, 512, GroupLayout.PREFERRED_SIZE)
        					.addPreferredGap(ComponentPlacement.UNRELATED)
        					.addComponent(btnSend, GroupLayout.PREFERRED_SIZE, 45, Short.MAX_VALUE)
        					.addGap(260))))
        		.addComponent(panelmain, GroupLayout.DEFAULT_SIZE, 837, Short.MAX_VALUE)
        		.addGroup(layout.createSequentialGroup()
        			.addGap(8)
        			.addComponent(lblcon, GroupLayout.PREFERRED_SIZE, 168, GroupLayout.PREFERRED_SIZE)
        			.addContainerGap(661, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
        	layout.createParallelGroup(Alignment.TRAILING)
        		.addGroup(layout.createSequentialGroup()
        			.addComponent(panelmain, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
        			.addGap(8)
        			.addGroup(layout.createParallelGroup(Alignment.LEADING)
        				.addGroup(layout.createSequentialGroup()
        					.addGroup(layout.createParallelGroup(Alignment.TRAILING, false)
        						.addComponent(panelonlinepeople, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        						.addComponent(lblcon, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
        						.addComponent(jLabel2))
        					.addGap(6)
        					.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        						.addComponent(jScrollPane3, GroupLayout.PREFERRED_SIZE, 356, GroupLayout.PREFERRED_SIZE)
        						.addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 321, GroupLayout.PREFERRED_SIZE)))
        				.addGroup(layout.createSequentialGroup()
        					.addComponent(panelcon, GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
        					.addGap(334)
        					.addComponent(jButton1, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
        					.addPreferredGap(ComponentPlacement.RELATED)))
        			.addGroup(layout.createParallelGroup(Alignment.LEADING)
        				.addGroup(layout.createSequentialGroup()
        					.addGap(10)
        					.addComponent(jScrollPane2, 0, 50, Short.MAX_VALUE))
        				.addGroup(layout.createSequentialGroup()
        					.addGap(14)
        					.addComponent(btnSend, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
        				.addGroup(layout.createSequentialGroup()
        					.addGap(18)
        					.addComponent(btnLogout, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)))
        			.addContainerGap())
        );
        panelonlinepeople.setLayout(null);
        
        lblonline = new JLabel("");
        lblonline.setHorizontalAlignment(SwingConstants.CENTER);
        lblonline.setBounds(0, 5, 30, 30);
        lblonline.setIcon(new ImageIcon(img_online));
        panelonlinepeople.add(lblonline);
        panelcon.setLayout(null);
        
        lblconversationimage = new JLabel("");
        lblconversationimage.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
        lblconversationimage.setHorizontalAlignment(SwingConstants.CENTER);
        lblconversationimage.setBounds(1, 0, 30, 30);
        lblconversationimage.setIcon(new ImageIcon(img_conversationimage));
        panelcon.add(lblconversationimage);
        panelmain.setLayout(null);
        
        panel = new JPanel();
        panel.setBackground(new Color(32, 178, 170));
        panel.setBounds(315, 4, 45, 45);
        panelmain.add(panel);
        panel.setLayout(null);
        
        lbltitleimage = new JLabel("");
        lbltitleimage.setHorizontalAlignment(SwingConstants.CENTER);
        lbltitleimage.setBounds(0, 0, 45, 45);
        lbltitleimage.setIcon(new ImageIcon(img_titleimage));
        panel.add(lbltitleimage);
        
        lblchatroom = new JLabel("Chat Room");
        lblchatroom.setHorizontalAlignment(SwingConstants.CENTER);
        lblchatroom.setFont(new Font("Comic Sans MS", Font.BOLD, 30));
        lblchatroom.setBounds(360, 10, 186, 39);
        panelmain.add(lblchatroom);
        getContentPane().setLayout(layout);

        pack();
        setLocationRelativeTo(null);
    }

    
    private void btnSendActionPerformed(java.awt.event.ActionEvent evt) {
        if(!inputMsg.getText().equals("")){
            if(!inputMsg.getText().equals("Enter you Message ...")){
                client.sendMessage(listConnect.getSelectedValuesList());
                inputMsg.setText("");
            }else{
            JOptionPane.showMessageDialog(this,"Please insert something to set your message","Alert",JOptionPane.WARNING_MESSAGE);
        }
        }else{
            JOptionPane.showMessageDialog(this,"Please insert something to send your message","Alert",JOptionPane.WARNING_MESSAGE);
        }
    }

   
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
        Thread thread = new Thread(this);
        thread.start();
    }
    
    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            server.removeClient(listConnect.getSelectedValuesList());
        } catch (RemoteException ex) {
            System.out.println("Error: " + ex.getMessage());
        } 
    }
    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            server.blockClient(listConnect.getSelectedValuesList());
        } catch (RemoteException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }

    
    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            server.reactiveClient(listConnect.getSelectedValuesList());
        } catch (RemoteException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }
    private javax.swing.JButton btnSend;
    private javax.swing.JTextArea inputMsg;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    //private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JList<String> listConnect;
    private javax.swing.JTextArea listMessage;
    private JPanel panelmain;
    private JPanel panel;
    private JLabel lbltitleimage;
    private JLabel lblchatroom;
    private JPanel panelcon;
    private JLabel lblconversationimage;
    private JLabel lblcon;
    private JPanel panelonlinepeople;
    private JLabel lblonline;
    private JButton btnLogout;


   
    @Override
    public void run() {
        try {
            model.clear();
            listClients = server.getListClientByName(name);
            int i=0;
            while(i<listClients.size()){
                model.addElement(listClients.get(i));
                i++;
            }
            listConnect.setModel(model);
        } catch (RemoteException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }
}
