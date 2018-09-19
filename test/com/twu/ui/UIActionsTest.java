package com.twu.ui;

import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.io.PrintStream;

import static org.mockito.Matchers.startsWith;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import static junit.framework.TestCase.*;

public class UIActionsTest {
    UIActions uiActions;
    private static InputStream inputStream;
    private PrintStream printStream;

    @Before
    public void setUp(){
        printStream = mock(PrintStream.class);
        inputStream = mock(InputStream.class);
        uiActions = new UIActions(inputStream, printStream);
    }

    @Test
    public void shouldPrintString(){

        uiActions.print("Start line");
        verify(printStream).println(startsWith("Start"));
    }

    @Test
    public void shouldReturnString(){
        UIActions mockUIActions = mock(UIActions.class);

        when(mockUIActions.readUserInputFromConsole()).thenReturn("Some String");

        assertEquals(mockUIActions.readUserInputFromConsole(), "Some String");
    }


}

