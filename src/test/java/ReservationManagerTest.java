package test.java;

import main.java.*;
import main.java.Enum.Detail;
import main.java.Exception.DateInvalideException;
import main.java.Exception.NotFoundException;
import main.java.Interface.ReservationManagerInterface;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class ReservationManagerTest {

    private ReservationManagerInterface reservationManagerInterface;

    private ChambreFactory factory;

    private Client client;

    @Before
    public void setUp() throws Throwable {
        factory = new ConcreteChambreFactory();
        reservationManagerInterface = new ReservationManager();
        client = new Client("clem","clem","","");
    }

    @Test
    public void getDetailForAllChambre() {

    }

    @Test
    public void creerChambreList() throws Throwable {
        reservationManagerInterface.creerChambreList(factory);
        Assert.assertEquals(reservationManagerInterface.getListChambre().size(), 40);
    }

    @Test
    public void getFirstChambreDispoByType() throws Throwable {
        reservationManagerInterface.creerChambreList(factory);
        Date dateDebut = new Date("01/01/2024");
        Date dateFin = new Date("07/01/2024");
        Optional<Chambre> chambre = this.reservationManagerInterface.getFirstChambreDispoByType(dateDebut,dateFin, Detail.CHAMBRESIMPLE);
        Assert.assertEquals(chambre.get().getDetail(),Detail.CHAMBRESIMPLE);
    }

    @Test
    public void effectuerReservation() throws Throwable {
        reservationManagerInterface.creerChambreList(factory);
        Date dateDebut = new Date("01/01/2024");
        Date dateFin = new Date("07/01/2024");
        this.reservationManagerInterface.effectuerReservation(client,Detail.CHAMBRESIMPLE,dateDebut,dateFin);
        Assert.assertEquals(this.reservationManagerInterface.listReservationByClient(client).size(), 1);
    }

    @Test
    public void modifReservation() throws DateInvalideException {
        Date dateDebut = new Date("01/01/2024");
        Date dateFin = new Date("07/01/2024");
        Date newdateDebut = new Date("12/01/2024");
        Date newdateFin = new Date("22/01/2024");
        Chambre chambre = new ConcreteChambreFactory().build(1,11);
        Reservation reservation = new Reservation(dateDebut,dateFin,chambre,client);
        this.reservationManagerInterface.getListReservation().put(reservation.getId(), reservation);
        Assert.assertEquals(this.reservationManagerInterface.getListReservation().size(), 1);
        this.reservationManagerInterface.modifReservation(reservation.getId(),newdateDebut,newdateFin);
        Assert.assertEquals(newdateDebut,this.reservationManagerInterface.getListReservation().get(reservation.getId()).getDateDebut());
        Assert.assertEquals(newdateFin,this.reservationManagerInterface.getListReservation().get(reservation.getId()).getDateFin());
    }

    @Test
    public void listReservationByClient() throws NotFoundException {
        Date dateDebut = new Date("01/01/2024");
        Date dateFin = new Date("07/01/2024");
        Chambre chambre = new ConcreteChambreFactory().build(1,11);
        Reservation reservation = new Reservation(dateDebut,dateFin,chambre,client);
        this.reservationManagerInterface.getListReservation().put(reservation.getId(),reservation);
        Assert.assertEquals(1,this.reservationManagerInterface.listReservationByClient(client).size());
        Assert.assertEquals(client,this.reservationManagerInterface.listReservationByClient(client).get(reservation.getId()).getClient());
    }

    @Test
    public void deleteReservation() {
        Date dateDebut = new Date("01/01/2024");
        Date dateFin = new Date("07/01/2024");
        Chambre chambre = new ConcreteChambreFactory().build(1,11);
        Reservation reservation = new Reservation(dateDebut,dateFin,chambre,client);
        this.reservationManagerInterface.getListReservation().put(reservation.getId(),reservation);
        Assert.assertEquals(1,this.reservationManagerInterface.getListReservation().size());
        this.reservationManagerInterface.deleteReservation(reservation.getId());
        Assert.assertEquals(0,this.reservationManagerInterface.getListReservation().size());
    }

    @Test
    public void getListReservation() {
        Assert.assertEquals(0,this.reservationManagerInterface.getListReservation().size());
    }

    @Test
    public void getListChambre() {
        Assert.assertEquals(0,this.reservationManagerInterface.getListChambre().size());
    }

    @Test
    public void getDisponibiliteByDate() throws Throwable {
        reservationManagerInterface.creerChambreList(factory);
        Date dateDebut = new Date("01/01/2024");
        Date dateFin = new Date("07/01/2024");
        Assert.assertEquals(40, this.reservationManagerInterface.getDisponibiliteByDate(dateDebut,dateFin).size());
    }
}