package com.duoker.watch.model;

import java.io.Serializable;

/**
 * Created by cheng on 2017/9/3.
 */

public class CheckAppVersionBean implements Serializable
{
    private String downurl;
    private String intro;
    private int retcode;
    private String retmsg;
    private int type;
    private String verNo;
    private String verStr;

    public String getDownurl()
    {
        return this.downurl;
    }

    public String getIntro()
    {
        return this.intro;
    }

    public int getRetcode()
    {
        return this.retcode;
    }

    public String getRetmsg()
    {
        return this.retmsg;
    }

    public int getType()
    {
        return this.type;
    }

    public String getVerNo()
    {
        return this.verNo;
    }

    public String getVerStr()
    {
        return this.verStr;
    }

    public void setDownurl(String paramString)
    {
        this.downurl = paramString;
    }

    public void setIntro(String paramString)
    {
        this.intro = paramString;
    }

    public void setRetcode(int paramInt)
    {
        this.retcode = paramInt;
    }

    public void setRetmsg(String paramString)
    {
        this.retmsg = paramString;
    }

    public void setType(int paramInt)
    {
        this.type = paramInt;
    }

    public void setVerNo(String paramString)
    {
        this.verNo = paramString;
    }

    public void setVerStr(String paramString)
    {
        this.verStr = paramString;
    }
}
