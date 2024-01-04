package main.java.Interface;

import main.java.Exception.ChambreNonDisponibleException;
import main.java.Hotel;

public interface DisplayManagerInterface {

    void menu(Hotel hotel) throws ChambreNonDisponibleException;
}
