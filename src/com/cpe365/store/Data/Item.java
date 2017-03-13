package com.cpe365.store.Data;

/**
 * Created by nnguy101 on 2/27/17.
 */

public class Item {
    protected int id;
    protected String name;
    protected double price;
    protected String description;
    protected String manufacturer;
    protected int stock;
    protected boolean discontinued;

    public Item(int id, String name, double price, String description,
                String manufacturer, int stock, boolean discontinued) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.manufacturer = manufacturer;
        this.stock = stock;
        this.discontinued = discontinued;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public boolean isDiscontinued() {
        return discontinued;
    }

    public void setDiscontinued(boolean discontinued) {
        this.discontinued = discontinued;
    }

    @Override
    public String toString() {
        return  "{\n\tid: " + id + ",\n" +
                "\tname: " + name + ",\n" +
                "\tprice: " + price + ",\n" +
                "\tdescription: " + description + ",\n" +
                "\tmanufacturer: " + manufacturer + ",\n" +
                "\tstock: " + stock + ",\n" +
                "\tdiscontinued: " + discontinued + "\n}";
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!Item.class.isAssignableFrom(obj.getClass())) {
            return false;
        }
        final Item other = (Item) obj;
        
        return this.id != other.id;
    }
}