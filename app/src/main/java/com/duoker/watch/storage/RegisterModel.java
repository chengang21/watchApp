package com.duoker.watch.storage;

import java.io.Serializable;

/**
 * Created by cheng on 2017/9/3.
 */

public class RegisterModel
        implements Serializable
{
    private String birth;
    private String code;
    private String headImage;
    private String password;
    private String registerUserName;
    private String token;
    private String userName;
    private String userSex;

    public String getBirth()
    {
        return this.birth;
    }

    public String getCode()
    {
        return this.code;
    }

    public String getHeadImage()
    {
        return this.headImage;
    }

    public String getPassword()
    {
        return this.password;
    }

    public String getRegisterUserName()
    {
        return this.registerUserName;
    }

    public String getToken()
    {
        return this.token;
    }

    public String getUserName()
    {
        return this.userName;
    }

    public String getUserSex()
    {
        return this.userSex;
    }

    public void setBirth(String paramString)
    {
        this.birth = paramString;
    }

    public void setCode(String paramString)
    {
        this.code = paramString;
    }

    public void setHeadImage(String paramString)
    {
        this.headImage = paramString;
    }

    public void setPassword(String paramString)
    {
        this.password = paramString;
    }

    public void setRegisterUserName(String paramString)
    {
        this.registerUserName = paramString;
    }

    public void setToken(String paramString)
    {
        this.token = paramString;
    }

    public void setUserName(String paramString)
    {
        this.userName = paramString;
    }

    public void setUserSex(String paramString)
    {
        this.userSex = paramString;
    }
}
