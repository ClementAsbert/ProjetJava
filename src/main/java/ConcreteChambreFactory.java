package main.java;


public class ConcreteChambreFactory extends ChambreFactory {
    @Override
    public Chambre build(int type, int num) throws IllegalArgumentException{
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
                throw new IllegalArgumentException();
        }
    }
}
