package com.odde.securetoken;

import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AuthenticationServiceTest {

    @Test
    public void is_valid_test() {
        Profile stubProfile = mock(Profile.class);
        when(stubProfile.getPassword("joey")).thenReturn("91");

        MyToken stubToken = mock(MyToken.class);
        when(stubToken.getRandom(anyString())).thenReturn("000000");

        AuthenticationService target = new AuthenticationService(stubProfile, stubToken);
//        AuthenticationService target = new AuthenticationService(new StubProfile(), new StubMyToken());

        boolean actual = target.isValid("joey", "91000000");

        assertTrue(actual);
    }
}

