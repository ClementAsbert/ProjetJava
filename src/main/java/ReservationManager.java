package main.java;

import main.java.Enum.Detail;
import main.java.Exception.ChambreNonDisponibleException;
import main.java.Exception.DateInvalideException;
import main.java.Exception.NotFoundException;
import main.java.Interface.ReservationManagerInterface;

import java.io.Serializable;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ReservationManager implements Serializable, ReservationManagerInterface {

    private static final long serialVersionUID = 6939002276261781623L;
    private Map<Integer,Reservation> listReservation;
    private List<Chambre> listChambre;

    public ReservationManager(){
        this.listReservation = new HashMap<>();
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
                .filter(chambre -> this.listReservation.values()
                        .stream()
                        //Regarde si la chambre est disponible dans l'intervale et que les date ne ce chevauche pas
                        .allMatch(reservation ->reservation.getChambre() == chambre && dateFin.before(reservation.getDateDebut()) || dateDebut.after(reservation.getDateFin())))
                .findFirst();
    }

    public List<Chambre> getDisponibiliteByDate(Date dateDebut, Date dateFin){
        return this.listChambre.stream()
                .filter(chambre -> this.listReservation.values()
                        .stream()
                        //Regarde si la chambre est disponible dans l'intervale et que les date ne ce chevauche pas
                        .allMatch(reservation -> dateFin.before(reservation.getDateDebut()) || dateDebut.after(reservation.getDateFin())))
                .collect(Collectors.toList());
    }
    @Override
    public void effectuerReservation(Client client, Detail type, Date dateDebut, Date dateFin) throws DateInvalideException, ChambreNonDisponibleException {
            if (dateFin.before(dateDebut) || dateDebut.after(dateFin)) {
                throw new DateInvalideException();
            }
            Optional<Chambre> chambre = this.getFirstChambreDispoByType(dateDebut, dateFin, type);
            if (chambre.isEmpty()) {
                throw new ChambreNonDisponibleException();
            } else {
                Reservation reservation = new Reservation(dateDebut, dateFin, chambre.get(), client);
                this.listReservation.put(reservation.getId(), reservation);
                System.out.println("Votre chambre sera la n°" + chambre.get().getNumero());
            }
    }
    @Override
    public void modifReservation(int id, Date newDateDebut, Date newDateFin ) throws DateInvalideException {
            if (newDateFin.before(newDateDebut) || newDateDebut.after(newDateFin)) {
                throw new DateInvalideException();
            }else{
                Optional<Reservation> reservationToModify = Optional.ofNullable(this.listReservation.get(id));

                reservationToModify.ifPresent(reservation -> {
                    reservation.setDateDebut(newDateDebut);
                    reservation.setDateFin(newDateFin);
                    System.out.println("Modification effectué avec succès");
                });
            }
    }
    @Override
    public Map<Integer,Reservation> listReservationByClient(Client client) throws NotFoundException {
        Map<Integer,Reservation> reservations = this.listReservation.values().stream()
                .filter(reservation -> reservation.getClient() == client)
                .collect(Collectors.toMap(Reservation::getId, Function.identity()));
        if(reservations.isEmpty()){
            throw new NotFoundException();
        }
        return reservations;
    }
    @Override
    public void deleteReservation(int id){
        try {
            if (!this.listReservation.containsKey(id)) {
                throw new NotFoundException();
            }else{
                this.listReservation.remove(id);
                System.out.println("Suppression effectuer avec succès");
            }
        }catch (NotFoundException e){
            System.out.println("Erreur : " + e.getMessage());
        };
    }
    @Override
    public Map<Integer,Reservation> getListReservation() {
        return listReservation;
    }
    @Override
    public List<Chambre> getListChambre() {
        return listChambre;
    }


}
