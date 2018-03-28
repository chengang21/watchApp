package com.duoker.watch.interactors.impl;

import com.duoker.watch.interactors.base.SimpleInteractor;
import com.duoker.watch.model.PhoneBalanceBean;
import com.duoker.watch.repository.L25Repository;

import java.io.IOException;

/**
 * Created by cheng on 2017/10/8.
 */
public class QueryPhoneBalanceInteractorImpl extends SimpleInteractor<PhoneBalanceBean>
{
    private final L25Repository mL25Repository;
    private final String mUserId;
    private final String mWatchId;

    public QueryPhoneBalanceInteractorImpl(String userid, String watchid, L25Repository repository)
    {
        this.mUserId = userid;
        this.mWatchId = watchid;
        this.mL25Repository = repository;
    }

    public void run()
    {
        try
        {
            postObject2UiThread( mL25Repository.queryPhoneBalance(this.mUserId, this.mWatchId));
            return;
        }
        catch (IOException e)
        {
            e.printStackTrace();
            postException2UiThread(e);
        }
    }
}