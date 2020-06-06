package com.odde.securetoken;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

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

