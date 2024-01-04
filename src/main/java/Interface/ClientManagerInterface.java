package main.java.Interface;

import main.java.Client;

import java.util.List;
import java.util.Optional;

public interface ClientManagerInterface {
    List<Client> getListClient();

    /**
     * Ajoute un client dans la liste des clients
     * @param client
     */
    void addClient(Client client);

    /**
     * Cherche un client dans la liste par son nom
     * @param nom
     * @return Optional<Client>
     */
    Optional<Client> findClient(String nom);
}
