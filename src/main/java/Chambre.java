package main.java;

import main.java.Enum.Detail;

import java.io.Serializable;

public abstract class Chambre implements Serializable {
    private static final long serialVersionUID = 3542636389129079000L;
    private int numero;
    private final Detail detail;

    public Chambre(int numero, Detail detail) {
        this.numero = numero;
        this.detail = detail;
    }
    public int getNumero() {
        return numero;
    }

    public  Detail getDetail(){
        return this.detail;
    }


    public abstract String toString();

}
