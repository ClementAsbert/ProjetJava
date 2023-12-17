import java.io.Serializable;
import java.util.Random;

public class ConcreteChambreFactory extends ChambreFactory {

    Random random = new Random();

    @Override
    public Chambre build(int type) throws Throwable{
        switch (type){
            case SIMPLE:
                return new ChambreSimple(random.nextInt());
            case DOUBLE:
                return new ChambreDouble(random.nextInt());
            case LUXESIMPLE:
                return new ChambreLuxeSimple(random.nextInt());
            case LUXDOUBLE:
                return new ChambreLuxeDouble(random.nextInt());
            default:
                throw new Throwable();
        }
    }
}
