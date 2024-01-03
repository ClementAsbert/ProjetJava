package main.java;

import main.java.Enum.Detail;

import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Main {
    public static Client enregistrement(Scanner scan, Hotel hotel){
        String nom,prenom,email,numeroTel;
        System.out.println("Renseigner votre Nom");
        nom = scan.nextLine();
        System.out.println("Renseigner votre Prénom");
        prenom = scan.nextLine();
        System.out.println("Renseigner votre email");
        email = scan.nextLine();
        System.out.println("Renseigner votre numero de téléphone");
        numeroTel = scan.nextLine();
        Client client = new Client(nom,prenom,email,"",numeroTel);
        hotel.addClient(client);
        return client;
    }
    public static void main(String[] args) throws Throwable {
        Hotel hotel = new Hotel("Melton");
        Client client = new Client("","","","","");
        for (Chambre chambre : hotel.getListChambre()) {
            Date dateDebut = new Date("01/01/2025");
            Date dateFin = new Date("07/01/2025");
            hotel.effectuerReservation(client, chambre.getDetail(), dateDebut, dateFin);
        }
        hotel.effectuerReservation(client, Detail.CHAMBRESIMPLE, new Date("01/01/2025"), new Date("07/01/2025"));
//        Hotel.saveHotel(hotel);
//        Scanner scan = new Scanner(System.in);
//        Optional<Client> client = Optional.empty();
//        Hotel hotel = Hotel.chargerHotel();
//        System.out.println("Bonjour bienvenue à l'hotel " + hotel.getNom());
//        System.out.println("Quesque je peux faire pour vous ?");
//        System.out.println("1 - Reserver une chambre");
//        if(scan.nextLine().equals("1")){
//            System.out.println("Etes vous déja client ? [oui/non]");
//            if(scan.nextLine().equals("oui")){
//                System.out.println("Pouvez vous me donner votre nom");
//                String name = scan.nextLine();
//                client = hotel.findClient(name);
//                if(client.isEmpty()){
//                    System.out.println("désoler je ne vous est pas trouvé veuillez vous enregistrer");
//                    client = Optional.of(enregistrement(scan, hotel));
//                }
//            }else{
//                enregistrement(scan,hotel);
//            }
//            System.out.println("Quelle type de chambre souhaitez vous reserver ?");
//            System.out.println(Arrays.toString(Detail.values()));
//            Detail type = null;
//            switch (scan.nextLine()){
//                case "1":
//                    type = Detail.CHAMBRESIMPLE;
//                    break;
//                case "2":
//                    type = Detail.CHAMBREDOUBLE;
//                    break;
//                case "3":
//                    type = Detail.CHAMBRELUXESIMPLE;
//                    break;
//                case "4":
//                    type = Detail.CHAMBRELUXEDOUBLE;
//                    break;
//            }
//            Date dateD = null;
//            Date deteF = null;
//            while(Objects.isNull(dateD) || Objects.isNull(deteF)){
//                System.out.println("A quelle date souhaitez vous commencer la reservation ? (dd/mm/yyyy)");
//                String dateDebut = scan.nextLine();
//                System.out.println("A quelle date souhaitez vous terminer la reservation ? (dd/mm/yyyy)");
//                String dateFin = scan.nextLine();
//                SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
//                try{
//                    dateD = format.parse(dateDebut);
//                    deteF = format.parse(dateFin);
//                }catch (ParseException e){
//                    System.out.println("format incorrect redonner les dates");
//                    e.printStackTrace();
//                }
//            }
//            hotel.effectuerReservation(client.get(),type,dateD,deteF);
//        }


    }

}