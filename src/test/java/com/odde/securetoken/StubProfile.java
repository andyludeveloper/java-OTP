package com.odde.securetoken;

public class StubProfile implements Profile
{

    @Override
    public String getPassword(String account) {
        if (account=="joey") {
            return "91";
        }
        return "";
    }
}
