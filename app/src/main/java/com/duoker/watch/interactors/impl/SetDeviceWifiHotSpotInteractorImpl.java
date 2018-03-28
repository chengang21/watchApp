package com.duoker.watch.interactors.impl;

import com.duoker.watch.interactors.base.SimpleInteractor;
import com.duoker.watch.model.SetWifiHotSpotModel;
import com.duoker.watch.repository.WatchSettingRepository;

import java.io.IOException;

/**
 * Created by cheng on 2017/9/9.
 */
public class SetDeviceWifiHotSpotInteractorImpl extends SimpleInteractor<Object>
{
    private final SetWifiHotSpotModel mSetWifiHotSpotModel;
    private final String mUserId;
    private final String mWatchId;
    private final WatchSettingRepository mWatchSettingRepository;

    public SetDeviceWifiHotSpotInteractorImpl(String paramString1, String paramString2, SetWifiHotSpotModel paramSetWifiHotSpotModel, WatchSettingRepository paramWatchSettingRepository)
    {
        this.mUserId = paramString1;
        this.mWatchId = paramString2;
        this.mSetWifiHotSpotModel = paramSetWifiHotSpotModel;
        this.mWatchSettingRepository = paramWatchSettingRepository;
    }

    public void run()
    {
        try
        {
            this.mWatchSettingRepository.setDeviceWifiHotSpot(this.mUserId, this.mWatchId, this.mSetWifiHotSpotModel);
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
