package com.duoker.watch.db;

import java.io.Serializable;

/**
 * Created by chengang on 4/25/2017.
 */

public class CardNumberRecord
        implements Serializable
{
    private int cardIsChecked;
    private String cardid;
    private String cardname;
    private String cardnum;
    private String cardshortnum;
    private int cardtype;

    public int getCardIsChecked()
    {
        return this.cardIsChecked;
    }

    public String getCardid()
    {
        return this.cardid;
    }

    public String getCardname()
    {
        return this.cardname;
    }

    public String getCardnum()
    {
        return this.cardnum;
    }

    public String getCardshortnum()
    {
        return this.cardshortnum;
    }

    public int getCardtype()
    {
        return this.cardtype;
    }

    public void setCardIsChecked(int paramInt)
    {
        this.cardIsChecked = paramInt;
    }

    public void setCardid(String paramString)
    {
        this.cardid = paramString;
    }

    public void setCardname(String paramString)
    {
        this.cardname = paramString;
    }

    public void setCardnum(String paramString)
    {
        this.cardnum = paramString;
    }

    public void setCardshortnum(String paramString)
    {
        this.cardshortnum = paramString;
    }

    public void setCardtype(int paramInt)
    {
        this.cardtype = paramInt;
    }
}