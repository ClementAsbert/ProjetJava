import Enum.TypeLit;

public class ChambreSimple extends Chambre{

    public ChambreSimple(int numero) {
        super(numero, true, TypeLit.SIMPLE, 50);
    }

    @Override
    public TypeLit getTypeLit() {
        return TypeLit.SIMPLE;
    }

}
