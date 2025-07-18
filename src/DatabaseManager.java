package com.luismonserratt.moviemanager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Provides a method to establish a connection to the MySQL database
 * used by the Movie Manager application.
 */
public class DatabaseManager {
    // JDBC URL, username and password
    private static final String URL = "jdbc:mysql://localhost:3306/movie_manager";
    private static final String USER = "root";
    private static final String PASSWORD = "Luis123!";

    /**
     * Establishes and returns a new connection to the MySQL database.
     *
     * @return a Connection object to the database
     * @throws SQLException if a database access error occurs
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
