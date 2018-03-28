package com.duoker.watch.utils;

import android.content.Context;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;

import java.util.List;

/**
 * Created by cheng on 2017/9/9.
 */

public class WifiAdmin
{
    private List<WifiConfiguration> mWifiConfiguration;
    private WifiInfo mWifiInfo;
    private List<ScanResult> mWifiList;
    WifiManager.WifiLock mWifiLock;
    private WifiManager mWifiManager;

    public WifiAdmin(Context paramContext)
    {
        this.mWifiManager = ((WifiManager)paramContext.getSystemService("wifi"));
        this.mWifiInfo = this.mWifiManager.getConnectionInfo();
    }

    public void acquireWifiLock()
    {
        this.mWifiLock.acquire();
    }

    public void addNetwork(WifiConfiguration paramWifiConfiguration)
    {
        int i = this.mWifiManager.addNetwork(paramWifiConfiguration);
        boolean bool = this.mWifiManager.enableNetwork(i, true);
        System.out.println("a--" + i);
        System.out.println("b--" + bool);
    }

    public int checkState()
    {
        return this.mWifiManager.getWifiState();
    }

    public void closeWifi()
    {
        if (this.mWifiManager.isWifiEnabled())
            this.mWifiManager.setWifiEnabled(false);
    }

    public void connectConfiguration(int paramInt)
    {
        if (paramInt > this.mWifiConfiguration.size())
            return;
        this.mWifiManager.enableNetwork(((WifiConfiguration)this.mWifiConfiguration.get(paramInt)).networkId, true);
    }

    public void creatWifiLock()
    {
        this.mWifiLock = this.mWifiManager.createWifiLock("Test");
    }

    public void disconnectWifi(int paramInt)
    {
        this.mWifiManager.disableNetwork(paramInt);
        this.mWifiManager.disconnect();
    }

    public String getBSSID()
    {
        if (this.mWifiInfo == null)
            return "NULL";
        return this.mWifiInfo.getBSSID();
    }

    public List<WifiConfiguration> getConfiguration()
    {
        return this.mWifiConfiguration;
    }

    public int getIPAddress()
    {
        if (this.mWifiInfo == null)
            return 0;
        return this.mWifiInfo.getIpAddress();
    }

    public String getMacAddress()
    {
        if (this.mWifiInfo == null)
            return "NULL";
        return this.mWifiInfo.getMacAddress();
    }

    public int getNetworkId()
    {
        if (this.mWifiInfo == null)
            return 0;
        return this.mWifiInfo.getNetworkId();
    }

    public String getWifiInfo()
    {
        if (this.mWifiInfo == null)
            return "NULL";
        return this.mWifiInfo.toString();
    }

    public List<ScanResult> getWifiList()
    {
        return this.mWifiList;
    }

    public StringBuilder lookUpScan()
    {
        StringBuilder localStringBuilder = new StringBuilder();
        for (int i = 0; i < this.mWifiList.size(); i++)
        {
            localStringBuilder.append("Index_" + new Integer(i + 1).toString() + ":");
            localStringBuilder.append(((ScanResult)this.mWifiList.get(i)).toString());
            localStringBuilder.append("/n");
        }
        return localStringBuilder;
    }

    public void openWifi()
    {
        if (!this.mWifiManager.isWifiEnabled())
            this.mWifiManager.setWifiEnabled(true);
    }

    public void releaseWifiLock()
    {
        if (this.mWifiLock.isHeld())
            this.mWifiLock.acquire();
    }

    public void startScan()
    {
        this.mWifiManager.startScan();
        this.mWifiList = this.mWifiManager.getScanResults();
        this.mWifiConfiguration = this.mWifiManager.getConfiguredNetworks();
    }
}
