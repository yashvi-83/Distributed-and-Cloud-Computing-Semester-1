package client;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;


public interface InterfaceClient extends Remote{
    //this function to retrieve the messages of the discussion from the server
    void retrieveMessage(int i, String message) throws RemoteException;
    
   
    
    //this function to send a message from a client to the server
    void sendMessage(List<String> list) throws RemoteException;
    
    //this function to retrieve the name of connected clients (client identifier) â€‹â€‹==> username
    String getName()throws RemoteException;
    
    //this function to deactivate the functionality of sending a message to a customer
    void closeChat(String message) throws RemoteException;
    
    //this function to enable a customer to send a message
    void openChat() throws RemoteException;
}
