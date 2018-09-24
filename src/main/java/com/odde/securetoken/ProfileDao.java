package com.odde.securetoken;

public class ProfileDao implements Profile {
    @Override
    public String getPassword(String account) {
        return Context.getPassword(account);
    }
}
