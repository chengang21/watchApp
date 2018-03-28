package com.duoker.watch.repository;

/**
 * Created by cheng on 2017/9/3.
 */

import com.duoker.watch.model.LoginInfoBean;
import com.duoker.watch.model.LoginModel;

import java.io.IOException;

public interface LoginRepository
{
    public LoginInfoBean login(LoginModel paramLoginModel) throws IOException;

    public void logout(String username)  throws IOException;

    public LoginInfoBean thirdPartLogin(String username, String password, String appid, String secret) throws IOException;
}
