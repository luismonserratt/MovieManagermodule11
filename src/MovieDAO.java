package com.luismonserratt.moviemanager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Data Access Object (DAO) for performing CRUD operations on the movies table.
 */
public class MovieDAO {

    /**
     * Insert a new movie into the database.
     */
    public boolean create(Movie movie) {
        String sql = "INSERT INTO movies (title, genre, year, rating, duration, watched) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, movie.getTitle());
            stmt.setString(2, movie.getGenre());
            stmt.setInt(3, movie.getYear());
            stmt.setDouble(4, movie.getRating());
            stmt.setInt(5, movie.getDuration());
            stmt.setBoolean(6, movie.isWatched());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Retrieve all movies from the database.
     */
    public List<Movie> readAll() {
        List<Movie> movies = new ArrayList<>();
        String sql = "SELECT * FROM movies";

        try (Connection conn = DatabaseManager.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                movies.add(new Movie(
                        rs.getString("title"),
                        rs.getString("genre"),
                        rs.getInt("year"),
                        rs.getDouble("rating"),
                        rs.getInt("duration"),
                        rs.getBoolean("watched")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return movies;
    }

    /**
     * Update an existing movie by title.
     */
    public boolean update(Movie movie) {
        String sql = "UPDATE movies SET genre=?, year=?, rating=?, duration=?, watched=? WHERE title=?";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, movie.getGenre());
            stmt.setInt(2, movie.getYear());
            stmt.setDouble(3, movie.getRating());
            stmt.setInt(4, movie.getDuration());
            stmt.setBoolean(5, movie.isWatched());
            stmt.setString(6, movie.getTitle());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Delete a movie by title.
     */
    public boolean delete(String title) {
        String sql = "DELETE FROM movies WHERE title=?";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, title);
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Calculate and return the average duration of all movies.
     */
    public double getAverageDuration() {
        String sql = "SELECT AVG(duration) FROM movies";
        try (Connection conn = DatabaseManager.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            return rs.next() ? rs.getDouble(1) : 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }
}
