package com.duoker.watch.interactors.impl;

import com.duoker.watch.interactors.base.SimpleInteractor;
import com.duoker.watch.repository.WatchSettingRepository;

import java.io.IOException;

/**
 * Created by cheng on 2017/10/9.
 */
public class ShutdownDeviceInteractorImpl extends SimpleInteractor<Object>
{
    private final String mUserId;
    private final String mWatchId;
    private final WatchSettingRepository mWatchSettingRepository;

    public ShutdownDeviceInteractorImpl(String userid, String watchid, WatchSettingRepository watchSettingRepository)
    {
        this.mUserId = userid;
        this.mWatchId = watchid;
        this.mWatchSettingRepository = watchSettingRepository;
    }

    public void run()
    {
        try
        {
            this.mWatchSettingRepository.shutdownDevice(this.mUserId, this.mWatchId);
            postObject2UiThread(null);
            return;
        }
        catch (IOException e)
        {
            e.printStackTrace();
            postException2UiThread(e);
        }
    }
}
