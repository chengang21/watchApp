package com.duoker.watch.model;

import java.io.Serializable;
import java.util.List;

public class CalcWalkCountBean
  implements Serializable
{
  private List<StepsBean> steps;
  private int synTime;
  private int walkNum;
  private String watchId;

  public List<StepsBean> getSteps()
  {
    return this.steps;
  }

  public int getSynTime()
  {
    return this.synTime;
  }

  public int getWalkNum()
  {
    return this.walkNum;
  }

  public String getWatchId()
  {
    return this.watchId;
  }

  public void setSteps(List<StepsBean> paramList)
  {
    this.steps = paramList;
  }

  public void setSynTime(int paramInt)
  {
    this.synTime = paramInt;
  }

  public void setWalkNum(int paramInt)
  {
    this.walkNum = paramInt;
  }

  public void setWatchId(String paramString)
  {
    this.watchId = paramString;
  }
}
