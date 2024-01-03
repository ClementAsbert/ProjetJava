package main.java;

import main.java.Enum.Detail;
public class ChambreSimple extends Chambre{

    public ChambreSimple(int numero) {
        super(numero, true, Detail.CHAMBRESIMPLE);
    }
    @Override
    public String toString() {
        return "Chambre simple : \n"
                + "Numéro : " + this.getNumero() + "\n"
                + "Prix : " + this.getDetail().getValeur() + "\n"
                + "Type : " + this.getDetail().getType() + "\n"
                + "Lit : " + this.getDetail().getLit() + "\n";
    }
}
