package ro.tuc.tp.tema4.BusinessLayer;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * clasa care implementeaza Serializable
 */
public class Order implements Serializable {
    private int orderID;
    private int clientID;
    private LocalDateTime orderDate;

    public Order(int orderID, int clientID, LocalDateTime orderData){
        this.orderID=orderID;
        this.clientID=clientID;
        this.orderDate =orderData;

    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public int getClientID() {
        return clientID;
    }

    public void setClientID(int clientID) {
        this.clientID = clientID;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    //20210520101
    @Override
    public int hashCode() {
        int hash;
        String s_hash=
                (orderDate.getMonthValue()<10?"0"+orderDate.getMonthValue():orderDate.getMonthValue())+""+
                (orderDate.getDayOfMonth()<10?"0"+orderDate.getDayOfMonth():orderDate.getDayOfMonth())+""
                +clientID+""+(orderID<10?"0"+orderID:orderID);
        hash=Integer.parseInt(s_hash);
        return hash;
    }
    public String getDate(){
        return orderDate.getDayOfMonth()+"/"+orderDate.getMonthValue()+"/"+orderDate.getYear();
    }
}
