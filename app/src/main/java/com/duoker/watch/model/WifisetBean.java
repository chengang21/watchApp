package com.duoker.watch.model;

import java.io.Serializable;

public class WifisetBean
  implements Serializable
{
  private int wifienable;
  private String wifimac;
  private String wifipwd;
  private String wifisetphone;
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

  public String getWifisetphone()
  {
    return this.wifisetphone;
  }

  public String getWifissid()
  {
    return this.wifissid;
  }

  public void setWifienable(int paramInt)
  {
    this.wifienable = paramInt;
  }

  public void setWifimac(String paramString)
  {
    this.wifimac = paramString;
  }

  public void setWifipwd(String paramString)
  {
    this.wifipwd = paramString;
  }

  public void setWifisetphone(String paramString)
  {
    this.wifisetphone = paramString;
  }

  public void setWifissid(String paramString)
  {
    this.wifissid = paramString;
  }
}
