package main.java;

import main.java.Interface.PersistenceManagerInterface;

import java.io.*;

public class PersistenceManager implements Serializable, PersistenceManagerInterface {

    private static final long serialVersionUID = -1081031926343701746L;
    @Override
    public void saveHotel(Hotel hotel){
        try(ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("Sauvegarde"))){
            os.writeObject(hotel);
            System.out.println("save");
        }catch (IOException exception){
            exception.printStackTrace();
        }
    }

    @Override
    public Hotel chargerHotel(){
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Sauvegarde"))){
            System.out.println("récupération");
            return (Hotel) ois.readObject();
        }catch (IOException | ClassNotFoundException e){
            e.printStackTrace();
            return null;
        }
    }

}
