package main.java;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Commande implements Serializable {
    private static final long serialVersionUID = -9016764079869565880L;
    int idCommande;
    private Client client;
    private List<Repas> listRepa;

    public Commande(int idCommande, Client client, List<Repas> listRepa) {
        this.idCommande = idCommande;
        this.client = client;
        this.listRepa = listRepa;
    }
}
