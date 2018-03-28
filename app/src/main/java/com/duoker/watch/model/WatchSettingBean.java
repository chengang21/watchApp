package com.duoker.watch.model;

import java.io.Serializable;

public class WatchSettingBean
  implements Serializable
{
  private int OnlyCallPHB;
  private int PowerSaveMode;
  private int alertmode;
  private int alertvolume;
  private int allowshutdown;
  private ClasssetBean classset;
  private int gpsfreq;
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

  public void setAlertmode(int paramInt)
  {
    this.alertmode = paramInt;
  }

  public void setAlertvolume(int paramInt)
  {
    this.alertvolume = paramInt;
  }

  public void setAllowshutdown(int paramInt)
  {
    this.allowshutdown = paramInt;
  }

  public void setClassset(ClasssetBean paramClasssetBean)
  {
    this.classset = paramClasssetBean;
  }

  public void setGpsfreq(int paramInt)
  {
    this.gpsfreq = paramInt;
  }

  public void setMuteset(MutesetBean paramMutesetBean)
  {
    this.muteset = paramMutesetBean;
  }

  public void setOnlyCallPHB(int paramInt)
  {
    this.OnlyCallPHB = paramInt;
  }

  public void setPowerSaveMode(int paramInt)
  {
    this.PowerSaveMode = paramInt;
  }

  public void setWifiset(WifisetBean paramWifisetBean)
  {
    this.wifiset = paramWifisetBean;
  }
}
