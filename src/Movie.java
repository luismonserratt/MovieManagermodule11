package com.luismonserratt.moviemanager;

/**
 * Represents a movie with details such as title, genre, release year,
 * rating, duration, and whether it has been watched.
 *
 * This class is part of the data model for the Movie Manager application.
 */
public class Movie {
    private String title;
    private String genre;
    private int year;
    private double rating;
    private int duration;
    private boolean watched;

    /**
     * Constructs a new Movie object with the specified attributes.
     *
     * @param title   the title of the movie
     * @param genre   the genre of the movie (e.g., Action, Drama)
     * @param year    the release year of the movie
     * @param rating  the movie's rating (e.g., out of 5.0)
     * @param duration the length of the movie in minutes
     * @param watched true if the movie has been watched, false otherwise
     */
    public Movie(String title, String genre, int year, double rating, int duration, boolean watched) {
        this.title = title;
        this.genre = genre;
        this.year = year;
        this.rating = rating;
        this.duration = duration;
        this.watched = watched;
    }

    // --- GETTERS ---

    /**
     * Gets the title of the movie.
     * @return the movie title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Gets the genre of the movie.
     * @return the genre
     */
    public String getGenre() {
        return genre;
    }

    /**
     * Gets the release year of the movie.
     * @return the release year
     */
    public int getYear() {
        return year;
    }

    /**
     * Gets the rating of the movie.
     * @return the rating (e.g., 4.5)
     */
    public double getRating() {
        return rating;
    }

    /**
     * Gets the duration of the movie in minutes.
     * @return the duration
     */
    public int getDuration() {
        return duration;
    }

    /**
     * Checks if the movie has been watched.
     * @return true if watched, false otherwise
     */
    public boolean isWatched() {
        return watched;
    }

    // --- SETTERS ---

    /**
     * Sets the genre of the movie.
     * @param genre the new genre
     */
    public void setGenre(String genre) {
        this.genre = genre;
    }

    /**
     * Sets the release year of the movie.
     * @param year the new release year
     */
    public void setYear(int year) {
        this.year = year;
    }

    /**
     * Sets the rating of the movie.
     * @param rating the new rating
     */
    public void setRating(double rating) {
        this.rating = rating;
    }

    /**
     * Sets the duration of the movie in minutes.
     * @param duration the new duration
     */
    public void setDuration(int duration) {
        this.duration = duration;
    }

    /**
     * Sets the watched status of the movie.
     * @param watched true if the movie has been watched
     */
    public void setWatched(boolean watched) {
        this.watched = watched;
    }

    /**
     * Returns a string representation of the movie with key details.
     *
     * @return a formatted string showing title, year, genre, rating, duration, and watched status
     */
    @Override
    public String toString() {
        return title + " (" + year + ") - " + genre + ", " + rating + "★, " + duration + " min - " +
                (watched ? "Watched" : "Unwatched");
    }
}
