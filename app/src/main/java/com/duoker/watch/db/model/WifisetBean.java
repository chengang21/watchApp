package com.duoker.watch.db.model;

/**
 * Created by chengang on 4/25/2017.
 */


import java.io.Serializable;

public class WifisetBean implements Serializable
{
    private int wifienable;
    private String wifimac;
    private String wifipwd;
    private String wifisetphone;
    private String wifissid;

    public int getWifienable() {
        return wifienable;
    }

    public void setWifienable(int wifienable) {
        this.wifienable = wifienable;
    }

    public String getWifimac() {
        return wifimac;
    }

    public void setWifimac(String wifimac) {
        this.wifimac = wifimac;
    }

    public String getWifipwd() {
        return wifipwd;
    }

    public void setWifipwd(String wifipwd) {
        this.wifipwd = wifipwd;
    }

    public String getWifisetphone() {
        return wifisetphone;
    }

    public void setWifisetphone(String wifisetphone) {
        this.wifisetphone = wifisetphone;
    }

    public String getWifissid() {
        return wifissid;
    }

    public void setWifissid(String wifissid) {
        this.wifissid = wifissid;
    }
}