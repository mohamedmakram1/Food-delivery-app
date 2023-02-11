package com.example.WaGbA;

public class Orders {

    private String Mealdetails,OrdeSstatus,Quantity;

    public Orders(){}

    public Orders(String mealdetails, String ordeSstatus, String quantity) {
        Mealdetails = mealdetails;
        OrdeSstatus = ordeSstatus;
        Quantity = quantity;
    }

    public String getMealdetails() {
        return Mealdetails;
    }

    public void setMealdetails(String mealdetails) {
        Mealdetails = mealdetails;
    }

    public String getOrdeSstatus() {
        return OrdeSstatus;
    }

    public void setOrdeSstatus(String ordeSstatus) {
        OrdeSstatus = ordeSstatus;
    }

    public String getQuantity() {
        return Quantity;
    }

    public void setQuantity(String quantity) {
        Quantity = quantity;
    }
}
