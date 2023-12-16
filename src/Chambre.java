import Enum.TypeLit;
public abstract class Chambre {
    int numero;
    boolean disponible;
    TypeLit typeLit;
    int prix;


    public Chambre(int numero, boolean disponible, TypeLit typeLit, int prix) {
        this.numero = numero;
        this.disponible = disponible;
        this.typeLit = typeLit;
        this.prix = prix;
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

    public abstract TypeLit getTypeLit();

    public int getPrix(){
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }
}
