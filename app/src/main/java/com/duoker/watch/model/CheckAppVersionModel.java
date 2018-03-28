package com.duoker.watch.model;

/**
 * Created by cheng on 2017/9/3.
 */

import java.io.Serializable;

public class CheckAppVersionModel implements Serializable
{
    private int appVerNo;
    private String brand;
    private int osType;
    private String token = "";
    private String userid;

    public int getAppVerNo()
    {
        return this.appVerNo;
    }

    public String getBrand()
    {
        return this.brand;
    }

    public int getOsType()
    {
        return this.osType;
    }

    public String getToken()
    {
        return this.token;
    }

    public String getUserid()
    {
        return this.userid;
    }

    public void setAppVerNo(int paramInt)
    {
        this.appVerNo = paramInt;
    }

    public void setBrand(String paramString)
    {
        this.brand = paramString;
    }

    public void setOsType(int paramInt)
    {
        this.osType = paramInt;
    }

    public void setToken(String paramString)
    {
        this.token = paramString;
    }

    public void setUserid(String paramString)
    {
        this.userid = paramString;
    }
}
