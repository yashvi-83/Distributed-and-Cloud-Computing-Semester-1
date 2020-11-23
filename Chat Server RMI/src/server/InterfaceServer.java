package server;

import java.rmi.Remote;
import java.rmi.RemoteException;
import client.InterfaceClient;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;


public interface InterfaceServer extends Remote{    
    //distributes a message to all connected clients
    void broadcastMessage(String message,List<String> list) throws RemoteException;
    
    //distributes a shared file to all connected clients
//    void broadcastMessage(ArrayList<Integer> inc,List<String> list,String filename) throws RemoteException;
    
    // retrieves the name of the connected clients
    Vector<String> getListClientByName(String name) throws RemoteException;
    
    //this function to adds a connected client to the list of connected clients
    void addClient(InterfaceClient client) throws RemoteException;
    
    
    //verifies is whether a username already exists in the server or not, because username is the identifier on chat
    boolean checkUsername(String username) throws RemoteException;
}
