package com.duoker.watch.model;

import java.io.Serializable;

/**
 * Created by cheng on 2017/8/31.
 */

public class SetupInfo  implements Serializable
{
    private boolean autoLogin;
    private boolean firstRun;
    private String loginUserName;
    private String password;
    private boolean remberPassword;
    private String serverAddr;

    public String getLoginUserName()
    {
        return this.loginUserName;
    }

    public String getPassword()
    {
        return this.password;
    }

    public String getServerAddr()
    {
        return this.serverAddr;
    }

    public boolean isAutoLogin()
    {
        return this.autoLogin;
    }

    public boolean isFirstRun()
    {
        return this.firstRun;
    }

    public boolean isRemberPassword()
    {
        return this.remberPassword;
    }

    public void setAutoLogin(boolean paramBoolean)
    {
        this.autoLogin = paramBoolean;
    }

    public void setFirstRun(boolean paramBoolean)
    {
        this.firstRun = paramBoolean;
    }

    public void setLoginUserName(String paramString)
    {
        this.loginUserName = paramString;
    }

    public void setPassword(String paramString)
    {
        this.password = paramString;
    }

    public void setRemberPassword(boolean paramBoolean)
    {
        this.remberPassword = paramBoolean;
    }

    public void setServerAddr(String paramString)
    {
        this.serverAddr = paramString;
    }
}
