package com.duoker.watch.interactors.impl;

import com.duoker.watch.interactors.base.SimpleInteractor;
import com.duoker.watch.model.ScheduleModel;
import com.duoker.watch.repository.ScheduleRepository;

import java.io.IOException;
/**
 * Created by cheng on 2017/10/7.
 */


public class DelScheduleInteractorImpl extends SimpleInteractor<Object>
{
    private final ScheduleModel mScheduleModel;
    private final ScheduleRepository mScheduleRepository;

    public DelScheduleInteractorImpl(ScheduleModel paramScheduleModel, ScheduleRepository paramScheduleRepository)
    {
        this.mScheduleModel = paramScheduleModel;
        this.mScheduleRepository = paramScheduleRepository;
    }

    public void run()
    {
        try
        {
            this.mScheduleRepository.delSchedule(this.mScheduleModel);
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
