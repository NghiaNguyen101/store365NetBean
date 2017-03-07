package com.cpe365.store; /**
 * Created by Owner on 2/26/2017.
 */

import java.sql.SQLException;
import java.util.List;
import com.cpe365.store.Data.Item;
import com.cpe365.store.DAO.ItemDAO;

public class Main {
    public static void main(String [] args) throws SQLException
    {
        System.out.println("hello world!");
        ItemDAO itemDao = new ItemDAO();
        List<Item> itemList = itemDao.searchItems("B");
        for (Item item : itemList) {
            System.out.println(item.toString());
        }
        //ListItemView view = new ListItemView();
    }
}
