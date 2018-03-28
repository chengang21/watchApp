package com.duoker.watch.model;

import java.io.Serializable;

/**
 * Created by cheng on 2017/9/3.
 */
public class ResetPasswordModel
        implements Serializable
{
    private String code;
    private String password;
    private String resetUserName;

    public String getCode()
    {
        return this.code;
    }

    public String getPassword()
    {
        return this.password;
    }

    public String getResetUserName()
    {
        return this.resetUserName;
    }

    public void setCode(String paramString)
    {
        this.code = paramString;
    }

    public void setPassword(String paramString)
    {
        this.password = paramString;
    }

    public void setResetUserName(String paramString)
    {
        this.resetUserName = paramString;
    }
}
