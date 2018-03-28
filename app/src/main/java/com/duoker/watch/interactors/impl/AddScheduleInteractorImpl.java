package com.duoker.watch.interactors.impl;

import com.duoker.watch.interactors.base.SimpleInteractor;
import com.duoker.watch.model.ScheduleModel;
import com.duoker.watch.repository.ScheduleRepository;

import java.io.IOException;

/**
 * Created by cheng on 2017/10/6.
 */
public class AddScheduleInteractorImpl extends SimpleInteractor<Object>
{
    private final ScheduleModel mScheduleModel;
    private final ScheduleRepository mScheduleRepository;

    public AddScheduleInteractorImpl(ScheduleModel paramScheduleModel, ScheduleRepository paramScheduleRepository)
    {
        mScheduleModel = paramScheduleModel;
        mScheduleRepository = paramScheduleRepository;
    }

    public void run()
    {
        try
        {
            this.mScheduleRepository.addSchedule(this.mScheduleModel);
            postObject2UiThread(null);
        }
        catch (IOException e)
        {
            e.printStackTrace();
            postException2UiThread(e);
        }
    }
}