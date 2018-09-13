package com.twu.movie;

import com.twu.libraryItem.ItemAvailability;
import com.twu.libraryItem.LibraryItem;

import java.util.Objects;

public class Movie implements LibraryItem {

    private String title;
    private String director;
    private int year;
    private int rating;
    private ItemAvailability movieAvailabilityStatus;

    public Movie(String title, String director, int year, int rating, ItemAvailability movieAvailabilityStatus) {
        this.title = title;
        this.director = director;
        this.year = year;
        this.rating = rating;
        this.movieAvailabilityStatus = movieAvailabilityStatus;
    }
    @Override
    public int hashCode() {
        return Objects.hash(title, director, year, movieAvailabilityStatus);
    }
    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (!(obj instanceof Movie)) {
            return false;
        }
        Movie movie = (Movie) obj;
        return this.title.toLowerCase().equals(movie.title.toLowerCase())&&
                this.director.toLowerCase().equals(movie.director.toLowerCase())&&
                this.year == movie.year &&
                this.movieAvailabilityStatus.equals(movie.movieAvailabilityStatus);
    }

    @Override
    public ItemAvailability getStatus() {
        return this.movieAvailabilityStatus;
    }

    @Override
    public void setStatus(ItemAvailability itemAvailability) {
        this.movieAvailabilityStatus = itemAvailability;
    }

    @Override
    public boolean isAvailable() {
        return this.movieAvailabilityStatus.equals(ItemAvailability.AVAILABLE);
    }


}
