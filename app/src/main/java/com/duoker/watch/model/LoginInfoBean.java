package com.duoker.watch.model;

import java.io.Serializable;
import java.util.List;

public class LoginInfoBean
  implements Serializable
{
  private UserBean user;
  private List<WatchsBean> watchs;

  public UserBean getUser()
  {
    return this.user;
  }

  public List<WatchsBean> getWatchs()
  {
    return this.watchs;
  }

  public void setUser(UserBean paramUserBean)
  {
    this.user = paramUserBean;
  }

  public void setWatchs(List<WatchsBean> paramList)
  {
    this.watchs = paramList;
  }
}
