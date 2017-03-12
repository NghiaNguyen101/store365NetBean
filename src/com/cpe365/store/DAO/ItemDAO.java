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

import com.cpe365.store.Data.Item;
import com.cpe365.store.DB.ConnectionFactory;
import com.cpe365.store.DB.DbUtil;

public class ItemDAO {
    private Connection connection;
    private Statement statement;
    private PreparedStatement preparedStatement;

    // Constructor
    public ItemDAO() {}

    /**
     * Get item based on id
     * @param item_id - id of item
     *
     * @return item object
     */
    public Item getItem(int item_id) throws SQLException {
        String query = "SELECT * FROM itemTable WHERE id=?";
        ResultSet rs = null;
        Item item = null;
        try {
            // Get connection and prepare stmt
            connection = ConnectionFactory.getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, item_id);

            // Execute query
            rs = preparedStatement.executeQuery();
            if (rs.next()) {
                item = itemMapper(rs);
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
        return item;
    }

    /**
     * Get all items
     *
     * @return itemList - list of items
     */
    public List<Item> getAllItems() throws  SQLException {
        String query = "SELECT * FROM itemTable WHERE discontinued=0 AND stock>0";
        ResultSet rs = null;
        List<Item> itemList = new LinkedList<Item>();

        try {
            // Get connection and do statement
            connection = ConnectionFactory.getConnection();
            statement = connection.createStatement();

            // Execute query
            rs = statement.executeQuery(query);

            // Add items to list
            while(rs.next()) {
                itemList.add(itemMapper(rs));
            }
        } catch (SQLException e) {
            System.out.println("Error select all itemTable");
            e.printStackTrace();
        } finally {
            // Close connection
            DbUtil.close(rs);
            DbUtil.close(preparedStatement);
            DbUtil.close(connection);
        }

        return itemList;
    }

    /**
     * Search for items
     * @param key - key use for searching globally
     *
     * @return itemList - list of items match the key
     */
    public List<Item> searchItems(String key) throws SQLException {
        String query = "SELECT * FROM itemTable WHERE name LIKE ? AND discontinued = 0 " +
                       "AND stock > 0";
        ResultSet rs = null;
        List<Item> itemList = new LinkedList<Item>();
        key = key.replace("!", "!!")
                .replace("%", "!%")
                .replace("_", "!_")
                .replace("[", "![");

        try {
            // Get connection and do statement
            connection = ConnectionFactory.getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, "%" + key + "%");

            // Execute query
            rs = preparedStatement.executeQuery();

            // Add items to list
            while(rs.next()) {
                itemList.add(itemMapper(rs));
            }
        } catch (SQLException e) {
            System.out.println("Error select from itemTable");
            e.printStackTrace();
        } finally {
            // Close connection
            DbUtil.close(rs);
            DbUtil.close(preparedStatement);
            DbUtil.close(connection);
        }

        return itemList;
    }

    /**
     * Insert item into database
     * @param name
     * @param price
     * @param description
     * @param manufacturer
     * @param stock
     *
     * @return boolean - true if succeed, false if not
     */
    public boolean postItem(String name, double price, String description,
                            String manufacturer, int stock) throws SQLException{
        String query = "INSERT INTO itemTable (name, price, description, " +
                "manufacturer, stock) VALUES (?,?,?,?,?)";
        boolean success = true;

        try {
            // Get Connection
            connection = ConnectionFactory.getConnection();
            preparedStatement = connection.prepareStatement(query);
            connection.setAutoCommit(false);

            // Prepare
            preparedStatement.setString(1, name);
            preparedStatement.setDouble(2, price);
            preparedStatement.setString(3, description);
            preparedStatement.setString(4, manufacturer);
            preparedStatement.setInt(5, stock);

            // Execute
            preparedStatement.executeUpdate();
            connection.commit();

        } catch (SQLException e) {
            System.out.println("Error insert item");
            e.printStackTrace();
            success = false;

            // Roll back
            if (connection != null)
                connection.rollback();

        } finally {
            // Close connection
            DbUtil.close(preparedStatement);
            DbUtil.close(connection);
        }
        return success;
    }

    /**
     * Insert items into database
     * @param itemList - List of items
     *
     * @return boolean - true if succeed, false if not
     */
    public boolean postMultipleItems(List<Item> itemList) throws  SQLException{
        String query = "INSERT INTO itemTable (name, price, description, " +
                "manufacturer, stock) VALUES (?,?,?,?,?)";
        boolean success = true;

        try {
            // Get Connection
            connection = ConnectionFactory.getConnection();
            preparedStatement = connection.prepareStatement(query);
            connection.setAutoCommit(false);

            // Prepare
            for (Item item: itemList) {
                preparedStatement.setString(1, item.getName());
                preparedStatement.setDouble(2, item.getPrice());
                preparedStatement.setString(3, item.getDescription());
                preparedStatement.setString(4, item.getManufacturer());
                preparedStatement.setInt(5, item.getStock());
                preparedStatement.addBatch();
            }

            // Execute
            preparedStatement.executeBatch();
            connection.commit();

        } catch (SQLException e) {
            System.out.println("Error insert itemss");
            e.printStackTrace();
            success = false;

            // Roll back
            if (connection != null)
                connection.rollback();

        } finally {
            // Close connection
            DbUtil.close(preparedStatement);
            DbUtil.close(connection);
        }

        return success;
    }

