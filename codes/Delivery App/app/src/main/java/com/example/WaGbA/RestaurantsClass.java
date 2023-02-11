package com.example.WaGbA;

public class RestaurantsClass
{
  String name,purl;
    RestaurantsClass()
    {

    }
    public RestaurantsClass(String name, String purl) {
        this.name = name;
        this.purl = purl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getPurl() {
        return purl;
    }

    public void setPurl(String purl) {
        this.purl = purl;
    }
}
