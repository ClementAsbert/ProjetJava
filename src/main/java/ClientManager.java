package main.java;

import main.java.Interface.ClientManagerInterface;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ClientManager implements Serializable, ClientManagerInterface {

    private static final long serialVersionUID = 4750886407036939414L;
    private final List<Client> listClient;

    public ClientManager() {
        this.listClient = new ArrayList<>();;
    }

    public List<Client> getListClient() {
        return listClient;
    }

    public void addClient(Client client){
        this.listClient.add(client);
    }

    public Optional<Client> findClient(String nom){
        return this.listClient.stream()
                .filter(client -> client.getNom().equalsIgnoreCase(nom))
                .findFirst();
    }
}