    /**
     * Put item, update item in table
     * @param id - id of item in question
     * @param name
     * @param price
     * @param description
     * @param manufacturer
     * @param stock
     * @param discontinued
     *
     * @return boolean - true if success, false otherwise
     */
    public boolean putItem(int id, String name, double price, String
            description, String manufacturer, int stock, int discontinued)
            throws SQLException {
        String query = "UPDATE itemTable SET name=?, price=?, " +
                "description=?, manufacturer=?, stock=?, discontinued=? " +
                "WHERE item_id=?";
        boolean success = true;

        try {
            // Get connection
            connection = ConnectionFactory.getConnection();
            preparedStatement = connection.prepareStatement(query);
            connection.setAutoCommit(false);
            
            // Prepare
            preparedStatement.setString(1, name);
            preparedStatement.setDouble(2, price);
            preparedStatement.setString(3, description);
            preparedStatement.setString(4, manufacturer);
            preparedStatement.setInt(5, stock);
            preparedStatement.setInt(6, discontinued);
            preparedStatement.setInt(7, id);

            // Execute
            preparedStatement.executeUpdate();
            connection.commit();

        } catch (SQLException e) {
            System.out.println("Error update item");
            e.printStackTrace();
            success = false;
            if (connection != null)
                connection.rollback();

        } finally {
            // Close connection
            DbUtil.close(preparedStatement);
            DbUtil.close(connection);
        }

        return success;
    }

    /**
     * Put items, update multiple items
     * @param itemList - list of items
     *
     * @return boolean - true if succeed, false otherwise
     */
    public boolean putMultipleItems(List<Item> itemList) throws SQLException {
        if (itemList.size() < 1) {
            System.out.println("itemList is empty - put");
            return false;
        }

        String query = "UPDATE itemTable SET name=?, price=?, " +
                "description=?, manufacturer=?, stock=?, discontinued=? " +
                "WHERE item_id=?";
        boolean success = true;

        try {
            // Get connection
            connection = ConnectionFactory.getConnection();
            preparedStatement = connection.prepareStatement(query);
            connection.setAutoCommit(false);
            
            // Prepare
            for (Item item : itemList) {
                preparedStatement.setString(1, item.getName());
                preparedStatement.setDouble(2, item.getPrice());
                preparedStatement.setString(3, item.getDescription());
                preparedStatement.setString(4, item.getManufacturer());
                preparedStatement.setInt(5, item.getStock());
                preparedStatement.setInt(6, (item.isDiscontinued() ? 1 : 0));
                preparedStatement.setInt(7, item.getId());
                preparedStatement.addBatch();
            }

            // Execute
            preparedStatement.executeBatch();
            connection.commit();

        } catch (SQLException e) {
            System.out.println("Error update item");
            e.printStackTrace();
            success = false;
            if (connection != null)
                connection.rollback();

        } finally {
            // Close connection
            DbUtil.close(preparedStatement);
            DbUtil.close(connection);
        }

        return success;
    }

    /**
     * Delete Specific item base on id
     * @param item_id
     * 
     * @return true or false for succeed or not
     */
    public boolean deleteItem(int item_id) throws SQLException {
        String query = "UPDATE itemTable SET discontinued=1 WHERE id=?";
        boolean success = true;
        
        try {
            connection = ConnectionFactory.getConnection();
            preparedStatement = connection.prepareStatement(query);
            connection.setAutoCommit(false);
            
            preparedStatement.setInt(1, item_id);
            int row = preparedStatement.executeUpdate();
            
            if(row == 0) {
              throw new SQLException("No item found for delete");  
            } 
            connection.commit();
        } catch (SQLException e) {
           System.out.println("Error delete item");
            e.printStackTrace();
            success = false;
            if (connection != null)
                connection.rollback();
        } finally {
            // Close connection
            DbUtil.close(preparedStatement);
            DbUtil.close(connection);
        }
        return success;
    }
    
    /**
     * Get item object from result set from mysql
     * @param rs - result set
     * @return Item object
     */
    private Item itemMapper(ResultSet rs) throws SQLException {
        return new Item(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getDouble("price"),
                rs.getString("description"),
                rs.getString("manufacturer"),
                rs.getInt("stock"),
                rs.getBoolean("discontinued")
        );
    }
}
