package server;

import java.rmi.Remote;
import java.rmi.RemoteException;
import client.InterfaceClient;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;


public interface InterfaceServer extends Remote{
    //cette fonction pour distribuer un message vers tous clients connect�es
    void broadcastMessage(String message,List<String> list) throws RemoteException;
    
    //cette fonction pour distribuer un fichier partag�e vers tous clients connectes
    void broadcastMessage(ArrayList<Integer> inc,List<String> list,String filename) throws RemoteException;
    
    //cette fonction pour recupere le nom des clients connectes
    Vector<String> getListClientByName(String name) throws RemoteException;
    
    //cette fonction pour ajouter un client connect�es a la liste des clients connect�es
    void addClient(InterfaceClient client) throws RemoteException;
    
    //cette fonction pour blocker un client d"envoyer un message, mais il peut recu les messages
    void blockClient(List<String> clients) throws RemoteException;
    
    //cette fonction pour supprimer totalement une liste des clients de chat (kick-out)
    void removeClient(List<String> clients) throws RemoteException;
    
    //cette fonction pour supprimer totalement un seul client de chat (kick-out)
    void removeClient(String clients) throws RemoteException;
    
    //cette fonction pour activer un client dans chat, d'apres etre dans le cas de "block"
    void reactiveClient(List<String> clients) throws RemoteException;
    
    //cette fonction pour verfifier est que un username existe deja dans le serveur ou non, car username est l'identificateur sur chat
    boolean checkUsername(String username) throws RemoteException;
}
