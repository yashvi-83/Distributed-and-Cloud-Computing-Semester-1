package server;

import java.rmi.Remote;
import java.rmi.RemoteException;
import client.InterfaceClient;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;


public interface InterfaceServer extends Remote{    
    //this function to distribute a message to all connected clients
    void broadcastMessage(String message,List<String> list) throws RemoteException;
    
    //this function to distribute a shared file to all connected clients
    void broadcastMessage(ArrayList<Integer> inc,List<String> list,String filename) throws RemoteException;
    
    //this function to retrieve the name of the connected clients
    Vector<String> getListClientByName(String name) throws RemoteException;
    
    //this function to add a connected client to the list of connected clients
    void addClient(InterfaceClient client) throws RemoteException;
    
    //this function to block a client from sending a message, but he can receive messages
    void blockClient(List<String> clients) throws RemoteException;
    
    //this function to completely delete a list of chat clients (kick-out)
    void removeClient(List<String> clients) throws RemoteException;
    
    //this function to completely remove a single chat client (kick-out)
    void removeClient(String clients) throws RemoteException;
    
    //this function to activate a client in chat, after being in the case of "block"
    void reactiveClient(List<String> clients) throws RemoteException;
    
    //this function to verify is whether a username already exists in the server or not, because username is the identifier on chat
    boolean checkUsername(String username) throws RemoteException;
}