package com.duoker.watch.interactors.impl;

import com.duoker.watch.interactors.base.SimpleInteractor;
import com.duoker.watch.model.ScheduleModel;
import com.duoker.watch.repository.ScheduleRepository;

import java.io.IOException;
import java.util.List;

/**
 * Created by cheng on 2017/10/6.
 */
public class DelBatchScheduleInteractorImpl extends SimpleInteractor<Object> {
    
    private final List<ScheduleModel> mScheduleModel;
    private final ScheduleRepository mScheduleRepository;

    public DelBatchScheduleInteractorImpl(List<ScheduleModel> paramList, ScheduleRepository paramScheduleRepository)
    {
        mScheduleModel = paramList;
        mScheduleRepository = paramScheduleRepository;
    }

    public void run()
    {
        try
        {
            mScheduleRepository.delBatch(mScheduleModel);
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
