package ro.tuc.tp.tema4.model;

import java.io.Serializable;

/**
 * Clasa care implementeaza Serializable, se foloseste pentru login
 */
public class Client implements Serializable {
    private int id;
    private String nume, email, username, parola;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public Client(String nume, String email, String username, String parola) {
        this.nume = nume;
        this.email = email;
        this.username = username;
        this.parola = parola;
    }
}
