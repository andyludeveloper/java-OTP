package com.odde.securetoken;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AuthenticationServiceTest {
    private ProfileDao _profile = mock(ProfileDao.class);
    private RsaTokenDao _token = mock(RsaTokenDao.class);
    private AuthenticationService _target = new AuthenticationService(_profile, _token);

    @Test
    public void is_valid() {
        givenPassword("andy", "0000");

        givenToken("1111");

        shouldBeValid("andy", "00001111");
    }

    private void shouldBeValid(String account, String password) {
        boolean actual = _target.isValid(account, password);
        assertTrue(actual);
    }

    @Test
    public void is_invalid() {
        givenPassword("andy", "0000");

        givenToken("1111");

        shouldBeInvalid("andy", "wrong answer");
    }

    private void shouldBeInvalid(String account, String password) {
        _target = new AuthenticationService(_profile, _token);
        boolean actual = _target.isValid(account, password);
        assertFalse(actual);
    }

    private void givenToken(String token) {

        when(_token.getRandom(anyString())).thenReturn(token);
    }

    private void givenPassword(String andy, String password) {

        when(_profile.getPassword(andy)).thenReturn(password);
    }
}

