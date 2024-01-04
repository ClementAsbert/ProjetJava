package main.java.Interface;

import main.java.Chambre;
import main.java.ChambreFactory;
import main.java.Client;
import main.java.Enum.Detail;
import main.java.Exception.ChambreNonDisponibleException;
import main.java.Exception.DateInvalideException;
import main.java.Reservation;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface ReservationManagerInterface {
    void creerChambreList(ChambreFactory factory) throws Throwable;
    Optional<Chambre> getFirstChambreDispoByType(Date dateDebut, Date dateFin, Detail detail);
    void effectuerReservation(Client client, Detail type, Date dateDebut, Date dateFin) throws ChambreNonDisponibleException, DateInvalideException;
    void modifReservation(int id, Date newDateDebut, Date newDateFin );
    List<Reservation> listReservationByClient(Client client);

    public void deleteReservation(int id);
    List<Reservation> getListReservation();

    List<Chambre> getListChambre();
    public String getDetailForAllChambre();
}
