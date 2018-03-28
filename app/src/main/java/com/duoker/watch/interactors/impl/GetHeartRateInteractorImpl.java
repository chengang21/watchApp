package com.duoker.watch.interactors.impl;

import com.duoker.watch.interactors.base.SimpleInteractor;
import com.duoker.watch.model.HeartRateBean;
import com.duoker.watch.repository.HeartRateRepository;

import java.io.IOException;
import java.util.List;

/**
 * Created by cheng on 2017/10/9.
 */

public class GetHeartRateInteractorImpl extends SimpleInteractor<List<HeartRateBean>>
{
    private final long mBeginTime;
    private final long mEndTime;
    private final HeartRateRepository mHeartRateRepository;
    private final String mUserId;
    private final String mWatchId;

    public GetHeartRateInteractorImpl(String userid, String watchid, long begintime, long endtime, HeartRateRepository heartRateRepository)
    {
        this.mUserId = userid;
        this.mWatchId = watchid;
        this.mBeginTime = begintime;
        this.mEndTime = endtime;
        this.mHeartRateRepository = heartRateRepository;
    }

    public void run()
    {
        try
        {
            postObject2UiThread(this.mHeartRateRepository.getHeartRate(this.mUserId, this.mWatchId, this.mBeginTime, this.mEndTime));
        }
        catch (IOException e)
        {
            e.printStackTrace();
            postException2UiThread(e);
        }
    }
}
