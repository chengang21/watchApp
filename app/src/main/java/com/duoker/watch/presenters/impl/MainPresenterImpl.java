package com.duoker.watch.presenters.impl;

/**
 * Created by chengang on 4/25/2017.
 */


import com.duoker.watch.executor.Executor;
import com.duoker.watch.executor.MainThread;
import com.duoker.watch.interactors.WelcomingInteractor;
import com.duoker.watch.interactors.impl.WelcomingInteractorImpl;
import com.duoker.watch.presenters.MainPresenter;
import com.duoker.watch.presenters.base.AbstractPresenter;
import com.duoker.watch.repository.MessageRepository;

/**
 * Created by dmilicic on 12/13/15.
 */
public class MainPresenterImpl extends AbstractPresenter implements MainPresenter,
        WelcomingInteractor.Callback {

    private MainPresenter.View mView;
    private MessageRepository mMessageRepository;

    public MainPresenterImpl( View view, MessageRepository messageRepository) {
        super( );
        mView = view;
        mMessageRepository = messageRepository;
    }

    public MainPresenterImpl(Executor executor, MainThread mainThread,
                             View view, MessageRepository messageRepository) {
        super(executor, mainThread);
        mView = view;
        mMessageRepository = messageRepository;
    }

    @Override
    public void onResume() {

        mView.showProgress();

        // initialize the interactor
        WelcomingInteractor interactor = new WelcomingInteractorImpl(
                this,
                mMessageRepository
        );

        // run the interactor
        interactor.execute();
    }

    @Override
    public void onPause() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void onError(String message) {
        mView.showError(message);
    }

    @Override
    public void onMessageRetrieved(String message) {
        mView.hideProgress();
        mView.displayWelcomeMessage(message);
    }

    @Override
    public void onRetrievalFailed(String error) {
        mView.hideProgress();
        onError(error);
    }
}

