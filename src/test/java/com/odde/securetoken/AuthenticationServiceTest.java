package com.odde.securetoken;

import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AuthenticationServiceTest {

    @Test
    public void is_valid_test() {
        AuthenticationService target = new AuthenticationService(new FakeProfile(), new FakeToken());

        boolean actual = target.isValid("andy", "00001111");

        assertTrue(actual);
    }
    @Test
    public void is_valid_test2() {
        AuthenticationService target = new AuthenticationService(new FakeProfile2(), new FakeToken2());

        boolean actual = target.isValid("andy", "00001111");

        assertTrue(actual);
    }
    @Test
    public void is_valid_test3() {
        IProfile profile = mock(IProfile.class);
        when(profile.getPassword("andy")).thenReturn("0000");

        IToken token = mock(IToken.class);
        when(token.getRandom(anyString())).thenReturn("1111");

        AuthenticationService target = new AuthenticationService(profile, token);

        boolean actual = target.isValid("andy", "00001111");

        assertTrue(actual);
    }
    @Test
    public void is_valid_test4() {
        ProfileDao profile = mock(ProfileDao.class);
        when(profile.getPassword("andy")).thenReturn("0000");

        RsaTokenDao token = mock(RsaTokenDao.class);
        when(token.getRandom(anyString())).thenReturn("1111");

        AuthenticationService target = new AuthenticationService(profile, token);

        boolean actual = target.isValid("andy", "00001111");

        assertTrue(actual);
    }
}

 class FakeProfile implements IProfile{

    @Override
    public String getPassword(String account) {
        if(account.equals("andy"))
            return "0000";
        else
            return "2222";
    }
}

 class FakeToken implements  IToken{

    @Override
    public String getRandom(String account) {
        return "1111";
    }
}
class FakeProfile2 extends ProfileDao{

    @Override
    public String getPassword(String account) {
        if(account.equals("andy"))
            return "0000";
        else
            return "2222";
    }
}

 class FakeToken2 extends RsaTokenDao{

    @Override
    public String getRandom(String account) {
        return "1111";
    }
}

