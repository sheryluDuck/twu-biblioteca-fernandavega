package com.twu.operations;

import com.twu.server.Server;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class UserPasswordCheckMiddlewareTest {
    @Mock
    private Server mockServer;

    @InjectMocks
    private UserPasswordCheckMiddleware userPasswordCheckMiddleware;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }
    @Test
    public void shouldReturnTrueIfPasswordMatches(){
        when(mockServer.isValidPassword(anyString(), anyString())).thenReturn(true);

        boolean correctPassword = userPasswordCheckMiddleware.check(anyString(), anyString());

        verify(mockServer, times(1)).isValidPassword(anyString(), anyString());

        assertTrue(correctPassword);
    }

    @Test
    public void shouldReturnFalseIfPasswordDoesNotMatch(){
        when(mockServer.isValidPassword(anyString(), anyString())).thenReturn(false);

        boolean incorrectPassword = userPasswordCheckMiddleware.check(anyString(), anyString());

        verify(mockServer, times(1)).isValidPassword(anyString(), anyString());

        assertFalse(incorrectPassword);
    }
}
