package main.java.Interface;

import main.java.Client;

import java.util.List;
import java.util.Optional;

public interface ClientManagerInterface {
    List<Client> getListClient();
    void addClient(Client client);

    Optional<Client> findClient(String nom);
}
