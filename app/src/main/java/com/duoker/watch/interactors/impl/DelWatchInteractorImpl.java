package com.duoker.watch.interactors.impl;

import com.duoker.watch.interactors.base.SimpleInteractor;
import com.duoker.watch.repository.WatchRepository;

import java.io.IOException;

/**
 * Created by chengang on 5/9/2017.
 */


public class DelWatchInteractorImpl extends SimpleInteractor<Object>
{
    private final String mUserId;
    private final String mWatchId;
    private final WatchRepository mWatchRepository;

    public DelWatchInteractorImpl(String userid, String watchid, WatchRepository repository)
    {
        mUserId = userid;
        mWatchId = watchid;
        mWatchRepository = repository;
    }

    public void run()
    {
        try
        {
            mWatchRepository.delWatch( mUserId, mWatchId);

            postObject2UiThread(null);
        }
        catch (IOException e)
        {
            e.printStackTrace();
            postException2UiThread(e);
        }
    }
}