package com.duoker.watch.model;

import java.io.Serializable;

public class StartHeartRateMeasureModel
  implements Serializable
{
  private String token = "";
  private String userid;
  private String watchId;

  public String getToken()
  {
    return this.token;
  }

  public String getUserid()
  {
    return this.userid;
  }

  public String getWatchId()
  {
    return this.watchId;
  }

  public void setToken(String paramString)
  {
    this.token = paramString;
  }

  public void setUserid(String paramString)
  {
    this.userid = paramString;
  }

  public void setWatchId(String paramString)
  {
    this.watchId = paramString;
  }
}
