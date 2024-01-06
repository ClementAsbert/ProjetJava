package main.java;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Commande implements Serializable {
    private static final long serialVersionUID = -9016764079869565880L;
    private List<Repas> listRepa;

    public Commande(List<Repas> listRepa) {
        this.listRepa = listRepa;
    }

    public List<Repas> getListRepa() {
        return listRepa;
    }
}