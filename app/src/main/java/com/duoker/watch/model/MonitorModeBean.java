package com.duoker.watch.model;

/**
 * Created by cheng on 2017/10/8.
 */
import java.io.Serializable;

public class MonitorModeBean implements Serializable
{
    private String result;
    private String status;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
