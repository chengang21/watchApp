package com.duoker.watch.model;

/**
 * Created by cheng on 2017/9/3.
 */

import java.io.Serializable;

public class PhoneBalanceBean implements Serializable
{
    private int retcode;
    private float balance;

    public int getRetcode()
    {
        return this.retcode;
    }

    public void setRetcode(int retcode)
    {
        this.retcode = retcode;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }
}
