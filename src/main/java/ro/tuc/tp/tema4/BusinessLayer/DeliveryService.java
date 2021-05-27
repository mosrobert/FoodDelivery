package ro.tuc.tp.tema4.BusinessLayer;

import ro.tuc.tp.tema4.DataLayer.FileWriter1;
import ro.tuc.tp.tema4.DataLayer.ReadExcel;
import ro.tuc.tp.tema4.model.Client;
import ro.tuc.tp.tema4.model.Inregistrare;

import java.util.*;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * Clasa care extinde Observable si implementeaza IDeliveryServiceProcessing
 */
public class DeliveryService extends Observable implements IDeliveryServiceProcessing {
    static private Map<Order, List<MenuItem>> orders = new HashMap<>();
    static private List<MenuItem> products = new ArrayList<>();

    public static Map<Order, List<MenuItem>> getOrders() {
        return orders;
    }

    public static void setOrders(Map<Order, List<MenuItem>> orders) {
        DeliveryService.orders = orders;
    }

    public static List<MenuItem> getProducts() {
        return products;
    }

    public static void setProducts(List<MenuItem> products) {
        DeliveryService.products = products;
    }

    /**
     * importarea produselor din excel
     * @param path
     */
    @Override
    public void importProduct(String path) {
        products= ReadExcel.read(path);
    }

    /**
     * adaugarea unui produs
     * @param p
     */
    @Override
    public void addProduct(MenuItem p) {
        products.add(p);

    }

    /**
     * Editeaza caracteristicile unui produs
     * @param title
     * @param p
     */
    @Override
    public void editProduct(String title, BaseProduct p) {
        MenuItem m = products.stream().filter(p1 -> p1 instanceof BaseProduct)
                .filter(p1 -> ((BaseProduct) p1).getTitle().equals(title)).collect(Collectors.toList()).get(0);
        BaseProduct pr = (BaseProduct) m;
        pr.setCalories(p.getCalories());
        pr.setFat(p.getFat());
        pr.setPrice(p.getPrice());
        pr.setProtein(p.getProtein());
        pr.setSodium(p.getSodium());

    }

    /**
     * Crearea unui nou produs
     * @param title titlul produsului
     * @param p produsul
     */
    @Override
    public void createProduct(String title, List<BaseProduct> p) {
        CompositeProduct comp = new CompositeProduct(title, p);
        products.add(comp);
    }

    /**
     * stergerea unui produs din lista de produse
     * @param p
     */
    @Override
    public void deleteProduct(MenuItem p) {
        String title;
        if (p instanceof BaseProduct) {
            title = ((BaseProduct) p).getTitle();
            MenuItem m = products.stream().filter(p1 -> p1 instanceof BaseProduct)
                    .filter(p1 -> ((BaseProduct) p1).getTitle().equals(title)).collect(Collectors.toList()).get(0);
            products.remove(m);
        } else {
            title = ((CompositeProduct) p).getTitle();
            MenuItem m = products.stream().filter(p1 -> p1 instanceof CompositeProduct)
                    .filter(p1 -> ((CompositeProduct) p1).getTitle().equals(title)).collect(Collectors.toList()).get(0);
            products.remove(m);
        }
    }

    /**
     * generarea raportului care afiseaza comenzile dintr-o perioada de timp
     * @param startHour ora de inceput
     * @param endHour ora de final
     * @return returneaza un string cu produsele comandate in intervalul orar
     */
    @Override
    public String generateReports1(int startHour, int endHour) {
        StringBuilder sb = new StringBuilder();
        Set<Order> ord = orders.keySet().stream().filter(or -> startHour <= or.getOrderDate().getHour() && or.getOrderDate().getHour() <= endHour).collect(Collectors.toSet());
        sb.append("Comenzile din intervalul orar " + startHour + " si " + endHour + ":");
        int i = 1;
        for (Order or : ord) {
            sb.append("\n" + i + ": Order " + or.getOrderID() + "->\n");
            orders.get(or).stream().forEach(p -> sb.append("\t" + p.toString()));
            i++;
        }
        return sb.toString();
    }

    /**
     * Generarea raportului in care un produs a fost comandat de mai multe de n ori
     * @param times numarul de comenzi ale produsului
     * @return returneaza un string cu produsele comandate
     */
    @Override
    public String generateReports2(int times) {
        StringBuilder sb = new StringBuilder();
        List<MenuItem> allprod = new ArrayList<>();
        Set<Order> ord = orders.keySet();
        Set<MenuItem> distinctProd = new HashSet<>();
        List<MenuItem> finalProd = new ArrayList<>();
        for (Order o : ord) {
            List<MenuItem> list = orders.get(o);
            allprod.addAll(list);
            distinctProd.addAll(list);
        }
        sb.append("Produsele comandate de " + times + " ori:");
        AtomicInteger i = new AtomicInteger(1);
        distinctProd.forEach(p -> {
            int count = (int) allprod.stream().filter(pr -> pr.getTitle().equals(p.getTitle())).count();
            if (count >= times) finalProd.add(p);
            i.getAndIncrement();
        });
        finalProd.forEach(p -> sb.append("\n" + i + ": " + p.toString()));
        return sb.toString();
    }

