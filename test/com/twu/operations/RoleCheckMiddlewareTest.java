package com.twu.operations;

import com.twu.server.Server;
import com.twu.user.User;
import com.twu.user.UserType;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class RoleCheckMiddlewareTest {
    @Mock
    private Server mockServer;

    @Mock
    private User mockUser;

    @InjectMocks
    private RoleCheckMiddleware roleCheckMiddleware;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldReturnTrueIfUserIsLibrarian(){
        when(mockUser.getUserType()).thenReturn(UserType.LIBRARIAN);
        when(mockServer.getUserByLibraryNumber(anyString())).thenReturn(mockUser);

        boolean isUserLibrarian = roleCheckMiddleware.check(anyString(), "testpass");

        verify(mockServer, times(1)).getUserByLibraryNumber(anyString());

        assertTrue(isUserLibrarian);

    }

    @Test
    public void shouldReturnFalseIfUserIsNotLibrarian(){
        when(mockUser.getUserType()).thenReturn(UserType.CUSTOMER);
        when(mockServer.getUserByLibraryNumber(anyString())).thenReturn(mockUser);

        boolean isUserLibrarian = roleCheckMiddleware.check(anyString(), "testpass");

        verify(mockServer, times(1)).getUserByLibraryNumber(anyString());

        assertTrue(isUserLibrarian);

    }
}
