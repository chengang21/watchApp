package com.duoker.watch.model;

/**
 * Created by cheng on 2017/10/9.
 */

import java.io.Serializable;
import java.util.List;

public class GetNotificationMessageModel implements Serializable
{
    private String loginUserName;
    private int size;
    private int start;
    private List<Integer> typeList;
    private List<String> watchIds;

    public String getLoginUserName() {
        return loginUserName;
    }

    public void setLoginUserName(String loginUserName) {
        this.loginUserName = loginUserName;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public List<Integer> getTypeList() {
        return typeList;
    }

    public void setTypeList(List<Integer> typeList) {
        this.typeList = typeList;
    }

    public List<String> getWatchIds() {
        return watchIds;
    }

    public void setWatchIds(List<String> watchIds) {
        this.watchIds = watchIds;
    }
}
