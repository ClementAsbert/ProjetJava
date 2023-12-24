package main.java.Exception;

public class ChambreNonDisponibleException extends Exception{

    public ChambreNonDisponibleException(){
        super("La chambre n'est pas disponible à cette période");
    }
}
