package main.java;

import main.java.Enum.Detail;

import java.io.Serializable;

public abstract class Chambre implements Serializable {
    private static final long serialVersionUID = 3542636389129079000L;
    private int numero;
    private boolean disponible;
    private final Detail detail;

    public Chambre(int numero, boolean disponible, Detail detail) {
        this.numero = numero;
        this.disponible = disponible;
        this.detail = detail;
    }
    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public  Detail getDetail(){
        return this.detail;
    }


    public abstract String toString();

}
