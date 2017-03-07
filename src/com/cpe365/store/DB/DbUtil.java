package com.cpe365.store.DB;

/**
 * Created by nnguy101 on 2/27/17.
 * Source: http://theopentutorials.com/tutorials/java/jdbc/jdbc-examples-introduction/
 */

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DbUtil {
    public static void close(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                /*log or print or ignore*/
            }
        }
    }

    public static void close(Statement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                /*log or print or ignore*/
            }
        }
    }

    public static void close(ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                /*log or print or ignore*/
            }
        }
    }
}
