package com.duoker.watch.interactors.impl;

import com.duoker.watch.interactors.base.SimpleInteractor;
import com.duoker.watch.repository.WatchSettingRepository;

import java.io.IOException;

/**
 * Created by cheng on 2017/10/9.
 */
public class EditAllowShutdownInteractorImpl extends SimpleInteractor <Object>
{
    private final int mAllowShutdown;
    private final String mUserId;
    private final String mWatchId;
    private final WatchSettingRepository mWatchSettingRepository;

    public EditAllowShutdownInteractorImpl(String userid, String watchid, int paramInt, WatchSettingRepository paramWatchSettingRepository)
    {
        this.mUserId = userid;
        this.mWatchId = watchid;
        this.mAllowShutdown = paramInt;
        this.mWatchSettingRepository = paramWatchSettingRepository;
    }

    public void run()
    {
        try
        {
            this.mWatchSettingRepository.allowShutdownDevice(this.mUserId, this.mWatchId, this.mAllowShutdown);
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
