public abstract class ChambreFactory {

    public static final int SIMPLE = 1;
    public static final int DOUBLE = 2;
    public static final int LUXESIMPLE = 3;
    public static final int LUXDOUBLE = 4;
    protected abstract Chambre build(int type) throws Throwable;
}
