package com.duoker.watch.db.model;

import java.io.Serializable;

/**
 * Created by chengang on 4/25/2017.
 */
public class MutesetBean   implements Serializable
{
    private String mutebegintime;
    private int muteenable;
    private String muteendtime;
    private int muteweek;

    public String getMutebegintime() {
        return mutebegintime;
    }

    public void setMutebegintime(String mutebegintime) {
        this.mutebegintime = mutebegintime;
    }

    public int getMuteenable() {
        return muteenable;
    }

    public void setMuteenable(int muteenable) {
        this.muteenable = muteenable;
    }

    public String getMuteendtime() {
        return muteendtime;
    }

    public void setMuteendtime(String muteendtime) {
        this.muteendtime = muteendtime;
    }

    public int getMuteweek() {
        return muteweek;
    }

    public void setMuteweek(int muteweek) {
        this.muteweek = muteweek;
    }
}
