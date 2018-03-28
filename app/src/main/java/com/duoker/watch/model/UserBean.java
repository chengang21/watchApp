package com.duoker.watch.model;

import java.io.Serializable;

public class UserBean   implements Serializable
{
  private String birthYear;
  private String headImage;
  private String session;
  private String userName;
  private String userSex;
  private String userid;

  public String getBirthYear()
  {
    return this.birthYear;
  }

  public String getHeadImage()
  {
    return this.headImage;
  }

  public String getSession()
  {
    return this.session;
  }

  public String getUserName()
  {
    return this.userName;
  }

  public String getUserSex()
  {
    return this.userSex;
  }

  public String getUserid()
  {
    return this.userid;
  }

  public void setBirthYear(String paramString)
  {
    this.birthYear = paramString;
  }

  public void setHeadImage(String paramString)
  {
    this.headImage = paramString;
  }

  public void setSession(String paramString)
  {
    this.session = paramString;
  }

  public void setUserName(String paramString)
  {
    this.userName = paramString;
  }

  public void setUserSex(String paramString)
  {
    this.userSex = paramString;
  }

  public void setUserid(String paramString)
  {
    this.userid = paramString;
  }
}
