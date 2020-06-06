package com.odde.securetoken;

public class AuthenticationService {
    private IProfile _profile;
    private IToken _token;
    private ILogger _logger;

    AuthenticationService(IProfile profile, IToken token, ILogger logger){
        _profile = profile;
        _token = token;
        _logger = logger;
    }

    AuthenticationService(){
        _profile =  new ProfileDao();
        _token = new RsaTokenDao();
    }
    public boolean isValid(String account, String password) {
        // 根據 account 取得自訂密碼
        String passwordFromDao = _profile.getPassword(account);

        // 根據 account 取得 RSA token 目前的亂數
        String randomCode = _token.getRandom(account);

        // 驗證傳入的 password 是否等於自訂密碼 + RSA token亂數
        String validPassword = passwordFromDao + randomCode;
        boolean isValid = password.equals(validPassword);

        if (isValid) {
            return true;
        } else {
            _logger.save("account: "+account+", try to login failed.");
            return false;
        }
    }
}
