package com.twu.server;

import com.twu.library.Library;
import com.twu.ui.UIActions;
import com.twu.user.User;
import com.twu.user.UserType;
import org.junit.Before;
import org.junit.Test;

import java.io.PrintStream;

import static junit.framework.TestCase.*;

public class ServerTest {
    private Server server;
    private UIActions uiActions;
    private Library library;
    private User userInServer;
    private User userNotInServer;

    @Before
    public void setUp(){
        uiActions = new UIActions(System.in, new PrintStream(System.out));
        server = new Server(library, uiActions);
        userInServer = new User("Test", "t@r.com", "fake address", "3243344343",
                "234-6778", "test", UserType.CUSTOMER);
        userNotInServer = new User("Test2", "t2@r.com", "fake address", "4324535555",
                "245-7875", "test2", UserType.CUSTOMER);
        server.register(userInServer);
    }

    @Test
    public void shouldReturnTrueIfUserExists(){
        assertTrue(server.hasUser(userInServer.getLibraryNumber()));
    }

    @Test
    public void shouldReturnFalseIfUserDoesNotExists(){
        assertFalse(server.hasUser(userNotInServer.getLibraryNumber()));
    }

    @Test
    public void shouldReturnRegisteredUser(){
        User returnedUser = server.getUserByLibraryNumber(userInServer.getLibraryNumber());

        assertEquals(userInServer, returnedUser);
    }

    @Test
    public void shouldReturnNullForNonRegisteredUser(){
        assertNull(server.getUserByLibraryNumber(userNotInServer.getLibraryNumber()));
    }

    @Test
    public void shouldReturnTrueForValidPasswordForUserInServer(){
        assertTrue(server.isValidPassword(userInServer.getLibraryNumber(), "test"));
    }

    @Test
    public void shouldReturnFalseForInvalidPasswordForUserInServer(){
        assertFalse(server.isValidPassword(userInServer.getLibraryNumber(), "thisIsNotThePassword"));
    }

    
}
