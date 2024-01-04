package main.java.Exception;

public class DateInvalideException extends Exception{

    public DateInvalideException(){
        super("la date de début ne peut pas être supérieur à la date de fin ou inversement");
    }

}
