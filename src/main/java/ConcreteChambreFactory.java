package main.java;

import java.util.Random;

public class ConcreteChambreFactory extends ChambreFactory {

    Random random = new Random();

    @Override
    public Chambre build(int type, int num) throws Throwable{
        switch (type){
            case SIMPLE:
                return new ChambreSimple(num);
            case DOUBLE:
                return new ChambreDouble(num);
            case LUXESIMPLE:
                return new ChambreLuxeSimple(num);
            case LUXDOUBLE:
                return new ChambreLuxeDouble(num);
            default:
                throw new Throwable();
        }
    }
}
