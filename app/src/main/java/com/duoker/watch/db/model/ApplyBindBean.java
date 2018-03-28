package com.duoker.watch.db.model;

import java.io.Serializable;

/**
 * Created by chengang on 4/25/2017.
 */
public class ApplyBindBean implements Serializable
{
    private int retcode;
    private String retmsg;

    public int getRetcode() {
        return retcode;
    }

    public void setRetcode(int retcode) {
        this.retcode = retcode;
    }

    public String getRetmsg() {
        return retmsg;
    }

    public void setRetmsg(String retmsg) {
        this.retmsg = retmsg;
    }
}
