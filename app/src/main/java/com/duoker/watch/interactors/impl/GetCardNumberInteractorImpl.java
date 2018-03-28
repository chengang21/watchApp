package com.duoker.watch.interactors.impl;

import com.duoker.watch.db.model.CardNumberBean;
import com.duoker.watch.interactors.base.SimpleInteractor;
import com.duoker.watch.repository.CardNumberRepository;

import java.io.IOException;
import java.util.List;

/**
 * Created by chengang on 4/30/2017.
 */

public class GetCardNumberInteractorImpl extends SimpleInteractor<List<CardNumberBean>> {
    private final CardNumberRepository mCardNumberRepository;
    private final String mUserId;
    private final String mWatchId;

    public GetCardNumberInteractorImpl(String userid, String watchid, CardNumberRepository cardNumberRepository) {
        this.mUserId = userid;
        this.mWatchId = watchid;
        this.mCardNumberRepository = cardNumberRepository;
    }

    @Override
    public void run() {
        try {
            postObject2UiThread(mCardNumberRepository.getCardNumber(mUserId, mWatchId));
        } catch (IOException e) {
            e.printStackTrace();
            postException2UiThread(e);
        }
    }
}