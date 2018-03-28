package com.duoker.watch.model;

/**
 * Created by chengang on 4/25/2017.
 */

import java.io.Serializable;

public class SetWifiHotSpotModel implements Serializable
{
    private int wifienable;
    private String wifimac;
    private String wifipwd;
    private String wifissid;

    public int getWifienable()
    {
        return this.wifienable;
    }

    public String getWifimac()
    {
        return this.wifimac;
    }

    public String getWifipwd()
    {
        return this.wifipwd;
    }

    public String getWifissid()
    {
        return this.wifissid;
    }

    public void setWifienable(int wifienable) {
        this.wifienable = wifienable;
    }

    public void setWifimac(String wifimac) {
        this.wifimac = wifimac;
    }

    public void setWifipwd(String wifipwd) {
        this.wifipwd = wifipwd;
    }

    public void setWifissid(String wifissid) {
        this.wifissid = wifissid;
    }
}