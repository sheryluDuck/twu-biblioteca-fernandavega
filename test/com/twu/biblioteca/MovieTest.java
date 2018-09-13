package com.twu.biblioteca;
import com.twu.libraryItem.ItemAvailability;
import com.twu.movie.Movie;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.*;
public class MovieTest {
    Movie availableMovie;
    Movie borrowedMovie;
    @Before
    public void setUp(){
        availableMovie = new Movie("Back to the future I", "Steven Spielberg", 1985, 10, ItemAvailability.AVAILABLE);
        borrowedMovie= new Movie("Saturday Night Fever", "John Badham", 1977, 9, ItemAvailability.RESERVED);
    }

    @Test
    public void shouldReturnMovieIsAvailableWhenStatusIsAvailable()
    {
        assertTrue(availableMovie.isAvailable());
    }
    @Test
    public void shouldReturnMovieIsNotAvailableWhenStatusIsDifferentFromAvailable(){
        assertFalse(borrowedMovie.isAvailable());
    }
    @Test
    public void shouldChangeStatusOfMovie(){
        availableMovie.setStatus(ItemAvailability.RESERVED);

        assertEquals(ItemAvailability.RESERVED, availableMovie.getStatus());
    }

    @Test
    public void shouldReturnMovieStatus(){

        assertEquals(ItemAvailability.RESERVED, borrowedMovie.getStatus());
    }
}
