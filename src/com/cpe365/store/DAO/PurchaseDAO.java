package com.cpe365.store.DAO;

/**
 * Created by nnguy101 on 2/27/17.
 */

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.LinkedList;

import com.cpe365.store.Data.Purchase;
import com.cpe365.store.Data.SelectItem;
import com.cpe365.store.DB.ConnectionFactory;
import com.cpe365.store.DB.DbUtil;

public class PurchaseDAO {
    private Connection connection;
    private Statement statement;
    private PreparedStatement preparedStatement;

    // Constructor
    public PurchaseDAO() {}

    /**
     * Get purchases base on transaction
     * @param transaction_id - use to look up purchases
     *
     * @return purchaseList - list of purchases
     */
    public List<Purchase> getPurchase(int transaction_id) throws SQLException {
        String query = "SELECT * FROM purchaseTable WHERE transaction_id=?";
        ResultSet rs = null;
        List<Purchase> purchaseList = new LinkedList<Purchase>();
        try {
            // Get connection and prepare stmt
            connection = ConnectionFactory.getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, transaction_id);

            // Execute query
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                purchaseList.add(purchaseMapper(rs));
            }
        } catch (SQLException e) {
            System.out.println("Error Get Item");
            e.printStackTrace();
        } finally {
            // Close connection
            DbUtil.close(rs);
            DbUtil.close(preparedStatement);
            DbUtil.close(connection);
        }
        return purchaseList;
    }

    /**
     * Insert purchases into purchaseTable
     * @param transaction_id - id for specific transaction
     * @param cart - list of selected items
     *
     * @return boolean - true if success, false if not
     */
    public boolean postPurchases(int transaction_id, List<SelectItem> cart)
            throws SQLException{
        if (cart.size() < 1) {
            System.out.println("Cart is empty - purchases!");
            return false;
        }
                    
        String query = "INSERT INTO purchaseTable (transaction_id, item_id," +
                "qty, price) VALUES (?,?,?,?)";
        boolean success = true;

        try {
            // Get connection
            connection = ConnectionFactory.getConnection();
            preparedStatement = connection.prepareStatement(query);
            connection.setAutoCommit(false);

            // Prepare statment for all items
            for (SelectItem item : cart) {
                preparedStatement.setInt(1, transaction_id);
                preparedStatement.setInt(2, item.getItem_id());
                preparedStatement.setInt(3, item.getQty());
                preparedStatement.setDouble(4, item.getPrice());
                preparedStatement.addBatch();
            }
            
            // Execute batch
            preparedStatement.executeBatch();
            connection.commit();

        } catch (SQLException e) {
            System.out.println("Error insert purchases");
            e.printStackTrace();
            success = false;

            // Roll back
            if (connection != null)
                connection.rollback();
        } finally {
            // Close Connection
            DbUtil.close(preparedStatement);
            DbUtil.close(connection);
        }

        return success;
    }

    /**
     * Get purchase object from result set
     * @param rs - result set
     *
     * @return purchase object
     */
    private Purchase purchaseMapper(ResultSet rs) throws SQLException {
        return new Purchase(
                rs.getInt("id"),
                rs.getInt("transaction_id"),
                rs.getInt("item_id"),
                rs.getInt("qty"),
                rs.getDouble("price")
        );
    }
}
