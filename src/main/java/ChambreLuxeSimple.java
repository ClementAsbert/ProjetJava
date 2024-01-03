package main.java;

import main.java.Enum.Detail;

public class ChambreLuxeSimple extends Chambre{
    public ChambreLuxeSimple(int numero) {
        super(numero, true, Detail.CHAMBRELUXESIMPLE);
    }

    @Override
    public String toString() {
        return "Chambre luxe simple : \n"
                + "Numéro : " + this.getNumero() + "\n"
                + "Prix : " + this.getDetail().getValeur() + "\n"
                + "Type : " + this.getDetail().getType() + "\n"
                + "Lit : " + this.getDetail().getLit() + "\n";
    }
}
