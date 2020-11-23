package server;

import java.rmi.RemoteException;


import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import client.InterfaceClient;
import java.util.List;
import java.util.Vector;


public class ChatServer extends UnicastRemoteObject implements InterfaceServer{
    private final ArrayList<InterfaceClient> clients; //list of connected clients 
    
    
    //constructor
    public ChatServer() throws RemoteException{
        super();
        this.clients = new ArrayList<>();
       
    }
    
    //Function to broadcast message to all connected clients or direct message to particular client
    @Override
    public synchronized void broadcastMessage(String message,List<String> list) throws RemoteException {
        if(list.isEmpty()){ //all clients
            int i= 0;
            while (i < clients.size()){
                clients.get(i++).retrieveMessage(1,message);
            }
        }else{  //particular client
            for (InterfaceClient client : clients) {
                for(int i=0;i<list.size();i++){
                    if(client.getName().equals(list.get(i))){
                        client.retrieveMessage(2,message);
                    }
                }
            }
        }
    }
    
 
    //Function to add new client to list of clients on server
    @Override
    public synchronized void addClient(InterfaceClient client) throws RemoteException {
        this.clients.add(client);
    }
    
    //Function to get connected clients' names
    @Override
    public synchronized Vector<String> getListClientByName(String name) throws RemoteException {
        Vector<String> list = new Vector<>();
        for (InterfaceClient client : clients) {
            if(!client.getName().equals(name)){
                list.add(client.getName());
            }
        }
        return list;
    }
    

    //Function to check if a username already exists on the server
    @Override
    public boolean checkUsername(String username) throws RemoteException {
        boolean exist = false;
        for(int i=0;i<clients.size();i++){
            if(clients.get(i).getName().equals(username)){
                exist = true;
            }
        }
        return exist;
    }
}
