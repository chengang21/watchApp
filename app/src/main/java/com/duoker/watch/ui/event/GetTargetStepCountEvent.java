package com.duoker.watch.ui.event;

/**
 * Created by chengang on 5/9/2017.
 */

public class GetTargetStepCountEvent
{
    public String watchId;

    public GetTargetStepCountEvent(String watchid)
    {
        this.watchId = watchid;
    }
}