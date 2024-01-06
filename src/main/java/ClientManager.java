package main.java;

import main.java.Interface.ClientManagerInterface;

import java.io.Serializable;
import java.util.*;

public class ClientManager implements Serializable, ClientManagerInterface {

    private static final long serialVersionUID = 4750886407036939414L;
    private final Set<Client> listClient;

    public ClientManager() {
        this.listClient = new HashSet<>();;
    }
    @Override
    public Set<Client> getListClient() {
        return listClient;
    }

    @Override
    public void addClient(Client client){
        this.listClient.add(client);
    }

    @Override
    public Optional<Client> findClient(String nom){
        return this.listClient.stream()
                .filter(client -> client.getNom().equalsIgnoreCase(nom))
                .findFirst();
    }
}
