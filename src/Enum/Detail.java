package Enum;

public enum Detail {
    CHAMBRESIMPLE(50,"Normale", "Simple"),
    CHAMBREDOUBLE(70,"Normale","Double"),
    CHAMBRELUXESIMPLE(150,"Luxe","Simple"),
    CHAMBRELUXEDOUBLE(170,"Luxe","Simple");

    private final int valeur;
    private final String type;
    private final String lit;

    private Detail(int valeur, String type, String lit){
        this.valeur = valeur;
        this.type = type;
        this.lit = lit;
    }

    public int getValeur(){
        return this.valeur;
    }

    public String getType(){
        return this.type;
    }

    public String getLit(){
        return this.lit;
    }


}
