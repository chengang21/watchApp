package com.duoker.watch.model;

import java.io.Serializable;

public class ClasssetBean
  implements Serializable
{
  private String classafternoonbegin;
  private String classafternoonend;
  private int classenable;
  private String classmorningbegin;
  private String classmorningend;
  private int classweek;

  public String getClassafternoonbegin()
  {
    return this.classafternoonbegin;
  }

  public String getClassafternoonend()
  {
    return this.classafternoonend;
  }

  public int getClassenable()
  {
    return this.classenable;
  }

  public String getClassmorningbegin()
  {
    return this.classmorningbegin;
  }

  public String getClassmorningend()
  {
    return this.classmorningend;
  }

  public int getClassweek()
  {
    return this.classweek;
  }

  public void setClassafternoonbegin(String paramString)
  {
    this.classafternoonbegin = paramString;
  }

  public void setClassafternoonend(String paramString)
  {
    this.classafternoonend = paramString;
  }

  public void setClassenable(int paramInt)
  {
    this.classenable = paramInt;
  }

  public void setClassmorningbegin(String paramString)
  {
    this.classmorningbegin = paramString;
  }

  public void setClassmorningend(String paramString)
  {
    this.classmorningend = paramString;
  }

  public void setClassweek(int paramInt)
  {
    this.classweek = paramInt;
  }
}
