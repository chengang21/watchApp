package com.duoker.watch.storage;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

import java.io.Serializable;

/**
 * Created by chengang on 5/9/2017.
 */

@Table(name="StepCountTargetStorage", onCreated="CREATE UNIQUE INDEX index_name ON StepCountTargetStorage(id)")
public class StepCountTargetStorage
        implements Serializable
{

    @Column(isId=true, name="id")
    private long id;

    @Column(name="loginUserName")
    private String loginUserName;

    @Column(name="targetStep")
    private long targetStep;

    @Column(name="watchid")
    private String watchId;

    public long getId()
    {
        return this.id;
    }

    public String getLoginUserName()
    {
        return this.loginUserName;
    }

    public long getTargetStep()
    {
        return this.targetStep;
    }

    public String getWatchId()
    {
        return this.watchId;
    }

    public void setId(long paramLong)
    {
        this.id = paramLong;
    }

    public void setLoginUserName(String paramString)
    {
        this.loginUserName = paramString;
    }

    public void setTargetStep(long paramLong)
    {
        this.targetStep = paramLong;
    }

    public void setWatchId(String paramString)
    {
        this.watchId = paramString;
    }
}