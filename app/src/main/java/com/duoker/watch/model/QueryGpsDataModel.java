package com.duoker.watch.model;

import java.io.Serializable;

/**
 * Created by cheng on 2017/10/9.
 */
public class QueryGpsDataModel implements Serializable
{
    private long beginTime;
    private boolean bydate;
    private long endTime;
    private String userId;
    private String watchId;

    public long getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(long beginTime) {
        this.beginTime = beginTime;
    }

    public boolean isBydate() {
        return bydate;
    }

    public void setBydate(boolean bydate) {
        this.bydate = bydate;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getWatchId() {
        return watchId;
    }

    public void setWatchId(String watchId) {
        this.watchId = watchId;
    }
}