    /**
     * Generarea raportului in care un client a comandat de un anumit numar de ori si o valoare mai mare decat cea specificata
     * @param times numarul de comenzi
     * @param minValue valoarea minima a comenzii
     * @return
     */
    @Override
    public String generateReports3(int times, int minValue) {
        Set<Order> orders = DeliveryService.getOrders().keySet();
        orders = orders.stream().filter(order -> getOrderValue(order) >= minValue).collect(Collectors.toSet());
        List<Integer> clientsID = new ArrayList<>();
        List<Integer> finalClientsID = clientsID;
        orders.forEach(order -> {
            if (!finalClientsID.contains(order.getClientID()))
                finalClientsID.add(order.getClientID());
        });
        clientsID = clientsID.stream().sorted().collect(Collectors.toList());
        StringBuilder sb = new StringBuilder();
        Set<Order> finalOrders = orders;
        AtomicInteger i = new AtomicInteger(1);
        clientsID.forEach(id -> {
            if (finalOrders.stream().filter(order -> order.getClientID() == id).count() >= times)
                sb.append("  ").append(i.getAndIncrement()).append(". ").append(getClientName(id)).append(" (id = ").append(id).append(")\n");
        });
        return "Clientii care au comandat de " + times + " ori, iar valoarea comenzii a fost peste " + minValue + "\n" + sb.toString();
    }

    private String getClientName(int id) {
        System.out.println("---" + id);
        for (Client client : Inregistrare.getCl()) {
            System.out.println(client.getId() + "---");
        }
        return Inregistrare.getCl().stream().filter(c -> c.getId() == id).collect(Collectors.toList()).get(0).getNume();
    }

    private int getOrderValue(Order order) {
        return DeliveryService.getOrders().get(order).stream().mapToInt(MenuItem::computePrice).sum();
    }

    /**
     * Genereaza raportul in care o comanda a fost comandata in acea zi
     * @param date data in care s-a facut comanda
     * @return returneaza un string cu comenzile
     */
    @Override
    public String generateReports4(String date) {
        Set<Order> orders = DeliveryService.getOrders()
                .keySet().stream()
                .filter(order -> order.getDate().equals(date))
                .collect(Collectors.toSet());
        List<List<MenuItem>> liste = new ArrayList<>();
        List<MenuItem> toateProdusele = new ArrayList<>();
        List<String> titlu = new ArrayList<>();
        orders.forEach(order -> liste.add(DeliveryService.getOrders().get(order)));
        liste.forEach(toateProdusele::addAll);
        List<MenuItem> produsele = toateProdusele.stream().filter(p -> p instanceof BaseProduct).collect(Collectors.toList());
        toateProdusele.stream().filter(p -> p instanceof CompositeProduct)
                .forEach(cp -> produsele.addAll(((CompositeProduct) cp).getProducts()));
        List<String> finalTitlu = titlu;
        produsele.forEach(p -> {
            if (!finalTitlu.contains(p.getTitle()))
                finalTitlu.add(p.getTitle());
        });
        StringBuilder sb = new StringBuilder("\n  produsele comandate in ziua " + date + ":\n");
        AtomicInteger i = new AtomicInteger(1);
        titlu.forEach(name -> {
            long count = produsele.stream().filter(p -> p.getTitle().equals(name)).count();
            sb.append("  ").append(i.getAndIncrement()).append(". ").append(name).append(" --> ").append(count).append("\n");
        });
        return sb.toString();
    }

    @Override
    public void createOrder(Order or, List<MenuItem> prod) {
        orders.put(or, prod);
        FileWriter1.generareFactura(or,prod);
    }

    /**
     * cautarea produsului dupa un anumit criteriu si o valoare
     * @param criteriu criteriul dupa care se cauta produsul
     * @param val valoarea dupa care se cauta produsul
     * @return returneaza produsele care se potrivesc criteriului
     */
    @Override
    public List<MenuItem> searchProd(String criteriu, String val) {
        if (criteriu.equals("Title"))
            return products.stream()
                    .filter(p -> (p instanceof BaseProduct) ? ((BaseProduct) p).getTitle().contains(val) : ((CompositeProduct) p).getTitle().contains(val))
                    .collect(Collectors.toList());
        else if (criteriu.equals("Rating")) {
            String[] split = val.split("-");
            double minVal = Double.parseDouble(split[0]);
            double maxVal = Double.parseDouble(split[1]);
            return products.stream().filter(p -> p instanceof BaseProduct)
                    .filter(p -> ((BaseProduct) p).getRating() >= minVal && ((BaseProduct) p).getRating() <= maxVal)
                    .collect(Collectors.toList());
        } else {
            String[] split = val.split("-");
            int minVal = Integer.parseInt(split[0]);
            int maxVal = Integer.parseInt(split[1]);
            if (criteriu.equals("Calories")) {
                return products.stream().filter(p -> p instanceof BaseProduct)
                        .filter(p -> ((BaseProduct) p).getCalories() >= minVal && ((BaseProduct) p).getCalories() <= maxVal)
                        .collect(Collectors.toList());
            }
            if (criteriu.equals("Protein")) return products.stream().filter(p -> p instanceof BaseProduct)
                    .filter(p -> ((BaseProduct) p).getProtein() >= minVal && ((BaseProduct) p).getProtein() <= maxVal)
                    .collect(Collectors.toList());
            if (criteriu.equals("Fat")) {
                return products.stream().filter(p -> p instanceof BaseProduct)
                        .filter(p -> ((BaseProduct) p).getFat() >= minVal && ((BaseProduct) p).getFat() <= maxVal)
                        .collect(Collectors.toList());
            }
            if (criteriu.equals("Sodium"))
                return products.stream().filter(p -> p instanceof BaseProduct)
                        .filter(p -> ((BaseProduct) p).getSodium() >= minVal && ((BaseProduct) p).getSodium() <= maxVal)
                        .collect(Collectors.toList());
            if (criteriu.equals("Price"))
                return products.stream().filter(p -> p instanceof BaseProduct)
                        .filter(p -> ((BaseProduct) p).getPrice() >= minVal && ((BaseProduct) p).getPrice() <= maxVal)
                        .collect(Collectors.toList());
        }
        return null;
    }
}
