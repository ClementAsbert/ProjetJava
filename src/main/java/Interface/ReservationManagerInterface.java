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
    /**
     * Creer une liste de 40 chambres (10 de chaque type)
     * @param factory
     * @throws Throwable
     */
    void creerChambreList(ChambreFactory factory) throws Throwable;

    /**
     * Recherche la première chambre disponible en fonction de son type et des dates
     * @param dateDebut
     * @param dateFin
     * @param detail
     * @return Optional<Chambre>
     */
    Optional<Chambre> getFirstChambreDispoByType(Date dateDebut, Date dateFin, Detail detail);

    /**
     * Reserve une chambre
     * @param client
     * @param type
     * @param dateDebut
     * @param dateFin
     * @throws ChambreNonDisponibleException
     * @throws DateInvalideException
     */
    void effectuerReservation(Client client, Detail type, Date dateDebut, Date dateFin) throws ChambreNonDisponibleException, DateInvalideException;

    /**
     * Modifie une reservation
     * @param id
     * @param newDateDebut
     * @param newDateFin
     */
    void modifReservation(int id, Date newDateDebut, Date newDateFin );

    /**
     * Recupère la liste de toutes les reservation d'un client
     * @param client
     * @return List<Reservation>
     */
    List<Reservation> listReservationByClient(Client client);

    /**
     * Suprrime une reservation
     * @param id
     */
    void deleteReservation(int id,Client client);

    /**
     * retourn la liste de toutes les reservations
     * @return List<Reservation>
     */
    List<Reservation> getListReservation();

    /**
     * recupère la liste de toutes les chambres
     * @return List<Chambre>
     */
    List<Chambre> getListChambre();

    /**
     * recupère les detail pour chaque type de chambre
     * @return String
     */
    public String getDetailForAllChambre();
}
