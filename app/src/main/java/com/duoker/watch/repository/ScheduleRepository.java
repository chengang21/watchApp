package com.duoker.watch.repository;

import com.duoker.watch.model.ScheduleModel;

import java.io.IOException;
import java.util.List;

/**
 * Created by cheng on 2017/10/6.
 */
public interface ScheduleRepository
{
    public ScheduleModel addSchedule(ScheduleModel paramScheduleModel)
            throws IOException;

    public void delBatch(List<ScheduleModel> paramList)
            throws IOException;

    public void delSchedule(ScheduleModel paramScheduleModel)
            throws IOException;

    public void editSchedule(ScheduleModel paramScheduleModel)
            throws IOException;

    public List<ScheduleModel> getSchedules(String paramString1, String paramString2)
            throws IOException;

    public boolean isAutoDelSchedule(String paramString);

    public void operateSchedule(ScheduleModel paramScheduleModel)
            throws IOException;
}