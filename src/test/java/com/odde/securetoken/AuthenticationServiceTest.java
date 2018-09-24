package com.odde.securetoken;

import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AuthenticationServiceTest {

    Profile stubProfile = mock(Profile.class);
    MyToken stubToken = mock(MyToken.class);
    AuthenticationService target = new AuthenticationService(stubProfile, stubToken);

    @Test
    public void is_valid_test() {
        givenProfile("joey", "91");
        givenToken("000000");

        shouldBeValid("joey", "91000000");
    }

    private void shouldBeValid(String account, String password) {
        assertTrue(target.isValid(account, password));
    }

    private void givenToken(String random) {
        when(stubToken.getRandom(anyString())).thenReturn(random);
    }

    private void givenProfile(String account, String password) {
        when(stubProfile.getPassword(account)).thenReturn(password);
    }

}

