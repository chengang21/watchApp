package com.duoker.watch.presenters.impl;

import com.duoker.watch.interactors.base.SimpleInteractor;
import com.duoker.watch.model.ScheduleModel;
import com.duoker.watch.repository.ScheduleRepository;

import java.io.IOException;
import java.util.List;

/**
 * Created by cheng on 2017/10/6.
 */
public class GetSchedulesInteractorImpl extends SimpleInteractor<List<ScheduleModel>> {
    private final ScheduleRepository mScheduleRepository;
    private final String mUserId;
    private final String mWatchId;

    public GetSchedulesInteractorImpl(String userid, String watchid, ScheduleRepository scheduleRepository)
    {
        mUserId = userid;
        mWatchId = watchid;
        mScheduleRepository = scheduleRepository;
    }

    public void run()
    {
        try
        {
            postObject2UiThread(mScheduleRepository.getSchedules(mUserId, mWatchId));
            return;
        }
        catch (IOException e)
        {
            e.printStackTrace();
            postException2UiThread(e);
        }
    }
}
