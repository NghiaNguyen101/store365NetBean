package com.cpe365.store.Data;

/**
 * Created by nnguy101 on 2/27/17.
 */

public class Purchase {
    protected int id;
    protected int transaction_id;
    protected int item_id;
    protected int qty;
    protected double price;

    public Purchase(int id, int transaction_id, int item_id, int qty, double price) {
        this.id = id;
        this.transaction_id = transaction_id;
        this.item_id = item_id;
        this.qty = qty;
        this.price = price;
    }

    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public int getTransaction_id() {
        return transaction_id;
    }

    public void setTransaction_id(int transaction_id) {
        this.transaction_id = transaction_id;
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

    @Override
    public String toString() {
        return  "{\n\tid:" + id + ",\n" +
                 "\ttransaction_id: " + transaction_id + ",\n" +
                "\titem_id: " + item_id + ",\n" +
                "\tqty: " + qty + ",\n" +
                "\tprice: " + price + "\n}";
    }
}
