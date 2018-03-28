package com.duoker.watch.db.model;

/**
 * Created by chengang on 4/25/2017.
 */


import android.text.TextUtils;
import java.io.Serializable;

public class WifiHotSpotModel implements Serializable
{
    private String wifiMac;
    private String wifiName;
    private String wifiRssi;

    public boolean equals(Object obj)
    {
        if ((obj instanceof WifiHotSpotModel))
        {
            WifiHotSpotModel wifiHotSpotModel = (WifiHotSpotModel)obj;
            if ((TextUtils.equals(getWifiName(), wifiHotSpotModel.getWifiName())) && (TextUtils.equals(getWifiMac(), wifiHotSpotModel.getWifiMac())))
                return true;
        }
        return false;
    }
    public int hashCode()
    {
        return new StringBuilder(getWifiName()).append(getWifiMac()).hashCode();
    }

    public String getWifiMac() {
        return wifiMac;
    }

    public void setWifiMac(String wifiMac) {
        this.wifiMac = wifiMac;
    }

    public String getWifiName() {
        return wifiName;
    }

    public void setWifiName(String wifiName) {
        this.wifiName = wifiName;
    }

    public String getWifiRssi() {
        return wifiRssi;
    }

    public void setWifiRssi(String wifiRssi) {
        this.wifiRssi = wifiRssi;
    }
}