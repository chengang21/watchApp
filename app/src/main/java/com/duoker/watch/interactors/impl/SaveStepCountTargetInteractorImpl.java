package com.duoker.watch.interactors.impl;

import com.duoker.watch.db.MyDbException;
import com.duoker.watch.interactors.base.SimpleInteractor;
import com.duoker.watch.repository.CalcWalkCountRepository;
import com.duoker.watch.storage.StepCountTargetStorage;

/**
 * Created by chengang on 5/9/2017.
 */
public class SaveStepCountTargetInteractorImpl extends SimpleInteractor<Object>
{
    private final CalcWalkCountRepository mCalcWalkCountRepository;
    private final StepCountTargetStorage mStepCountTargetStorage;

    public SaveStepCountTargetInteractorImpl(StepCountTargetStorage stepCountTargetStorage, CalcWalkCountRepository calcWalkCountRepository)
    {
        mStepCountTargetStorage = stepCountTargetStorage;
        mCalcWalkCountRepository = calcWalkCountRepository;
    }

    public void run()
    {
        try
        {
            mCalcWalkCountRepository.saveStepCountTarget(this.mStepCountTargetStorage);
            postObject2UiThread(null);
        }
        catch (MyDbException e)
        {
            e.printStackTrace();
            postException2UiThread(e);
        }
    }
}

