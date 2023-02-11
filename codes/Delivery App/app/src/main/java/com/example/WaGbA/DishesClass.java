package com.example.WaGbA;

public class DishesClass {

    String purl,price;
    DishesClass()
    {

    }

    public DishesClass(String purl, String price) {
        this.purl = purl;
        this.price = price;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPurl() {
        return purl;
    }

    public void setPurl(String purl) {
        this.purl = purl;
    }
}