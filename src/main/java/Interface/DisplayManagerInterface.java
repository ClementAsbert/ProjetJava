package main.java.Interface;

import main.java.Exception.ChambreNonDisponibleException;
import main.java.Exception.DateInvalideException;
import main.java.Exception.NotFoundException;
import main.java.Hotel;

public interface DisplayManagerInterface {

    /**
     * Fonction principal pour l'affichage du menu
     * @param hotel
     * @throws ChambreNonDisponibleException
     * @throws DateInvalideException
     */
    void menu(Hotel hotel) throws ChambreNonDisponibleException, DateInvalideException, NotFoundException;
}
