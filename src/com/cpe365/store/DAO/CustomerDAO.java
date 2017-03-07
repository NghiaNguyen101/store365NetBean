package com.cpe365.store.DAO;

/**
 * Created by nnguy101 on 2/27/17.
 */
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;

import com.cpe365.store.Data.Customer;
import com.cpe365.store.DB.ConnectionFactory;
import com.cpe365.store.DB.DbUtil;

public class CustomerDAO {
    private Connection connection;
    private Statement statement;
    private PreparedStatement preparedStatement;

    // Customer Dao
    public CustomerDAO() {}

    /**
     * Get customer by id
     * @param customer_id
     *
     * @return customer object
     */
    public Customer getCustomer(int customer_id) {
        String query = "SELECT * FROM customerTable WHERE id=?";
        ResultSet rs = null;
        Customer customer = null;
        try {
            // Get connection and prepare stmt
            connection = ConnectionFactory.getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, customer_id);

            // Execute query
            rs = preparedStatement.executeQuery();
            if (rs.next()) {
                customer = customerMapper(rs);
            }
        } catch (SQLException e) {
            System.out.println("Error Get Item");
            e.printStackTrace();
        } finally {
            DbUtil.close(rs);
            DbUtil.close(preparedStatement);
            DbUtil.close(connection);
        }
        return customer;
    }

    /**
     * Post new customer
     * @param name
     * @param address
     *
     * @return id of customer, -1 if fail
     */
    public int postCustomer(String name, String address) throws  SQLException{
        String query = "INSERT INTO customerTable (name, address) VALUES (?,?)";
        boolean success = true;
        int id = -1;
        ResultSet rs = null;

        try {
            // Get connection and prepare statement
            connection = ConnectionFactory.getConnection();
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, address);

            // Execute query
            preparedStatement.executeUpdate();
            rs = preparedStatement.getGeneratedKeys();
            if (rs.next()) {
                id = rs.getInt(1);
                connection.commit();
            } else {
                throw new SQLException("Can't retrieve last insert id");
            }

        } catch (SQLException e) {
            System.out.println("Error posting customer");
            e.printStackTrace();
            if (connection != null)
                connection.rollback();
            success = false;
        } finally {
            // Close connection
            DbUtil.close(preparedStatement);
            DbUtil.close(connection);
        }
        return id;
    }

    /**
     * Put customer, check customer exist first
     * @param id
     * @param name
     * @param address
     *
     * @return boolean - true if success, false otherwise
     */
    public boolean putCustomer(int id, String name, String address) throws
            SQLException{
        String query_check = "SELECT COUNT(1) FROM customerTable WHERE id=?";
        String query_put = "UPDATE customerTable SET name=?, address=? " +
                "WHERE id=?";
        boolean success = true;
        ResultSet rs = null;

        try {
            // Get connection and prepare statement
            connection = ConnectionFactory.getConnection();
            preparedStatement = connection.prepareStatement(query_check);
            preparedStatement.setInt(1, id);

            // Execute query_check
            rs = preparedStatement.executeQuery();

            if (rs.next() && rs.getInt(1) == 0) {
                connection.setAutoCommit(false);
                preparedStatement = connection.prepareStatement(query_put);
                preparedStatement.setString(1, name);
                preparedStatement.setString(2, address);

                // Execute query_put
                preparedStatement.executeUpdate();
                connection.commit();
            } else {
                System.out.println("Customer not exists");
                success = false;
            }

        } catch (SQLException e) {
            System.out.println("Error posting customer");
            e.printStackTrace();
            if (connection != null)
                connection.rollback();
            success = false;
        } finally {
            // Close connection
            DbUtil.close(rs);
            DbUtil.close(preparedStatement);
            DbUtil.close(connection);
        }
        return success;
    }

    /**
     * Map the result set to customer object
     * @param rs - result set contain customer info from db
     *
     * @return customer object
     */
    private Customer customerMapper(ResultSet rs) throws  SQLException {
        return new Customer(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getString("address")
        );
    }
}
