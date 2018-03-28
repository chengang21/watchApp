package com.duoker.watch.interactors.impl;

import com.duoker.watch.db.model.WifiHotSpotModel;
import com.duoker.watch.interactors.base.SimpleInteractor;
import com.duoker.watch.repository.WatchSettingRepository;

import java.io.IOException;
import java.util.List;

/**
 * Created by cheng on 2017/9/9.
 */
public class GetWifiSpot2ModelInteractorImpl extends SimpleInteractor<List<WifiHotSpotModel>>
{
    private final String mUserId;
    private final String mWatchId;
    private final WatchSettingRepository mWatchSettingRepository;

    public GetWifiSpot2ModelInteractorImpl(String userid, String watchId, WatchSettingRepository watchSettingRepository)
    {
        this.mUserId = userid;
        this.mWatchId = watchId;
        this.mWatchSettingRepository = watchSettingRepository;
    }

    public void run()
    {
        try
        {
            postObject2UiThread(this.mWatchSettingRepository.getWifiHotSpots2Model(this.mUserId, this.mWatchId));
        }
        catch (IOException e)
        {
            e.printStackTrace();
            postException2UiThread(e);
        }
    }
}
