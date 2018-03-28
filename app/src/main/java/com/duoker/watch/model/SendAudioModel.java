package com.duoker.watch.model;

import java.io.Serializable;

public class SendAudioModel
  implements Serializable
{
  private String audiopath;
  private String audiosender;
  private long audiosendtime;
  private int audiotime;
  private long groupId;
  private String loginUserName;
  private String userid;
  private String watchId;

  public String getAudiopath()
  {
    return this.audiopath;
  }

  public String getAudiosender()
  {
    return this.audiosender;
  }

  public long getAudiosendtime()
  {
    return this.audiosendtime;
  }

  public int getAudiotime()
  {
    return this.audiotime;
  }

  public long getGroupId()
  {
    return this.groupId;
  }

  public String getLoginUserName()
  {
    return this.loginUserName;
  }

  public String getUserid()
  {
    return this.userid;
  }

  public String getWatchId()
  {
    return this.watchId;
  }

  public void setAudiopath(String paramString)
  {
    this.audiopath = paramString;
  }

  public void setAudiosender(String paramString)
  {
    this.audiosender = paramString;
  }

  public void setAudiosendtime(long paramLong)
  {
    this.audiosendtime = paramLong;
  }

  public void setAudiotime(int paramInt)
  {
    this.audiotime = paramInt;
  }

  public void setGroupId(long paramLong)
  {
    this.groupId = paramLong;
  }

  public void setLoginUserName(String paramString)
  {
    this.loginUserName = paramString;
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
