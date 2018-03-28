package com.duoker.watch.interactors.impl;

import com.duoker.watch.db.model.CardNumberBean;
import com.duoker.watch.interactors.base.SimpleInteractor;
import com.duoker.watch.repository.CardNumberRepository;

import java.io.IOException;
import java.util.List;

/**
 * Created by chengang on 4/30/2017.
 */
public class SaveAllCardNumberInteractorImpl extends SimpleInteractor<Object> {
    private final List<CardNumberBean> mCardNumberBeanList;
    private final CardNumberRepository mCardNumberRepository;
    private final String mUserId;
    private final String mWatchId;

    public SaveAllCardNumberInteractorImpl(String userid, String watchid, List<CardNumberBean> list, CardNumberRepository cardNumberRepository) {
        this.mUserId = userid;
        this.mWatchId = watchid;
        this.mCardNumberBeanList = list;
        this.mCardNumberRepository = cardNumberRepository;
    }

    public void run()
    {
        try
        {
            mCardNumberRepository.saveAll(this.mUserId, this.mWatchId, this.mCardNumberBeanList);
            postObject2UiThread(null);
        }
        catch (IOException e)
        {
            e.printStackTrace();
            postException2UiThread(e);
        }
    }
}
