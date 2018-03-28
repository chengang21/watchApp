package com.duoker.watch.db.model;

import java.io.Serializable;

/**
 * Created by chengang on 4/25/2017.
 */
public class DoAgreeBindModel implements Serializable
{
    private String bindUserid;
    private int status;
    private String token = "";
    private String userid;
    private String watchId;

    public String getBindUserid() {
        return bindUserid;
    }

    public void setBindUserid(String bindUserid) {
        this.bindUserid = bindUserid;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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
