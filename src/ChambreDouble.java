import Enum.TypeLit;
public class ChambreDouble extends Chambre{


    public ChambreDouble(int numero) {
        super(numero, true, TypeLit.DOUBLE, 70);
    }

    @Override
    public TypeLit getTypeLit() {
        return TypeLit.DOUBLE;
    }
}
