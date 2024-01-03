package main.java;

public abstract class ChambreFactory {

    public static final int SIMPLE = 1;
    public static final int DOUBLE = 2;
    public static final int LUXESIMPLE = 3;
    public static final int LUXDOUBLE = 4;
    public abstract Chambre build(int type,int num) throws Throwable;
}
