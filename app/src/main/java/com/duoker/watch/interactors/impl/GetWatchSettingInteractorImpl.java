package com.duoker.watch.interactors.impl;

import com.duoker.watch.db.model.WatchSettingBean;
import com.duoker.watch.interactors.base.SimpleInteractor;
import com.duoker.watch.repository.WatchSettingRepository;

import java.io.IOException;

/**
 * Created by chengang on 4/30/2017.
 */

public class GetWatchSettingInteractorImpl extends SimpleInteractor<WatchSettingBean>
{
    private final String mUserId;
    private final String mWatchId;
    private final WatchSettingRepository mWatchSettingRepository;

    public GetWatchSettingInteractorImpl(String userid, String watchid, WatchSettingRepository watchSettingRepository)
    {
        this.mUserId = userid;
        this.mWatchId = watchid;
        this.mWatchSettingRepository = watchSettingRepository;
    }

    public void run()
    {
        try
        {
            postObject2UiThread( mWatchSettingRepository.getSetting(this.mUserId, this.mWatchId));
            return;
        }
        catch (IOException e)
        {
            e.printStackTrace();
            postException2UiThread(e);
        }
    }
}