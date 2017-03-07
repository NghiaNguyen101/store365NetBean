package com.cpe365.store.Data;

/**
 * Created by nnguy101 on 2/27/17.
 */

import java.util.Date;

public class Transaction {
    protected int id;
    protected int customer_id;
    protected String credit_card;
    protected double amount;
    protected Date purchase_date;

    public Transaction(int id, int customer_id, String credit_card, double amount, Date purchase_date) {
        this.id = id;
        this.customer_id = customer_id;
        this.credit_card = credit_card;
        this.amount = amount;
        this.purchase_date = purchase_date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public String getCredit_card() {
        return credit_card;
    }

    public void setCredit_card(String credit_card) {
        this.credit_card = credit_card;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getPurchase_date() {
        return purchase_date;
    }

    public void setPurchase_date(Date purchase_date) {
        this.purchase_date = purchase_date;
    }

    @Override
    public String toString() {
        return  "{\n\tid: " + id + ",\n" +
                "\tcustomer_id: " + customer_id + ",\n" +
                "\tcredit_card: " + credit_card + ",\n" +
                "\tamount: " + amount + ",\n" +
                "\tpurchase_date: " + purchase_date.toString() + "\n}";
    }
}
