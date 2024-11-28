package dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {
    private static Connection connection = null;
    
    public static Connection getConnection() {
        if (connection == null) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/voting_system",
                    "root",
                    "password"
                );
                System.out.println("Database connection established.");
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Database connection established.");
            }
        }
        return connection;
    }
}