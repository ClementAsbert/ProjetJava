package main.java.Interface;

import main.java.Hotel;

public interface PersistenceManagerInterface {
    Hotel chargerHotel();

    void saveHotel(Hotel hotel);
}
