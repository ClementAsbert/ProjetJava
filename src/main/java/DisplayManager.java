package main.java;

import main.java.Enum.Detail;
import main.java.Enum.TypeRepas;
import main.java.Exception.ChambreNonDisponibleException;
import main.java.Exception.DateInvalideException;
import main.java.Exception.NotFoundException;
import main.java.Interface.DisplayManagerInterface;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class DisplayManager implements DisplayManagerInterface {

    public void menu(Hotel hotel) throws ChambreNonDisponibleException, DateInvalideException, NotFoundException {
        Scanner scanner = new Scanner(System.in);
        Client client = null;
        while (true){
            System.out.println("1 - Reserver une chambre");
            System.out.println("2 - S'enregistrer");
            System.out.println("3 - Modifier une reservation");
            System.out.println("4 - Commander un repas");
            System.out.println("5 - Afficher les details des chambres");
            System.out.println("6 - Obtenir la facture");
            System.out.println("7 - Supprimer une reservation");
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
                    if (Objects.isNull(client)) {
                        System.out.println("Merci de vous enregistrer");
                    } else {
                        option3(hotel,client,scanner);
                    }
                    break;
                case 4:
                    if (Objects.isNull(client)) {
                        System.out.println("Merci de vous enregistrer");
                    } else {
                        option4(hotel, client, scanner);
                    }
                    break;
                case 5:
                    option5(hotel);
                    break;
                case 6:
                    if (Objects.isNull(client)) {
                        System.out.println("Merci de vous enregistrer");
                    } else {
                        option6(hotel, client, scanner);
                    }
                    break;
                case 7:
                    if (Objects.isNull(client)) {
                        System.out.println("Merci de vous enregistrer");
                    } else {
                        option7(hotel, client, scanner);
                    }
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

    /**
     * gere l'affichage de la reservation de chambre
     * @param scanner
     * @param hotel
     * @param client
     * @throws ChambreNonDisponibleException
     * @throws DateInvalideException
     */
    private void option1(Scanner scanner, Hotel hotel, Client client)  {
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
                System.out.println("Très bien je verifie si j'ai ce type de chambre disponible à ces dates");
                hotel.getReservationManager().effectuerReservation(client,type,dateDebut,dateFin);
            } catch (ParseException | DateInvalideException | ChambreNonDisponibleException e) {
                System.out.println("Erreur : "+e.getMessage());
            }
        }

    }

    /**
     * enregistre ou trouve un client
     * @param scanner
     * @param hotel
     * @return
     */
    private Client option2(Scanner scanner, Hotel hotel){
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

    /**
     * Modifie une reservation
     * @param hotel
     * @param client
     * @param scanner
     */
    private void option3(Hotel hotel, Client client,Scanner scanner) {
        try{
            System.out.println("Voici toutes vos reservation la quelle souhaitez vous modifier ? (Merci de saisir sont id)");
            hotel.getReservationManager().listReservationByClient(client).values().forEach(reservation -> System.out.println(reservation.toString()));
            int id = scanner.nextInt();
            System.out.println("Merci de saisir la nouvelles date de début");
            String dateD = scanner.next();
            System.out.println("Merci de saisir la nouvelles date de fin");
            String dateF = scanner.next();
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            Date dateDebut = null;
            Date dateFin = null;

            dateDebut = dateFormat.parse(dateD);
            dateFin = dateFormat.parse(dateF);
            hotel.getReservationManager().modifReservation(id,dateDebut,dateFin);
        }catch (ParseException | DateInvalideException | NotFoundException e){
            System.out.println("Erreur : "+e.getMessage());
        }
    }

    /**
     * affiche les details des 4 types de chambre
     * @param hotel
     */
    private void option5(Hotel hotel){
        System.out.println("Notre hotel compte 4 types de chambre : " + Arrays.toString(Detail.values()));
        System.out.println("Voici les details pour chaque type de chambre : \n" );
        System.out.println(hotel.getReservationManager().getDetailForAllChambre());
    }

    /**
     * Supprime une reservation
     * @param hotel
     * @param client
     * @param scanner
     */
    private void option7(Hotel hotel, Client client,Scanner scanner) throws NotFoundException {
        System.out.println("Voici toutes vos reservation la quelle souhaitez vous supprimer ? (Merci de saisir sont id)");
        hotel.getReservationManager().listReservationByClient(client).values().forEach(reservation -> System.out.println(reservation.toString()));
        int id = scanner.nextInt();
        hotel.getReservationManager().deleteReservation(id);
    }

    private void option4(Hotel hotel, Client client, Scanner scanner) throws NotFoundException {
        try{
            hotel.getReservationManager().listReservationByClient(client).values().forEach(reservation -> System.out.println(reservation.toString()));
            System.out.println("Sur quelle reservation souhaiter vous commander ? (tapez le numero de la reservation)");
            int reservation = scanner.nextInt();
            System.out.println("Très bien quelle type de rapas voulez vous commander ?");
            System.out.println("1-" + TypeRepas.valueOf(TypeRepas.PETIT_DEJEUNER.toString()));
            System.out.println("2-" + TypeRepas.valueOf(TypeRepas.DEJEUNER.toString()));
            System.out.println("3-" + TypeRepas.valueOf(TypeRepas.DINER.toString()));
            int choice = scanner.nextInt();
            TypeRepas type = null;
            switch (choice) {
                case 1:
                    type = TypeRepas.PETIT_DEJEUNER;
                    break;
                case 2:
                    type = TypeRepas.DEJEUNER;
                    break;
                case 3:
                    type = TypeRepas.DINER;
                    break;
                default:
                    System.out.println("type invalide");
            }
            System.out.println("Combien en voulez vous ?");
            int quantite = scanner.nextInt();
            List<Repas> repas = new ArrayList<>();
            for (int i = 0; i < quantite; i++) {
                repas.add(new Repas(type));
            }
            hotel.getReservationManager().getListReservation().get(reservation).ajouterCommande(repas);
            System.out.println("Commande effectuer avec succès");
        }catch (NotFoundException e){
            System.out.println("Erreur : "+e.getMessage());
        }
    }

    private void option6(Hotel hotel, Client client, Scanner scanner){
        try{
            hotel.getReservationManager().listReservationByClient(client).values().forEach(reservation -> System.out.println(reservation.toString()));
            System.out.println("Pour quelle reservation souhaitez vous la facture ? (tapez le numero)");
            int res = scanner.nextInt();
            Facture facture = new Facture(client,hotel.getReservationManager().getListReservation().get(res));
            System.out.println(facture);
        }catch (NotFoundException e){
            System.out.println("Erreur : " +e.getMessage());
        }
    }

}
