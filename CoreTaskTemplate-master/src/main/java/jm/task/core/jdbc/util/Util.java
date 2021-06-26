package jm.task.core.jdbc.util;

import com.mysql.cj.jdbc.Driver;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Util {

    private static final String url = "jdbc:mysql://localhost:3306/mysql";
    private static final String user = "root";
    private static final String pass = "root";

    public static Connection getConnection() {
        Connection connection = null;

        try {
            DriverManager.registerDriver(new Driver());
            connection = DriverManager.getConnection(url, user, pass);
//            System.out.println("Connection established");
        } catch (SQLException e) {
            System.out.println("driver hasn't been found or connection failed");
            e.printStackTrace();
        }
        return connection;
    }
}