package test.java;

import main.java.*;
import main.java.Exception.ChambreNonDisponibleException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

public class HotelTest {
    private Hotel hotel;
    private Client client;
    private Reservation reservation;

    private Chambre chambre;

    private ChambreFactory chambreFactory;

    @Before
    public void setup() throws Throwable {
        hotel = new Hotel("Mercury");
        chambreFactory = new ConcreteChambreFactory();
        chambre = chambreFactory.build(1);
        client = new Client("clem", "clem", "clem@blabla.com", "777 rue", "0605334555");

    }

    @Test
    public void addChambre() throws Throwable {
        Chambre chambre1 = chambreFactory.build(ChambreFactory.SIMPLE);
        hotel.addChambre(chambre1);
        Assert.assertEquals(hotel.getListChambre().size(), 1 );
        Assert.assertEquals(hotel.getListChambre().get(0), chambre1);
    }

    @Test
    public void addReservation() {
        reservation = new Reservation(new Date(), new Date(), chambre);
        hotel.addReservation(reservation);
        Assert.assertEquals(hotel.getListReservation().size() , 1);
        Assert.assertEquals(hotel.getListReservation().get(0), reservation);
    }

    @Test
    public void chambreDisponibleList() {
        hotel.addChambre(chambre);
        Assert.assertEquals(hotel.chambreDisponibleList().size(), 1);
        Assert.assertEquals(hotel.chambreDisponibleList().get(0), chambre);
    }

    @Test
    public void effectuerReservation() throws ChambreNonDisponibleException {
        hotel.effectuerReservation(client,chambre,new Date(), new Date());
        assertFalse(chambre.isDisponible());
        Assert.assertEquals(client.getListReservation().size(), 1);
    }

    @Test(expected = ChambreNonDisponibleException.class)
    public void effectuerReservation_ERROR() throws ChambreNonDisponibleException {
        chambre.setDisponible(false);
        hotel.effectuerReservation(client,chambre,new Date(), new Date());
    }
}