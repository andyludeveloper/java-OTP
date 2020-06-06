package com.odde.securetoken;

public class ProfileDao implements IProfile {
    public String getPassword(String account) {
        return Context.getPassword(account);
    }
}
