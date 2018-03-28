package com.duoker.watch.model;

import java.io.Serializable;

/**
 * Created by cheng on 2017/10/9.
 */
public class DoUnbindWatchModel implements Serializable
{
    private String deluid;
    private String token = "";
    private String userid;
    private String watchId;

    public String getDeluid() {
        return deluid;
    }

    public void setDeluid(String deluid) {
        this.deluid = deluid;
    }

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
