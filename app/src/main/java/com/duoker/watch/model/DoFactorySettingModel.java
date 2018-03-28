package com.duoker.watch.model;

import java.io.Serializable;

/**
 * Created by cheng on 2017/10/9.
 */
public class DoFactorySettingModel implements Serializable
{
    private String token;
    private String userid;
    private String watchid;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getWatchid() {
        return watchid;
    }

    public void setWatchid(String watchid) {
        this.watchid = watchid;
    }
}
