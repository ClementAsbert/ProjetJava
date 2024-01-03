package main.java;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Commande implements Serializable {
    private static final long serialVersionUID = -9016764079869565880L;
    int idCommande;
    private Client client;
    private final List<OrderItem> listRepa = new ArrayList<OrderItem>();
}
