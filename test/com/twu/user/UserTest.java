package com.twu.user;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.*;

public class UserTest {
    private User user;

    @Before
    public void setUp(){
        user = new User("Smithy", "sm@t.com", "Middle of nowhere",
                "73734435", "123-4567", "test123", UserType.CUSTOMER);
    }

    @Test
    public void shouldReturnUserType(){
        UserType returnedUserType = user.getUserType();

        assertEquals(UserType.CUSTOMER, returnedUserType);
    }

    @Test
    public void shouldReturnUserName(){
        String returnedUserName = user.getName();

        assertEquals("Smithy", returnedUserName);
    }

    @Test
    public void shouldReturnLibraryNumber(){
        String returnedLibraryNumber = user.getLibraryNumber();

        assertEquals("123-4567", returnedLibraryNumber);
    }

    @Test
    public void shouldReturnFalseForNoCorrespondingPassword(){

        assertFalse(user.isValidPassword(user.getLibraryNumber(), "thisisnotthepassword"));
    }

    @Test
    public void shouldReturnTrueForCorrespondingPassword(){

        assertTrue(user.isValidPassword(user.getLibraryNumber(), "test123"));
    }
}
