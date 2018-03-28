package com.duoker.watch.db.model;

/**
 * Created by chengang on 4/25/2017.
 */

import java.io.Serializable;

public class AllWifiHotSpotBean implements Serializable
{
    private int status;
    private WifiHotSpotBean wifihotspot;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public WifiHotSpotBean getWifihotspot() {
        return wifihotspot;
    }

    public void setWifihotspot(WifiHotSpotBean wifihotspot) {
        this.wifihotspot = wifihotspot;
    }
}