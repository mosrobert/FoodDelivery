package ro.tuc.tp.tema4.model;

import java.io.Serializable;

/**
 * Clasa care implementeaza Serializable, se foloseste pentru login
 */
public class Administrator implements Serializable {
    private String username,parola,nume;

    public Administrator(String username, String parola, String nume) {
        this.username = username;
        this.parola = parola;
        this.nume = nume;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getParola() {
        return parola;
    }

    public void setParola(String parola) {
        this.parola = parola;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }
}
