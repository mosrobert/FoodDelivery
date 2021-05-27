package ro.tuc.tp.tema4.BusinessLayer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Clasa care extinde MenuItem si implementeaza Serializable
 */
public class CompositeProduct extends MenuItem implements Serializable {
   private String title;
   private List<BaseProduct> products=new ArrayList<>();

    /**
     * Calculeaza suma totala a produselor din composite product
     * @return returneaza un int
     */
    @Override
    public int computePrice() {
        return products.stream().mapToInt(BaseProduct::getPrice).sum();
    }

    public CompositeProduct(String title, List<BaseProduct> products) {
        this.title = title;
        this.products = products;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<BaseProduct> getProducts() {
        return products;
    }

    public void setProducts(List<BaseProduct> products) {
        this.products = products;
    }

    /**
     * Afisarea composite product-ului
     * @return returneaza un string
     */
    @Override
    public String toString() {
        return "CompositeProduct{" +
                "title='" + title + '\'' +
                ", products=" + products +
                '}';
    }
}
