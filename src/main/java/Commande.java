package main.java;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Commande implements Serializable {
    int idCommande;
    private Client client;
    private final List<OrderItem> listRepa = new ArrayList<OrderItem>();
}
