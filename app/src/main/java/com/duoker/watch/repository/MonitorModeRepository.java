package com.duoker.watch.repository;

import com.duoker.watch.model.MonitorModeBean;

import java.io.IOException;

/**
 * Created by cheng on 2017/10/8.
 */
public interface MonitorModeRepository
{
    MonitorModeBean setCallToMonitorMode(String userid, String watchid) throws IOException;
}
