package main.java;

import main.java.Enum.Detail;
public class ChambreDouble extends Chambre{


    public ChambreDouble(int numero) {
        super(numero, true, Detail.CHAMBREDOUBLE);
    }

    @Override
    public String toString() {
        return "Chambre Double : \n"
                + "Numéro : " + this.getNumero() + "\n"
                + "Prix : " + this.getDetail().getValeur() + "\n"
                + "Type : " + this.getDetail().getType() + "\n"
                + "Lit : " + this.getDetail().getLit() + "\n";
    }
}
