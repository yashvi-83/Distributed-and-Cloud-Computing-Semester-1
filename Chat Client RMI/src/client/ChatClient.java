package client;

import server.InterfaceServer;
import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;


public class ChatClient extends UnicastRemoteObject implements InterfaceClient{
    private final InterfaceServer server;
    private final String name;
    private final JTextArea input;
    private final JTextArea output;
    private final JPanel jpanel;
    
    
    public ChatClient(String name , InterfaceServer server,JTextArea jtext1,JTextArea jtext2,JPanel jpanel) throws RemoteException{
        this.name = name;
        this.server = server;
        this.input = jtext1;
        this.output = jtext2;
        this.jpanel = jpanel;
        server.addClient(this);
    }
    
    
    @Override
    public void retrieveMessage(int type,String message) throws RemoteException {
    	if (type ==1) {
    		output.setText(output.getText() + "\n" + message);
    	}
    	else {
    		output.setText(output.getText() + "\n" + "PM from  " + message);
    	}
        
    }
    
    
   
    
    
    @Override
    public void sendMessage(List<String> list) {
        try {
            server.broadcastMessage("["+ name + "]" + " : " + input.getText(),list);
        } catch (RemoteException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }
    
   
    @Override
    public String getName() {
        return name;
    }

    
    @Override
    public void closeChat(String message) throws RemoteException {
        input.setEditable(false);
        input.setEnabled(false);
        JOptionPane.showMessageDialog(new JFrame(),message,"Alert",JOptionPane.WARNING_MESSAGE); 
    }

 
    @Override
    public void openChat() throws RemoteException {
        input.setEditable(true);
        input.setEnabled(true);    
    }
}
