package ro.tuc.tp.tema4.BusinessLayer;

import java.io.Serializable;

/**
 * Clasa abstracta care implementeaza MenuItem
 */
public abstract class MenuItem implements Serializable {
    /**
     * determina pretul total al fiecarui produs
     * @return pretul produsului
     */
    abstract public int computePrice();
    abstract public String getTitle();

}
