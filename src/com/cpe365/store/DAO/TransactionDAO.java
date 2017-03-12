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
import java.util.stream.Collectors;

import com.cpe365.store.Data.Customer;
import com.cpe365.store.DB.ConnectionFactory;
import com.cpe365.store.DB.DbUtil;
import com.cpe365.store.Data.SelectItem;
import com.cpe365.store.Data.Transaction;
import com.cpe365.store.Data.TransactionDetail;

public class TransactionDAO {
    private Connection connection;
    private Statement statement;
    private PreparedStatement preparedStatement;
    private PurchaseDAO purchaseDAO;
    private CustomerDAO customerDAO;

    // Constructor
    public TransactionDAO() {
        purchaseDAO = new PurchaseDAO();
        customerDAO = new CustomerDAO();
    }

    /**
     * Get Transaction based on id
     * @param transaction_id
     *
     * @return Transaction object
     */
    public Transaction getTransaction(int transaction_id) throws SQLException {
        String query = "SELECT * FROM TransactionTable WHERE id=?";
        ResultSet rs = null;
        Transaction transaction = null;

        try {
            // Get connection and prepare stmt
            connection = ConnectionFactory.getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, transaction_id);

            // Execute query
            rs = preparedStatement.executeQuery();
            if (rs.next()) {
                transaction = transactionMapper(rs);
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
        return transaction;
    }

    /**
     * Get Transaction detail, include all info about items
     * @param transaction_id - useto look up
     *
     * @return TransactionDetail object
     */
    public TransactionDetail getTransactionDetail(int transaction_id) throws
            SQLException {
        String query = "SELECT * FROM transactionTable WHERE id=? ";
        ResultSet rs = null;
        TransactionDetail transactionDetail = null;
        try {
            // Get connection
            connection = ConnectionFactory.getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, transaction_id);

            // Execute Query
            rs = preparedStatement.executeQuery();

            // Found a transaction
            if (rs.next()) {
                // get Customer
                Customer customer = customerDAO.getCustomer(
                        rs.getInt("customer_id"));

                // get items -> selectItems
                List<SelectItem> itemList = purchaseDAO.getPurchase
                        (transaction_id)
                        .stream()
                        .map(purchase -> new SelectItem(purchase.getItem_id(),
                                                        purchase.getQty(),
                                                        purchase.getPrice()))
                        .collect(Collectors.toList());
                // make a transactionDetail object
                transactionDetail = new TransactionDetail(
                        transaction_id,
                        customer,
                        rs.getString("credit_card"),
                        itemList,
                        rs.getDouble("amount"),
                        rs.getDate("purchase_date"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close connection
            DbUtil.close(rs);
            DbUtil.close(preparedStatement);
            DbUtil.close(connection);
        }
        return transactionDetail;
    }

    /**
     * Insert new transaction
     * @param name - customer name in question
     * @param address - address of customer
     * @param credit_card - card being used
     * @param cart - list of selected items
     *
     * @return boolean - true if success, false if not
     */
    public boolean postTransaction(String name, String address, String credit_card,
                                   List<SelectItem> cart) throws SQLException {
        if (cart.size() < 1) {
            System.out.println("Cart is empty!");
            return false;
        }
        
        int customer_id = customerDAO.postCustomer(name, address);
        String query = "INSERT INTO transactionTable (customer_id, " +
                "credit_card, amount, purchase_date) VALUES (?,?,?,NOW())";

        ResultSet rs = null;
        boolean success = true;
        double amount = 0;
        for (SelectItem item: cart) {
            amount += item.getTotalPrice();
        }
        try {
            // Get connection
            connection = ConnectionFactory.getConnection();
            preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            connection.setAutoCommit(false);

            preparedStatement.setInt(1, customer_id);
            preparedStatement.setString(2, credit_card);
            preparedStatement.setDouble(3, amount);

            // Execute query
            int rows = preparedStatement.executeUpdate();
            if (rows == 0) {
                throw new SQLException("Row affect return 0, insert fail");
            }

            // Get last insert id
            rs = preparedStatement.getGeneratedKeys();

            if (rs.next()) {
                int transaction_id = rs.getInt(1);
                connection.commit();
                // Add purchases to purchaseTable
                purchaseDAO.postPurchases(transaction_id, cart);      
            } else {
                throw new SQLException ("No value for return insert id");
            }

        } catch (SQLException e) {
            System.out.println("Error post transaction");
            e.printStackTrace();
            success = false;

            // Roll back to previous state
            if(connection != null)
                connection.rollback();
        } finally {
            // Close connection
            DbUtil.close(rs);
            DbUtil.close(preparedStatement);
            DbUtil.close(connection);
        }

        return success;
    }

    /**
     * Get the object Transaction from result set
     * @param rs - result set
     *
     * @return Transaction object
     */
    private Transaction transactionMapper (ResultSet rs) throws SQLException {
        return new Transaction(
                rs.getInt("id"),
                rs.getInt("customer_id"),
                rs.getString("credit_card"),
                rs.getDouble("amount"),
                rs.getDate("Transaction_date")
        );
    }
}
