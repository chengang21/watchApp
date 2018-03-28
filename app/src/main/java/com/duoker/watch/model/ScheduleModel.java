package com.duoker.watch.model;

import java.io.Serializable;

/**
 * Created by cheng on 2017/10/6.
 */

public class ScheduleModel implements Serializable
{
    private String schedulecontent;
    private String scheduleid;
    private int schedulerepeat;
    private String schedulesetnikname;
    private String schedulesetphone;
    private int schedulestatus;
    private String scheduletime;
    private int scheduleweek;
    private String userId;
    private String watchId;

    public String getSchedulecontent() {
        return schedulecontent;
    }

    public void setSchedulecontent(String schedulecontent) {
        this.schedulecontent = schedulecontent;
    }

    public String getScheduleid() {
        return scheduleid;
    }

    public void setScheduleid(String scheduleid) {
        this.scheduleid = scheduleid;
    }

    public int getSchedulerepeat() {
        return schedulerepeat;
    }

    public void setSchedulerepeat(int schedulerepeat) {
        this.schedulerepeat = schedulerepeat;
    }

    public String getSchedulesetnikname() {
        return schedulesetnikname;
    }

    public void setSchedulesetnikname(String schedulesetnikname) {
        this.schedulesetnikname = schedulesetnikname;
    }

    public String getSchedulesetphone() {
        return schedulesetphone;
    }

    public void setSchedulesetphone(String schedulesetphone) {
        this.schedulesetphone = schedulesetphone;
    }

    public int getSchedulestatus() {
        return schedulestatus;
    }

    public void setSchedulestatus(int schedulestatus) {
        this.schedulestatus = schedulestatus;
    }

    public String getScheduletime() {
        return scheduletime;
    }

    public void setScheduletime(String scheduletime) {
        this.scheduletime = scheduletime;
    }

    public int getScheduleweek() {
        return scheduleweek;
    }

    public void setScheduleweek(int scheduleweek) {
        this.scheduleweek = scheduleweek;
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
