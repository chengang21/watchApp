package com.duoker.watch.db.model;

import java.io.Serializable;

/**
 * Created by chengang on 4/25/2017.
 */
public class CheckWatchBean implements Serializable
{
    private String owner;
    private String phone;

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
