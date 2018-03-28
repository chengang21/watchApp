package com.duoker.watch.model;

import java.io.Serializable;

/**
 * Created by cheng on 2017/10/9.
 */
public class DoPermissionToTransferModel implements Serializable
{
    private String newUid;
    private String pwd;
    private String token = "";
    private String userid;
    private String watchid;

    public String getNewUid() {
        return newUid;
    }

    public void setNewUid(String newUid) {
        this.newUid = newUid;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
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

    public String getWatchid() {
        return watchid;
    }

    public void setWatchid(String watchid) {
        this.watchid = watchid;
    }
}
