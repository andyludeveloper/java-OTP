package com.odde.securetoken;

public class StubMyToken implements MyToken
{
    @Override
    public String getRandom(String account) {
        return "000000";
    }
}
