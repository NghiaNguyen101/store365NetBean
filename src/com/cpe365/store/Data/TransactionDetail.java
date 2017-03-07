package com.cpe365.store.Data;

/**
 * Created by nnguy101 on 2/27/17.
 */

import java.util.Date;
import java.util.List;
import com.cpe365.store.Data.SelectItem;

public class TransactionDetail {
    protected int transaction_id;
    protected Customer customer;
    protected String credit_card;
    protected List<SelectItem> cart;
    protected double amount;
    protected Date purchase_date;

    public TransactionDetail(int transaction_id, Customer customer,
                             String credit_card, List<SelectItem> cart,
                             double amount, Date purchase_date) {
        this.transaction_id = transaction_id;
        this.customer = customer;
        this.credit_card = credit_card;
        this.cart = cart;
        this.amount = amount;
        this.purchase_date = purchase_date;
    }

    public int getTransaction_id() {
        return transaction_id;
    }

    public void setTransaction_id(int transaction_id) {
        this.transaction_id = transaction_id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getCredit_card() {
        return credit_card;
    }

    public void setCredit_card(String credit_card) {
        this.credit_card = credit_card;
    }

    public List<SelectItem> getCart() {
        return cart;
    }

    public void setCart(List<SelectItem> cart) {
        this.cart = cart;
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
}
