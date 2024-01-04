package main.java;

import main.java.Enum.Detail;
import main.java.Exception.ChambreNonDisponibleException;
import main.java.Interface.ReservationManagerInterface;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

public class ReservationManager implements Serializable, ReservationManagerInterface {

    private static final long serialVersionUID = 6939002276261781623L;
    private List<Reservation> listReservation;
    private List<Chambre> listChambre;

    public ReservationManager(){
        this.listReservation = new ArrayList<>();
        this.listChambre = new ArrayList<>();
    }

    public String getDetailForAllChambre(){
        Set<Class<? extends Chambre>> chambreTypes = new HashSet<>();
        StringBuilder result = new StringBuilder();

        for (Chambre chambre : listChambre) {
            Class<? extends Chambre> type = chambre.getClass();

            if (!chambreTypes.contains(type)) {
                chambreTypes.add(type);
                result.append(chambre.toString()).append("\n");
            }
        }
        return result.toString();
    }
    @Override
    public void creerChambreList(ChambreFactory factory) throws Throwable {
        for(int i = 1; i<=10;i++){
            this.getListChambre().add(factory.build(ChambreFactory.SIMPLE, i));
            this.getListChambre().add(factory.build(ChambreFactory.DOUBLE, i + 10));
            this.getListChambre().add(factory.build(ChambreFactory.LUXESIMPLE, i + 20));
            this.getListChambre().add(factory.build(ChambreFactory.LUXDOUBLE, i + 30));
        }
    }
    @Override
    public Optional<Chambre> getFirstChambreDispoByType(Date dateDebut, Date dateFin, Detail detail){
        return this.listChambre.stream()
                .filter(chambre -> chambre.getDetail().equals(detail))
                .filter(chambre -> this.listReservation
                        .stream()
                        //Regarde si la chambre est disponible dans l'intervale et que les date ne ce chevauche pas
                        .allMatch(reservation ->reservation.getChambre() == chambre && dateFin.before(reservation.getDateDebut()) || dateDebut.after(reservation.getDateFin())))
                .findFirst();
    }
    @Override
    public void effectuerReservation(Client client, Detail type, Date dateDebut, Date dateFin) throws ChambreNonDisponibleException {
        Optional<Chambre> chambre = this.getFirstChambreDispoByType(dateDebut,dateFin,type);
        if(chambre.isEmpty()){
            throw new ChambreNonDisponibleException();
        }else {
            Reservation reservation = new Reservation(dateDebut,dateFin,chambre.get(),client);
            this.listReservation.add(reservation);
            System.out.println("Votre chambre sera la n°" + chambre.get().getNumero());
        }
    }
    @Override
    public void modifReservation(int id, Date newDateDebut, Date newDateFin ){
        Optional<Reservation> reservationToModify = this.listReservation.stream()
                .filter(reservation -> reservation.getId() == id )
                .findFirst();

        reservationToModify.ifPresent(reservation -> {
            reservation.setDateDebut(newDateDebut);
            reservation.setDateFin(newDateFin);
        });
    }
    @Override
    public List<Reservation> listReservationByClient(Client client){
        return this.listReservation.stream()
                .filter(reservation -> reservation.getClient() == client)
                .collect(Collectors.toList());
    }
    @Override
    public void deleteReservation(int id){
        this.listReservation.removeIf(reservation -> reservation.getId() == id);
    }
    @Override
    public List<Reservation> getListReservation() {
        return listReservation;
    }

    public void setListReservation(List<Reservation> listReservation) {
        this.listReservation = listReservation;
    }
    @Override
    public List<Chambre> getListChambre() {
        return listChambre;
    }

    public void setListChambre(List<Chambre> listChambre) {
        this.listChambre = listChambre;
    }
}
