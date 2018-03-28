package com.duoker.watch.interactors.impl;

import com.duoker.watch.db.MyDbException;
import com.duoker.watch.interactors.base.SimpleInteractor;
import com.duoker.watch.repository.CalcWalkCountRepository;
import com.duoker.watch.storage.StepCountTargetStorage;

/**
 * Created by chengang on 5/9/2017.
 */

public class GetStepCountTargetInteractorImpl extends SimpleInteractor<StepCountTargetStorage>
{
    private final CalcWalkCountRepository mCalcWalkCountRepository;
    private final String mLoginUserName;
    private final String mWatchId;

    public GetStepCountTargetInteractorImpl(String username, String watchid, CalcWalkCountRepository repository)
    {
        this.mLoginUserName = username;
        this.mWatchId = watchid;
        this.mCalcWalkCountRepository = repository;
    }

    public void run()
    {
        try
        {
            postObject2UiThread( mCalcWalkCountRepository.getStepCountTarget(this.mLoginUserName, this.mWatchId));
            return;
        }
        catch (MyDbException e)
        {
            e.printStackTrace();
            postException2UiThread(e);
        }
    }
}