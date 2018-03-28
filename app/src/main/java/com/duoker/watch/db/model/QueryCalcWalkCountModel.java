package com.duoker.watch.db.model;

import java.io.Serializable;

/**
 * Created by chengang on 5/9/2017.
 */

public class QueryCalcWalkCountModel implements Serializable
{
    private long endTime;
    private long startTime;
    private String userId;
    private String watchId;

    public long getEndTime()
    {
        return endTime;
    }

    public long getStartTime()
    {
        return startTime;
    }

    public String getUserId()
    {
        return userId;
    }

    public String getWatchId()
    {
        return watchId;
    }

    public void setEndTime(long end)
    {
        endTime = end;
    }

    public void setStartTime(long start)
    {
        startTime = start;
    }

    public void setUserId(String userid)
    {
        userId = userid;
    }

    public void setWatchId(String watchId)
    {
        this.watchId = watchId;
    }
}
