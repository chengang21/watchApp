package com.duoker.watch.model;

import java.io.Serializable;

public class SleepTimeBean
  implements Serializable
{
  private long calcTime = 0L;
  private int sleepTime = 0;

  public long getCalcTime()
  {
    return this.calcTime;
  }

  public int getSleepTime()
  {
    return this.sleepTime;
  }

  public void setCalcTime(long paramLong)
  {
    this.calcTime = paramLong;
  }

  public void setSleepTime(int paramInt)
  {
    this.sleepTime = paramInt;
  }
}
