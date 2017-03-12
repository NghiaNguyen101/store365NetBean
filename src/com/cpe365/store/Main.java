package com.cpe365.store; /**
 * Created by Owner on 2/26/2017.
 */

import java.sql.SQLException;
import java.util.List;
import java.util.LinkedList;
import com.cpe365.store.Data.*;
import com.cpe365.store.DAO.ItemDAO;
import com.cpe365.store.DAO.TransactionDAO;
import com.cpe365.store.DAO.PurchaseDAO;
import com.cpe365.store.GUI.ItemList;

public class Main {
    public static void main(String [] args) throws SQLException
    {
        System.out.println("hello world!");
        /**
        ItemDAO itemDao = new ItemDAO();
        List<Item> itemList = itemDao.searchItems("B");
        for (Item item : itemList) {
            System.out.println(item.toString());
        }
        
        TransactionDAO transactionDAO = new TransactionDAO();
        TransactionDetail transactionDetail = transactionDAO.getTransactionDetail(3);
        for(SelectItem item : transactionDetail.getCart()) {
            System.out.println(item.toString());
        }
        //itemDao.deleteItem(6);
        //transactionDAO = new TransactionDAO();
        List<SelectItem> cart = new LinkedList();
        cart.add(new SelectItem(1, 2, 24.99));
        cart.add(new SelectItem(2, 1, 14.99));
        cart.add(new SelectItem(3, 1, 49.99));
        boolean res = transactionDAO.postTransaction("Nghia", "Some Where", "123456789", cart);
        */
        //PurchaseDAO purchaseDAO = new PurchaseDAO();
        //purchaseDAO.postPurchases(8, cart);
        ItemList itemList = new ItemList();
        itemList.setVisible(true);
    }
}
