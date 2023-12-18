package main.java;

import main.java.Enum.Detail;
public abstract class Chambre {
    private int numero;
    private boolean disponible;
    private Detail detail;


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

    public String getType(){
        return this.detail.getType();
    }

    public int getPrix(){
        return this.detail.getValeur();
    }

    public String getLit(){
        return this.detail.getLit();
    }

    public  String getDetail(){
        return "main.java.Chambre : " + getType() + "\n" +
                "Lit : " + getLit() + "\n" +
                "Prix : " + getPrix() + "\n";
    }

    public abstract String toString();

}
