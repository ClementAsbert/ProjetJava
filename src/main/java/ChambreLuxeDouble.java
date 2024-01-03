package main.java;

import main.java.Enum.Detail;
public class ChambreLuxeDouble extends Chambre{
    public ChambreLuxeDouble(int numero) {
        super(numero, true, Detail.CHAMBRELUXEDOUBLE);
    }

    @Override
    public String toString() {
        return "Chambre luxe Double : \n"
                + "Numéro : " + this.getNumero() + "\n"
                + "Prix : " + this.getDetail().getValeur() + "\n"
                + "Type : " + this.getDetail().getType() + "\n"
                + "Lit : " + this.getDetail().getLit() + "\n";
    }
}
