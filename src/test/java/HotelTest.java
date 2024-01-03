package test.java;

import main.java.*;
import main.java.Enum.Detail;
import main.java.Exception.ChambreNonDisponibleException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;
@Ignore
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
        chambre = chambreFactory.build(1,1);
        client = new Client("clem", "clem", "clem@blabla.com", "777 rue", "0605334555");

    }

    @Test
    public void effectuerReservation() throws ChambreNonDisponibleException {
        Date dateDebut = new Date("01/07/2024");
        Date dateFin = new Date("07/07/2024");
        hotel.effectuerReservation(client, Detail.CHAMBRESIMPLE,dateDebut,dateFin);
    }

    @Test(expected = ChambreNonDisponibleException.class)
    public void effectuerReservation_ERROR() throws ChambreNonDisponibleException {
        // On reserve toutes les chambres à une date
        for (Chambre chambre : hotel.getListChambre()) {
            Date dateDebut = new Date("01/01/2025");
            Date dateFin = new Date("07/01/2025");
            hotel.effectuerReservation(client, chambre.getDetail(), dateDebut, dateFin);
        }

        // On reserve une fois de plus au même date
        Date dateDebut = new Date("01/01/2025");
        Date dateFin = new Date("07/01/2025");
        hotel.effectuerReservation(client, Detail.CHAMBRESIMPLE, dateDebut, dateFin);

    }

//    @Test
//    public void chambreDisponibleList() {
//        Assert.assertEquals(hotel.chambreDisponibleList().size(), 40);
//    }

//    @Test
//    public void effectuerReservation() throws ChambreNonDisponibleException {
//        hotel.effectuerReservation(client,chambre,new Date(), new Date());
//        assertFalse(chambre.isDisponible());
//        Assert.assertEquals(client.getListReservation().size(), 1);
//    }

//    @Test(expected = ChambreNonDisponibleException.class)
//    public void effectuerReservation_ERROR() throws ChambreNonDisponibleException {
//        chambre.setDisponible(false);
//        hotel.effectuerReservation(client,chambre,new Date(), new Date());
//    }


}