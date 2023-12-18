package main.java;

import main.java.Enum.Detail;

public class ChambreLuxeSimple extends Chambre{
    public ChambreLuxeSimple(int numero) {
        super(numero, true, Detail.CHAMBRELUXESIMPLE);
    }

    @Override
    public String toString() {
        return null;
    }
}
