package com.duoker.watch.interactors;

import com.duoker.watch.interactors.base.Interactor;

/**
 * Created by chengang on 4/25/2017.
 */


public interface WelcomingInteractor extends Interactor {

    interface Callback {
        void onMessageRetrieved(String message);

        void onRetrievalFailed(String error);
    }
}