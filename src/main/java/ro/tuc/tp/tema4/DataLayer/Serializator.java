package ro.tuc.tp.tema4.DataLayer;

import ro.tuc.tp.tema4.BusinessLayer.DeliveryService;
import ro.tuc.tp.tema4.BusinessLayer.MenuItem;
import ro.tuc.tp.tema4.BusinessLayer.Order;
import ro.tuc.tp.tema4.model.Client;
import ro.tuc.tp.tema4.model.Inregistrare;

import java.io.*;
import java.util.List;
import java.util.Map;

public class Serializator {
    public static void serializare(String fileName, Object obj) {
        try {
            FileOutputStream fileOut = new FileOutputStream(fileName);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(obj);
            out.close();
            fileOut.close();
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    public static Object deserializare(String fileName) {
        Object obj = null;
        try {
            FileInputStream fileIn = new FileInputStream(fileName);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            obj = in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException i) {
            i.printStackTrace();
        } catch (ClassNotFoundException c) {
            c.printStackTrace();
        }
        return obj;
    }

    /**
     * Deserializarea pentru produse
     */
    static public void deserializareProduse() {
        DeliveryService ds = new DeliveryService();
        try {
            FileInputStream fileIn = new FileInputStream("produse.txt");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            Object obj;
            int n = in.readInt();
            for (int i = 0; i < n; i++) {
                obj = in.readObject();
                MenuItem p = (MenuItem) obj;
                ds.addProduct(p);
            }
            in.close();
            fileIn.close();
        } catch (EOFException ex) {
            System.out.println(ex.getMessage());
        } catch (IOException | ClassNotFoundException i) {
            i.printStackTrace();
        }
    }

    /**
     * serializarea produselor
     */
    static public void serializareProduse() {
        DeliveryService ds = new DeliveryService();
        try {
            FileOutputStream fileOut = new FileOutputStream("produse.txt");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            Object obj;
            out.writeInt(DeliveryService.getProducts().size());
            for (MenuItem p : DeliveryService.getProducts()) {
                out.writeObject(p);
            }
            out.close();
            fileOut.close();
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    /**
     * Serializarea clientilor
     */
    static public void serializareClienti() {
        try {
            FileOutputStream fileOut = new FileOutputStream("clienti.txt");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            Object obj;
            out.writeInt(Inregistrare.getCl().size());
            for (Client p : Inregistrare.getCl()) {
                out.writeObject(p);
            }
            out.close();
            fileOut.close();
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    /**
     * Deserializarea clientilor
     */
    static public void deserializareClienti() {
        try {
            FileInputStream fileIn = new FileInputStream("clienti.txt");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            Object obj;
            int n = in.readInt();
            for (int i = 0; i < n; i++) {
                obj = in.readObject();
                Client cl = (Client) obj;
                Inregistrare.addUser(cl);
            }
            in.close();
            fileIn.close();
        } catch (EOFException ex) {
            System.out.println(ex.getMessage());
        } catch (IOException | ClassNotFoundException i) {
            i.printStackTrace();
        }
    }

    /**
     * Serializarea comenzilor
     */
    static public void serializareComenzi(){
        try {
            FileOutputStream fileOut = new FileOutputStream("comenzi.txt");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(DeliveryService.getOrders());
            out.close();
            fileOut.close();
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    /**
     * Deserializarea comenzilor
     */
    static public void deserializareComenzi(){
        try {
            FileInputStream fileIn = new FileInputStream("comenzi.txt");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            DeliveryService.setOrders((Map<Order, List<MenuItem>>) in.readObject());
            in.close();
            fileIn.close();
        } catch(EOFException ex){
            System.out.println(ex.getMessage());
        } catch (IOException | ClassNotFoundException i) {
            i.printStackTrace();
        }
    }
}
