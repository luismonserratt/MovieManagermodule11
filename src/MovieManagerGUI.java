package com.luismonserratt.moviemanager;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * Graphical User Interface (GUI) for managing movies stored in a MySQL database.
 *
 * Provides buttons to connect to the database, display all movies, add a new movie,
 * update an existing movie, delete a movie, and show the average duration.
 */
public class MovieManagerGUI extends JFrame {
    private MovieDAO dao;
    private JTextArea displayArea;

    /**
     * Constructs the MovieManagerGUI and sets up its components and layout.
     */
    public MovieManagerGUI() {
        dao = new MovieDAO();

        setTitle("Movie Manager (MySQL)");
        setSize(650, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        displayArea = new JTextArea();
        displayArea.setEditable(false);
        add(new JScrollPane(displayArea), BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new GridLayout(2, 4, 5, 5));
        String[] labels = {"Connect", "Display", "Add", "Update", "Delete", "Avg Duration", "Exit"};
        JButton[] buttons = new JButton[labels.length];

        for (int i = 0; i < labels.length; i++) {
            buttons[i] = new JButton(labels[i]);
            buttonPanel.add(buttons[i]);
        }

        add(buttonPanel, BorderLayout.SOUTH);

        buttons[0].addActionListener(e -> testConnection());
        buttons[1].addActionListener(e -> displayMovies());
        buttons[2].addActionListener(e -> addMovie());
        buttons[3].addActionListener(e -> updateMovie());
        buttons[4].addActionListener(e -> deleteMovie());
        buttons[5].addActionListener(e -> showAverageDuration());
        buttons[6].addActionListener(e -> System.exit(0));
    }

    /**
     * Tests the database connection and shows a message.
     */
    private void testConnection() {
        try {
            DatabaseManager.getConnection().close();
            JOptionPane.showMessageDialog(this, "✅ Connection successful!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "❌ Connection failed: " + e.getMessage());
        }
    }

    /**
     * Displays all movies from the database in the text area.
     */
    private void displayMovies() {
        List<Movie> movies = dao.readAll();
        StringBuilder sb = new StringBuilder();
        for (Movie m : movies) {
            sb.append(m).append("\n");
        }
        displayArea.setText(sb.toString());
    }

    /**
     * Adds a new movie to the database using user input.
     */
    private void addMovie() {
        try {
            String title = JOptionPane.showInputDialog("Title:");
            String genre = JOptionPane.showInputDialog("Genre:");
            int year = Integer.parseInt(JOptionPane.showInputDialog("Year:"));
            double rating = Double.parseDouble(JOptionPane.showInputDialog("Rating:"));
            int duration = Integer.parseInt(JOptionPane.showInputDialog("Duration:"));
            boolean watched = Boolean.parseBoolean(JOptionPane.showInputDialog("Watched? (true/false)"));

            Movie movie = new Movie(title, genre, year, rating, duration, watched);
            boolean success = dao.create(movie);
            JOptionPane.showMessageDialog(this, success ? "✅ Movie added." : "❌ Add failed.");
            displayMovies();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "⚠️ Invalid input.");
        }
    }

    /**
     * Updates an existing movie's information using user input.
     */
    private void updateMovie() {
        try {
            String title = JOptionPane.showInputDialog("Title to update:");
            String genre = JOptionPane.showInputDialog("New genre:");
            int year = Integer.parseInt(JOptionPane.showInputDialog("New year:"));
            double rating = Double.parseDouble(JOptionPane.showInputDialog("New rating:"));
            int duration = Integer.parseInt(JOptionPane.showInputDialog("New duration:"));
            boolean watched = Boolean.parseBoolean(JOptionPane.showInputDialog("Watched? (true/false)"));

            Movie movie = new Movie(title, genre, year, rating, duration, watched);
            boolean success = dao.update(movie);
            JOptionPane.showMessageDialog(this, success ? "✅ Movie updated." : "❌ Update failed.");
            displayMovies();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "⚠️ Invalid input.");
        }
    }

    /**
     * Deletes a movie from the database based on its title.
     */
    private void deleteMovie() {
        String title = JOptionPane.showInputDialog("Title to delete:");
        boolean success = dao.delete(title);
        JOptionPane.showMessageDialog(this, success ? "✅ Deleted!" : "❌ Not found.");
        displayMovies();
    }

    /**
     * Displays the average duration of all movies in a popup.
     */
    private void showAverageDuration() {
        double avg = dao.getAverageDuration();
        JOptionPane.showMessageDialog(this, "🎬 Average duration: " + String.format("%.2f", avg) + " minutes");
    }

    /**
     * The main method to launch the GUI application.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MovieManagerGUI().setVisible(true));
    }
}
