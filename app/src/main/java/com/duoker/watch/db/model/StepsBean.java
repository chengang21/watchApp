package com.duoker.watch.db.model;

/**
 * Created by chengang on 4/25/2017.
 */

import java.io.Serializable;

public class StepsBean
        implements Serializable
{
    private int stepsCount;
    private long stepsDate;

    public int getStepsCount()
    {
        return this.stepsCount;
    }

    public long getStepsDate()
    {
        return this.stepsDate;
    }

    public void setStepsCount(int paramInt)
    {
        this.stepsCount = paramInt;
    }

    public void setStepsDate(long paramLong)
    {
        this.stepsDate = paramLong;
    }
}