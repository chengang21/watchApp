package com.duoker.watch.model;

import java.io.Serializable;

/**
 * Created by cheng on 2017/10/6.
 */

public class UserSetupInfo implements Serializable
{
    private boolean audio;
    private String defaultWatchId;
    private boolean isDelOutTimeSchedule;
    private MapTypeEnum mapType;
    private boolean notificationmsg;
    private String preLatitude;
    private String preLongitude;
    private boolean vibration;

    public String getDefaultWatchId()
    {
        return this.defaultWatchId;
    }

    public MapTypeEnum getMapType()
    {
        return this.mapType;
    }

    public String getPreLatitude()
    {
        return this.preLatitude;
    }

    public String getPreLongitude()
    {
        return this.preLongitude;
    }

    public boolean isAudio()
    {
        return this.audio;
    }

    public boolean isDelOutTimeSchedule()
    {
        return this.isDelOutTimeSchedule;
    }

    public boolean isNotificationmsg()
    {
        return this.notificationmsg;
    }

    public boolean isVibration()
    {
        return this.vibration;
    }

    public void setAudio(boolean audio) {
        this.audio = audio;
    }

    public void setDefaultWatchId(String defaultWatchId) {
        this.defaultWatchId = defaultWatchId;
    }

    public void setDelOutTimeSchedule(boolean delOutTimeSchedule) {
        isDelOutTimeSchedule = delOutTimeSchedule;
    }

    public void setMapType(MapTypeEnum mapType) {
        this.mapType = mapType;
    }

    public void setNotificationmsg(boolean notificationmsg) {
        this.notificationmsg = notificationmsg;
    }

    public void setPreLatitude(String preLatitude) {
        this.preLatitude = preLatitude;
    }

    public void setPreLongitude(String preLongitude) {
        this.preLongitude = preLongitude;
    }

    public void setVibration(boolean vibration) {
        this.vibration = vibration;
    }

    public static enum MapTypeEnum
    {
        BAIDU,
        AMAP,
        GOOGLE
    }
}
