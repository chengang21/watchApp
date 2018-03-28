package com.duoker.watch.db.model;

/**
 * Created by chengang on 4/25/2017.
 */


import java.io.Serializable;

public class WatchSettingBean implements Serializable
{
    private int alertmode;
    private int alertvolume;
    private int allowshutdown;
    private int PowerSaveMode;
    private int OnlyCallPHB;
    private int gpsfreq;
    private ClasssetBean classset;
    private MutesetBean muteset;
    private WifisetBean wifiset;

    public int getAlertmode()
    {
        return this.alertmode;
    }

    public int getAlertvolume()
    {
        return this.alertvolume;
    }

    public int getAllowshutdown()
    {
        return this.allowshutdown;
    }

    public ClasssetBean getClassset()
    {
        return this.classset;
    }

    public int getGpsfreq()
    {
        return this.gpsfreq;
    }

    public MutesetBean getMuteset()
    {
        return this.muteset;
    }

    public int getOnlyCallPHB()
    {
        return this.OnlyCallPHB;
    }

    public int getPowerSaveMode()
    {
        return this.PowerSaveMode;
    }

    public WifisetBean getWifiset()
    {
        return this.wifiset;
    }

    public void setOnlyCallPHB(int onlyCallPHB) {
        OnlyCallPHB = onlyCallPHB;
    }

    public void setPowerSaveMode(int powerSaveMode) {
        PowerSaveMode = powerSaveMode;
    }

    public void setAlertmode(int alertmode) {
        this.alertmode = alertmode;
    }

    public void setAlertvolume(int alertvolume) {
        this.alertvolume = alertvolume;
    }

    public void setAllowshutdown(int allowshutdown) {
        this.allowshutdown = allowshutdown;
    }

    public void setClassset(ClasssetBean classset) {
        this.classset = classset;
    }

    public void setGpsfreq(int gpsfreq) {
        this.gpsfreq = gpsfreq;
    }

    public void setMuteset(MutesetBean muteset) {
        this.muteset = muteset;
    }

    public void setWifiset(WifisetBean wifiset) {
        this.wifiset = wifiset;
    }
}