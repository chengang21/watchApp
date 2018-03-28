package com.duoker.watch.db;

/**
 * Created by chengang on 4/24/2017.
 */

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;
import java.io.Serializable;

@Table(name="ly_save_area")
public class FenceRecord   implements Serializable
{
    @Column(name="enable")
    private boolean enable;

    @Column(isId=true, name="id")
    private long id;

    @Column(name="latitude")
    private double latitude;

    @Column(name="loginUserName")
    private String loginUserName;

    @Column(name="longitude")
    private double longitude;

    @Column(name="name")
    private String name;

    @Column(name="notifyType")
    private int notifyType;

    @Column(name="radius")
    private int radius = 2;

    @Column(name="watchid")
    private String watchId;

    public long getId()
    {
        return this.id;
    }

    public double getLatitude()
    {
        return this.latitude;
    }

    public String getLoginUserName()
    {
        return this.loginUserName;
    }

    public double getLongitude()
    {
        return this.longitude;
    }

    public String getName()
    {
        return this.name;
    }

    public int getNotifyType()
    {
        return this.notifyType;
    }

    public int getRadius()
    {
        return this.radius;
    }

    public String getWatchId()
    {
        return this.watchId;
    }

    public boolean isEnable()
    {
        return this.enable;
    }

    public void setEnable(boolean paramBoolean)
    {
        this.enable = paramBoolean;
    }

    public void setId(long paramLong)
    {
        this.id = paramLong;
    }

    public void setLatitude(double paramDouble)
    {
        this.latitude = paramDouble;
    }

    public void setLoginUserName(String paramString)
    {
        this.loginUserName = paramString;
    }

    public void setLongitude(double paramDouble)
    {
        this.longitude = paramDouble;
    }

    public void setName(String paramString)
    {
        this.name = paramString;
    }

    public void setNotifyType(int paramInt)
    {
        this.notifyType = paramInt;
    }

    public void setRadius(int paramInt)
    {
        this.radius = paramInt;
    }

    public void setWatchId(String paramString)
    {
        this.watchId = paramString;
    }
}