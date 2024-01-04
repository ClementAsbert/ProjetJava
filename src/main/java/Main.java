package main.java;

import main.java.Enum.Detail;
import main.java.Interface.ClientManagerInterface;
import main.java.Interface.DisplayManagerInterface;
import main.java.Interface.PersistenceManagerInterface;
import main.java.Interface.ReservationManagerInterface;

import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Throwable {
        PersistenceManagerInterface persistence = new PersistenceManager();
        ReservationManagerInterface reservationManager = new ReservationManager();
        ClientManagerInterface clientManager = new ClientManager();
        ChambreFactory factory = new ConcreteChambreFactory();
        DisplayManagerInterface display = new DisplayManager();
//        Hotel hotel = new Hotel("Melton",factory,persistence,reservationManager,clientManager);
        Hotel hotel = persistence.chargerHotel();
        System.out.println("Bonjour bienvenue à l'hotel " + hotel.getNom());
        System.out.println("Quesque je peux faire pour vous ?");
        display.menu(hotel);
    }

}