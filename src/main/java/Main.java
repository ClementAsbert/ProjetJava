package main.java;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Throwable {
        Hotel hotel = Hotel.chargerHotel();
        System.out.println(hotel.getListChambre().size());
    }
}