package ro.tuc.tp.tema4.BusinessLayer;

import java.awt.*;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Interfata care determina operatiile efectuate de administrator si de client
 */
public interface IDeliveryServiceProcessing {
    /**
     * Importarea produselor dintr-un fisier excel
     * @param path fisierul din care se adauga
     */
    public void importProduct(String path);

    /**
     * adaugarea produselor in lista
     * @param p produsul care se doreste adaugat
     */
    public void addProduct(MenuItem p);

    /**
     * editarea datelor unui produs
     * @param title numele produsului
     * @param p produsul
     */
    public void editProduct(String title, BaseProduct p);

    /**
     * crearea unui produs nou
     * @param title numele produsului
     * @param p produsul
     */
    public void createProduct(String title, List<BaseProduct> p);

    /**
     * Stergerea unui produs din lista de produse
     * @param p produsul
     */
    public void deleteProduct(MenuItem p);

    /**
     * generarea raportului care afiseaza comenzile dintr-o perioada de timp
     * @param startHourt ora de inceput
     * @param endHour ora de final
     * @return returneaza produsele corespunzatoare
     */
    public String generateReports1(int startHourt, int endHour);

    /**
     * Generarea raportului in care un produs a fost comandat de mai multe de n ori
     * @param times numarul de ori
     * @return returneaza un string cu produsele
     */
    public String generateReports2(int times);

    /**
     * Generarea raportului in care un client a comandat de un anumit numar de ori si o valoare mai mare decat cea specificata
     * @param times numarul de cate ori s-a comandat
     * @param minValue valoarea minima
     * @return returneaza un string cu produsele
     */
    public String generateReports3(int times, int minValue);

    /**
     * Genereaza raportul in care o comanda a fost comandata in acea zi
     * @param dt data in care s-a efectuat comanda
     * @return returneaza produsele
     */
    public String generateReports4(String dt);

    /**
     * crearea unei comenzi
     * @param or comanda
     * @param prod produsul comandat
     */
    public void createOrder(Order or, List<MenuItem> prod);

    /**
     * cautarea unui produs dupa un anumit criteriu
     * @param criteriu criteriul dupa care se cauta
     * @param val valoarea pusa in functie de criteriu
     * @return returneaza produsele
     */
    public List<MenuItem> searchProd(String criteriu, String val);
}
