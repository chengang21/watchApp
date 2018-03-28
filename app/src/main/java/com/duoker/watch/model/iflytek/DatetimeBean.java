package com.duoker.watch.model.iflytek;

import java.io.Serializable;

/**
 * Created by cheng on 2017/10/6.
 */
public class DatetimeBean implements Serializable
{
    private String date;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDateOrig() {
        return dateOrig;
    }

    public void setDateOrig(String dateOrig) {
        this.dateOrig = dateOrig;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTimeOrig() {
        return timeOrig;
    }

    public void setTimeOrig(String timeOrig) {
        this.timeOrig = timeOrig;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    private String dateOrig;
    private String time;
    private String timeOrig;
    private String type;
}
