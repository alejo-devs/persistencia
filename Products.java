package com.mycompany.persistencia;
   public class Products {


    private double price;
    private String name;
    private int amount;

    public Products() {
        price = 0;
        name ="";
        amount = 0;
    }

    public Products(double price, String name, int amount) {
        this.price = price;
        this.name = name;
        this.amount = amount;
    }


    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

}

    
