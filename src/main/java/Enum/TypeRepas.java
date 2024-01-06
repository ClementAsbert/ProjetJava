package main.java.Enum;

public enum TypeRepas {
    PETIT_DEJEUNER("Petit dejeuner",10),
    DEJEUNER("Dejeuner",50),
    DINER("Diner",40);

    private final String repas;

    private final int prix;

    TypeRepas(String repas, int prix) {
        this.repas = repas;
        this.prix = prix;
    }

    public String getRepas() {
        return repas;
    }

    public int getPrix(){
        return prix;
    }
}