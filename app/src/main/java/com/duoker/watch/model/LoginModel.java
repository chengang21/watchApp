package com.duoker.watch.model;

import java.io.Serializable;

public class LoginModel
  implements Serializable
{
  private String loginUserName;
  private String password;

  public String getLoginUserName()
  {
    return this.loginUserName;
  }

  public String getPassword()
  {
    return this.password;
  }

  public void setLoginUserName(String paramString)
  {
    this.loginUserName = paramString;
  }

  public void setPassword(String paramString)
  {
    this.password = paramString;
  }
}
