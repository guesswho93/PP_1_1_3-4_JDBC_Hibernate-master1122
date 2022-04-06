package jm.task.core.jdbc.util;

import com.mysql.cj.jdbc.Driver;

import java.io.Closeable;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Util {
    public static Connection getConnection() throws SQLException {

        Connection connection = null;

        try {
            //Driver driver = new com.mysql.jdbc.Driver();
            //DriverManager.registerDriver(driver);
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/usersDB", "root", "Snf791993");

        } catch (SQLException e) {
            e.printStackTrace();
        }
        //connection.close();

        return connection;

    }
}
