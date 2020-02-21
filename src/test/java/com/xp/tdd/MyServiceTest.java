package com.xp.tdd;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class MyServiceTest {

    //1. Dummy
    private class DummyAuthorizer implements Authorizer {
        public Boolean authorize(String username, String password) {
            return null;
        }
    }

    @Test
    public void testDummy() {
        Authorizer dummy = new DummyAuthorizer();
        //Dummy object or Just null?
        assertTrue(new MyService(dummy).normalAction());
        assertTrue(new MyService(null).normalAction());
    }


    //2. Stub
    private class AlwaysTrueAuthorizer implements Authorizer {
        public Boolean authorize(String username, String password) {
            return true;
        }
    }

    @Test
    public void testStub() {
        Authorizer stub = new AlwaysTrueAuthorizer();
        assertTrue(new MyService(stub).criticalAction("someUser","somePassword"));
    }

    //3. Spy
    private class AlwaysTrueAuthorizerSpy implements Authorizer {
        public boolean authorizeInvoked = false;
        public Boolean authorize(String username, String password) {
            authorizeInvoked=true;
            return true;
        }
    }

    @Test
    public void testSpy() {
        AlwaysTrueAuthorizerSpy spy = new AlwaysTrueAuthorizerSpy();
        //state verification
        assertTrue(new MyService(spy).criticalAction("someUser","somePassword"));
        //behavior verification
        assertTrue(spy.authorizeInvoked);

    }


    //4. Mock
    @Test
    public void testMock() {
        Authorizer authorizerMock = mock(Authorizer.class);
        //prepare
        when(authorizerMock.authorize("testuser","testpassword")).thenReturn(true);
        //state verification
        assertTrue(new MyService(authorizerMock).criticalAction("testuser","testpassword"));

        //behavior verification
        verify(authorizerMock).authorize("testuser","testpassword");


    }

    //5. Fake

}
