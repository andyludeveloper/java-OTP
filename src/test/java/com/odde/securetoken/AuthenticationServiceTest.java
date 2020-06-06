package com.odde.securetoken;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class AuthenticationServiceTest {
    private final ProfileDao _profile = mock(ProfileDao.class);
    private final RsaTokenDao _token = mock(RsaTokenDao.class);
    private final ILogger _logger = mock(ILogger.class);
    private final AuthenticationService _target = new AuthenticationService(_profile, _token, _logger);

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
        shouldBeInvalid("andy");
    }

    private void shouldBeInvalid(String account) {
        boolean actual = _target.isValid(account, "wrong answer");
        assertFalse(actual);
    }

    @Test
    public void should_log_account_when_invalid() {
        whenInvalid();
        shouldSaveLog("andy", "login failed");
    }

    private void shouldSaveLog(String account, String state) {
        verify(_logger, times(1)).save(argThat(argument -> argument.contains(account)
                &&argument.contains(state)));
    }

    private void whenInvalid() {
        givenPassword("andy", "0000");
        givenToken("1111");
        _target.isValid("andy", "wrong answer");
    }

    private void givenToken(String token) {

        when(_token.getRandom(anyString())).thenReturn(token);
    }

    private void givenPassword(String andy, String password) {

        when(_profile.getPassword(andy)).thenReturn(password);
    }
}

