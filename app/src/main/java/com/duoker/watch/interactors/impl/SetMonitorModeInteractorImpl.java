package com.duoker.watch.interactors.impl;

import com.duoker.watch.interactors.base.SimpleInteractor;
import com.duoker.watch.model.MonitorModeBean;
import com.duoker.watch.repository.MonitorModeRepository;

import java.io.IOException;

/**
 * Created by cheng on 2017/10/8.
 */
public class SetMonitorModeInteractorImpl extends SimpleInteractor <MonitorModeBean>
{
    private final MonitorModeRepository mMonitorModeRepository;
    private final String mUserId;
    private final String mWatchId;

    public SetMonitorModeInteractorImpl(String userid, String watchid, MonitorModeRepository paramMonitorModeRepository)
    {
        this.mUserId = userid;
        this.mWatchId = watchid;
        this.mMonitorModeRepository = paramMonitorModeRepository;
    }

    public void run()
    {
        try
        {
            postObject2UiThread(this.mMonitorModeRepository.setCallToMonitorMode(this.mUserId, this.mWatchId));
            return;
        }
        catch (IOException e)
        {
            e.printStackTrace();
            postException2UiThread(e);
        }
    }
}
