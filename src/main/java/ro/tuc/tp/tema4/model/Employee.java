package ro.tuc.tp.tema4.model;

import java.io.Serializable;

/**
 * Clasa care implementeaza serializable, se foloseste pentru login
 */
public class Employee implements Serializable {
    private String nume,username,parola;

    public Employee(String nume, String username, String parola) {
        this.nume = nume;
        this.username = username;
        this.parola = parola;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
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
}
