package ro.tuc.tp.tema4.DataLayer;

import ro.tuc.tp.tema4.BusinessLayer.BaseProduct;
import ro.tuc.tp.tema4.BusinessLayer.CompositeProduct;
import ro.tuc.tp.tema4.BusinessLayer.MenuItem;
import ro.tuc.tp.tema4.BusinessLayer.Order;
import ro.tuc.tp.tema4.model.Client;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class FileWriter1 {
    /**
     * scrie un mesaj intr-un fisier
     * @param filename numele fisierului
     * @param mesaj mesajul
     */
    public static void write(String filename, String mesaj) {
        try {
            File fileOutput = new File(filename);
            FileWriter write = new FileWriter(fileOutput);
            PrintWriter pw = new PrintWriter(write);
            pw.println(mesaj);
            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * generarea facturii pentru comanda
     * @param ord comanda efectuata
     * @param prod produsele
     */
    public static void generareFactura(Order ord, List<MenuItem> prod) {
        try {
            File fileOutput = new File("factura_" + ord.getOrderID() + ".txt");
            FileWriter write = new FileWriter(fileOutput);
            PrintWriter write1 = new PrintWriter(write);

            String mesaj = "Comanda nr." + ord.getOrderID() + "\n" +
                    "Data: " + ord.getOrderDate() + "\n" +
                    "Produse:\n";
            StringBuilder sb = new StringBuilder();
            AtomicInteger i = new AtomicInteger(1);
            prod.forEach(p -> sb.append((i.getAndIncrement()) + ": " +  p.getTitle() +"\n"));
            int price = prod.stream().mapToInt(MenuItem::computePrice).sum();
            String msg = mesaj + sb.toString() + "\n" + "Pret total: " + price;
            write1.println(msg);
            write1.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
