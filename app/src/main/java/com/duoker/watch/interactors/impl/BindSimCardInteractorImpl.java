package com.duoker.watch.interactors.impl;

import com.duoker.watch.interactors.base.SimpleInteractor;
import com.duoker.watch.repository.WatchRepository;
import com.duoker.watch.ui.event.SetWatchInfoPhoneEvent;

import java.io.IOException;

/**
 * Created by chengang on 5/9/2017.
 */


public class BindSimCardInteractorImpl extends SimpleInteractor<SetWatchInfoPhoneEvent>
{
    private final String mSimCardNum;
    private final String mUserId;
    private final String mWatchId;
    private final WatchRepository mWatchRepository;

    public BindSimCardInteractorImpl(String userid, String watchid, String simcardnum, WatchRepository repository)
    {
        mUserId = userid;
        mWatchId = watchid;
        mSimCardNum = simcardnum;
        mWatchRepository = repository;
    }

    public void run()
    {
        try
        {
            mWatchRepository.bindSimCard( mUserId, mWatchId, mSimCardNum);
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