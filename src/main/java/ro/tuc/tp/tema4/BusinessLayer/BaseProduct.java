package ro.tuc.tp.tema4.BusinessLayer;

import java.awt.*;
import java.io.*;

/**
 * este clasa care contine toate atributele unui produs
 */
public class BaseProduct extends MenuItem implements Serializable {

    private String title;
    private double rating;
    private int calories;
    private int protein;
    private int fat;
    private int sodium;
    private int price;

    /**
     * Creeaza un obiect de tipul BaseProduct
     * @param title denumirea obiectului
     * @param rating ratingul produsului
     * @param calories caloriile produsului
     * @param protein proteinele produsului
     * @param fat grasimile produsului
     * @param sodium sodiul produsului
     * @param price pretul produsului
     */
    public BaseProduct(String title, Double rating, Integer calories, Integer protein, Integer fat, Integer sodium, Integer price){
        this.title=title;
        this.rating=rating;
        this.calories=calories;
        this.protein=protein;
        this.fat=fat;
        this.sodium=sodium;
        this.price=price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public int getProtein() {
        return protein;
    }

    public void setProtein(int protein) {
        this.protein = protein;
    }

    public int getFat() {
        return fat;
    }

    public void setFat(int fat) {
        this.fat = fat;
    }

    public int getSodium() {
        return sodium;
    }

    public void setSodium(int sodium) {
        this.sodium = sodium;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public int computePrice() {
        return price;
    }

    /**
     * Afiseaza informatii despre produs
     * @return returneaza un string cu informatiile produsului
     */
    @Override
    public String toString() {
        return "BaseProduct{" +
                "title='" + title + '\'' +
                ", rating=" + rating +
                ", calories=" + calories +
                ", protein=" + protein +
                ", fat=" + fat +
                ", sodium=" + sodium +
                ", price=" + price +
                '}';
    }
}
