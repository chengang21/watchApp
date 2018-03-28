package com.duoker.watch.model;

import java.io.Serializable;

public class MutesetBean
  implements Serializable
{
  private String mutebegintime;
  private int muteenable;
  private String muteendtime;
  private int muteweek;

  public String getMutebegintime()
  {
    return this.mutebegintime;
  }

  public int getMuteenable()
  {
    return this.muteenable;
  }

  public String getMuteendtime()
  {
    return this.muteendtime;
  }

  public int getMuteweek()
  {
    return this.muteweek;
  }

  public void setMutebegintime(String paramString)
  {
    this.mutebegintime = paramString;
  }

  public void setMuteenable(int paramInt)
  {
    this.muteenable = paramInt;
  }

  public void setMuteendtime(String paramString)
  {
    this.muteendtime = paramString;
  }

  public void setMuteweek(int paramInt)
  {
    this.muteweek = paramInt;
  }
}
