package main.java;

import main.java.Enum.Detail;
import main.java.Exception.ChambreNonDisponibleException;
import main.java.Exception.DateInvalideException;
import main.java.Exception.NotFoundException;
import main.java.Interface.DisplayManagerInterface;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

public class DisplayManager implements DisplayManagerInterface {

    public void menu(Hotel hotel) throws ChambreNonDisponibleException, DateInvalideException {
        Scanner scanner = new Scanner(System.in);
        Client client = null;
        while (true){
            System.out.println("1 - Reserver une chambre");
            System.out.println("2 - S'enregistrer");
            System.out.println("3 - Modifier une reservation");
            System.out.println("4 - Commander un repas");
            System.out.println("5 - Afficher les details des chambres");
            System.out.println("6 - Obtenir la facture");
            System.out.println("0 - Quitter");


            System.out.println("Choisissez une option");
            int choice = scanner.nextInt();

            switch (choice){
                case 1:
                    if (Objects.isNull(client)) {
                        System.out.println("Merci de vous enregistrer");
                    } else {
                        option1(scanner, hotel, client);
                    }
                    break;
                case 2:
                    client = option2(scanner,hotel);
                    break;
                case 3:
                    option3(hotel, client,scanner);
                    break;
                case 4:
                    break;
                case 5:
                    option5(hotel);
                    break;
                case 0:
                    System.out.println("Enregistrement de l'hotel");
                    hotel.saveHotel();
                    System.out.println("Etat de l'hotel enregistrer, Au revoir !");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Option invalide veuillez réessayer ");
            }
        }
    }

    public void option1(Scanner scanner, Hotel hotel, Client client) throws ChambreNonDisponibleException, DateInvalideException {
        System.out.println("Très bien quelle type de chambre souhaiter vous reservé ?");
        System.out.println("1-" + Detail.valueOf(Detail.CHAMBRESIMPLE.toString()));
        System.out.println("2-" + Detail.valueOf(Detail.CHAMBREDOUBLE.toString()));
        System.out.println("3-" + Detail.valueOf(Detail.CHAMBRELUXESIMPLE.toString()));
        System.out.println("4-" + Detail.valueOf(Detail.CHAMBRELUXEDOUBLE.toString()));
        int choice = scanner.nextInt();
        Detail type = null;
        switch (choice){
            case 1:
                type = Detail.CHAMBRESIMPLE;
                break;
            case 2:
                type = Detail.CHAMBREDOUBLE;
                break;
            case 3:
                type = Detail.CHAMBRELUXESIMPLE;
                break;
            case 4:
                type = Detail.CHAMBRELUXEDOUBLE;
                break;
            default:
                System.out.println("Choix invalide");
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date dateDebut = null;
        Date dateFin = null;
        while (Objects.isNull(dateDebut) || Objects.isNull(dateFin)) {
            System.out.println("A quelle date souhaiter commencer la reservation ? (DD/MM/YYYY");
            String dateD = scanner.next();
            System.out.println("A quelle date souhaiter terminer la reservation ? (DD/MM/YYYY");
            String dateF = scanner.next();
            try {
                dateDebut = dateFormat.parse(dateD);
                dateFin = dateFormat.parse(dateF);
            } catch (ParseException e) {
                System.out.println("Erreur : "+e.getMessage());
            }
        }
        System.out.println("Très bien je verifie si j'ai ce type de chambre disponible à ces dates");
        hotel.getReservationManager().effectuerReservation(client,type,dateDebut,dateFin);
    }

    public Client option2(Scanner scanner, Hotel hotel){
        String nom,prenom,email,telephone, rep;
        System.out.println("etes vous deja client ? (oui/non)");
        rep = scanner.next();
        if(rep.equals("oui")){
            System.out.println("Merci de renseigner votre nom");
            rep = scanner.next();
            Optional<Client> clientFind = hotel.getClientManager().findClient(rep);
            if(clientFind.isEmpty()){
                System.out.println("Désoler je ne vous est pas trouvé, Veuillez vous enregistrer");
            }else{
                System.out.println("parfait je vous est trouvé, Bienvenue");
                return clientFind.get();
            }
        }
        System.out.println("Remplissez votre nom");
        nom = scanner.next();
        System.out.println("Remplissez votre prénom");
        prenom = scanner.next();
        System.out.println("Remplissez votre email");
        email = scanner.next();
        System.out.println("Remplissez votre numéro de téléphone");
        telephone = scanner.next();
        Client newClient = new Client(nom,prenom,email,telephone);
        hotel.getClientManager().addClient(newClient);
        System.out.println("Vous êtes maintenant client de l'hotel !");
        return newClient;
    }

    public void option3(Hotel hotel, Client client,Scanner scanner){
        System.out.println("Voici toutes vos reservation la quelle souhaitez vous modifier ? (Merci de saisir sont id)");
        hotel.getReservationManager().listReservationByClient(client).forEach(reservation -> System.out.println(reservation.toString()));
        int id = scanner.nextInt();
        Optional<Reservation> reservationToModify = hotel.getReservationManager().listReservationByClient(client).stream()
                        .filter(reservation -> reservation.getId() == id).findFirst();
        try {
            if (reservationToModify.isEmpty()) {
                throw new NotFoundException();
            } else {
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                Date dateDebut = null;
                Date dateFin = null;
                while (Objects.isNull(dateDebut) || Objects.isNull(dateFin)) {
                    System.out.println("Merci de saisir la nouvelles date de début");
                    String dateD = scanner.next();
                    System.out.println("Merci de saisir la nouvelles date de fin");
                    String dateF = scanner.next();
                    try {
                        dateDebut = dateFormat.parse(dateD);
                        dateFin = dateFormat.parse(dateF);
                        if (dateFin.before(dateDebut) || dateDebut.after(dateFin)) {
                            throw new DateInvalideException();
                        }
                        reservationToModify.get().setDateDebut(dateDebut);
                        reservationToModify.get().setDateFin(dateFin);
                        System.out.println("Changement effectuer avec succès");
                    } catch (ParseException | DateInvalideException e) {
                        System.out.println("Erreur : " + e.getMessage());
                    }
                }

            }
        }catch (NotFoundException e){
            System.out.println("Erreur : " + e.getMessage());
        }

    }

    public void option5(Hotel hotel){
        System.out.println("Notre hotel compte 4 types de chambre : " + Arrays.toString(Detail.values()));
        System.out.println("Voici les details pour chaque type de chambre : \n" );
        System.out.println(hotel.getReservationManager().getDetailForAllChambre());
    }
}
