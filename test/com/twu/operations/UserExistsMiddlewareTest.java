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

public class UserExistsMiddlewareTest {

    @Mock
    private Server mockServer;

    @InjectMocks
    private UserExistsMiddleware userExistsMiddleware;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldReturnTrueIfUserExists(){
        when(mockServer.hasUser(anyString())).thenReturn(true);

        boolean userExists = userExistsMiddleware.check(anyString(), "anyPassword");

        verify(mockServer, times(1)).hasUser(anyString());

        assertTrue(userExists);

    }

    @Test
    public void shouldReturnFalseIfUserDoesNotExists(){
        when(mockServer.hasUser(anyString())).thenReturn(false);

        boolean userExists = userExistsMiddleware.check(anyString(), "anyPassword");

        verify(mockServer, times(1)).hasUser(anyString());

        //is this because there is no checkNext() defined?
        assertFalse(userExists);

    }

}
