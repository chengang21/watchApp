package com.duoker.watch.model.iflytek;

import java.io.Serializable;

/**
 * Created by cheng on 2017/10/6.
 */
public class SlotsBean implements Serializable
{
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public DatetimeBean getDatetime() {
        return datetime;
    }

    public void setDatetime(DatetimeBean datetime) {
        this.datetime = datetime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String content;
    private DatetimeBean datetime;
    private String name;
}
