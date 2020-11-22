package server;

import java.rmi.Remote;
import java.rmi.RemoteException;
import client.InterfaceClient;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;


public interface InterfaceServer extends Remote{
    //Function to broadcast a message to all connected clients
    void broadcastMessage(String message,List<String> list) throws RemoteException;
    
    //Function to broadcast a file to all connected clients
    void broadcastMessage(ArrayList<Integer> inc,List<String> list,String filename) throws RemoteException;
    
    //Function to get list of connected clients
    Vector<String> getListClientByName(String name) throws RemoteException;
    
    //Function to add new connected client to the list of connected clients
    void addClient(InterfaceClient client) throws RemoteException;
    
    //Function to block a user from sending message but can receive other messages
    void blockClient(List<String> clients) throws RemoteException;
    
    //Function to remove list of connected clients
    void removeClient(List<String> clients) throws RemoteException;
    
    //Function to remove one client
    void removeClient(String clients) throws RemoteException;
    
    //Function to activate a client after being "blocked"
    void reactiveClient(List<String> clients) throws RemoteException;
    
    //Function to check if a username already exists on the server
    boolean checkUsername(String username) throws RemoteException;
}
