package ro.tuc.tp.tema4.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Clasa in care se tin clientii, administratorul si angajatii
 */
public class Inregistrare {
    static private List<Client > cl=new ArrayList<>();
    static private List<Administrator> admin=new ArrayList<>();
    static private List<Employee> emp=new ArrayList<>();

    static public  List<Client> getCl() {
        return cl;
    }

   static public void setCl(List<Client> cl) {
        Inregistrare.cl = cl;
    }

    static public List<Administrator> getAdmin() {
        return admin;
    }

    static public void setAdmin(List<Administrator> admin) {
        Inregistrare.admin = admin;
    }

    static public List<Employee> getEmp() {
        return emp;
    }

    static public void setEmp(List<Employee> emp) {
        Inregistrare.emp = emp;
    }

    /**
     * adaugarea unui nou client
     * @param c clientul adaugat
     */
    static public void addUser(Client c){
        cl.add(c);
    }

    /**
     * adaugarea unui nou admin
     * @param ad adminul adaugat
     */
    static public void addUser(Administrator ad){
        admin.add(ad);
    }

    static public void addUser(Employee e){
        emp.add(e);
    }

    /**
     * Verifica daca la logare contul introdus este pentru client
     * @param usn username ul de la login
     * @param pass parola de la login
     * @return true sau false
     */
    static public boolean isClient(String usn,String pass){
        List<Client >cl1=cl.stream().filter(c1->c1.getUsername().equals(usn)&&c1.getParola().equals(pass)).collect(Collectors.toList());
        return cl1.size() > 0;

    }
    static public boolean isAdmin(String usn,String pass){
        List<Administrator >ad1=admin.stream().filter(adm1->adm1.getUsername().equals(usn)&&adm1.getParola().equals(pass)).collect(Collectors.toList());
        return ad1.size() > 0;
    }
    static public boolean isEmployee(String usn,String pass){
        List<Employee >em1=emp.stream().filter(emp1->emp1.getUsername().equals(usn)&&emp1.getParola().equals(pass)).collect(Collectors.toList());
        return em1.size() > 0;
    }
}
