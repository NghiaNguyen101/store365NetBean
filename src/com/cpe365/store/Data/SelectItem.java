package com.cpe365.store.Data;

/**
 * Created by nnguy101 on 2/27/17.
 *
 * Store the select item in cart
 * Need item_id, qty, and price of item
 */

public class SelectItem {
    protected int item_id;
    protected int qty;
    protected double price;


    public SelectItem(int item_id, int qty, double price) {
        this.item_id = item_id;
        this.qty = qty;
        this.price = price;
    }

    public int getItem_id() {
        return item_id;
    }

    public void setItem_id(int item_id) {
        this.item_id = item_id;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getTotalPrice() {
        return qty*price;
    }

    @Override
    public String toString() {
        return  "{\n\titem_id: " + item_id + ",\n" +
                "\tqty: " + qty + ",\n" +
                "\tprice: " + price + "\n}";
    }
}
