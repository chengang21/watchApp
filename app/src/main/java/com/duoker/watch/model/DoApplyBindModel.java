package com.duoker.watch.model;

import java.io.Serializable;

/**
 * Created by chengang on 4/25/2017.
 */
public class DoApplyBindModel implements Serializable
{
    private String token = "";
    private String userid;
    private String watchId;

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

    public String getWatchId() {
        return watchId;
    }

    public void setWatchId(String watchId) {
        this.watchId = watchId;
    }
}
