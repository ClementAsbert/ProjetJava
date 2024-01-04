package main.java.Interface;

import main.java.Hotel;

public interface PersistenceManagerInterface {
    /**
     * récupère l'hotel depuis un fichier
     * @return
     */
    Hotel chargerHotel();

    /**
     * Sauvegarde l'hotel dans un fichier
     * @param hotel
     */
    void saveHotel(Hotel hotel);
}
