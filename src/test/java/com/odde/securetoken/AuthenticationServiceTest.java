package com.odde.securetoken;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class AuthenticationServiceTest {

    @Test
    public void is_valid_test() {
//        AuthenticationService target = new AuthenticationService();
        AuthenticationService target = new AuthenticationService(new StubProfile(), new StubMyToken());

        boolean actual = target.isValid("joey", "91000000");

        assertTrue(actual);
    }

}

