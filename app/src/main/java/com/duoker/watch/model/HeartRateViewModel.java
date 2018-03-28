package com.duoker.watch.model;

/**
 * Created by cheng on 2017/10/8.
 */
import java.io.Serializable;

public class HeartRateViewModel implements Serializable
{
    private HeartRateBean heartRateBean;
    private int type;

    public HeartRateViewModel(HeartRateBean heartRateBean, int type)
    {
        this.heartRateBean = heartRateBean;
        this.type = type;
    }

    public HeartRateBean getHeartRateBean() {
        return heartRateBean;
    }

    public void setHeartRateBean(HeartRateBean heartRateBean) {
        this.heartRateBean = heartRateBean;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}