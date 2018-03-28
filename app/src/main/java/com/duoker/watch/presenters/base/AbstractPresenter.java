package com.duoker.watch.presenters.base;

/**
 * Created by chengang on 4/24/2017.
 */


import android.os.Bundle;

import com.duoker.watch.executor.Executor;
import com.duoker.watch.executor.MainThread;
import com.duoker.watch.executor.impl.ThreadExecutor;
import com.duoker.watch.threading.MainThreadImpl;

public abstract class AbstractPresenter implements BasePresenter {
    //protected Executor mExecutor;
    // protected MainThread mMainThread;

    public AbstractPresenter() {
        this(ThreadExecutor.getInstance(), MainThreadImpl.getInstance());
    }

    public AbstractPresenter(Executor executor, MainThread mainThread) {
        //this.mExecutor = executor;
        //this.mMainThread = mainThread;
    }

    @Override
    public void onCreate(Bundle paramBundle) {
    }

    @Override
    public void onDestroy() {
    }

    @Override
    public void onError(String paramString) {
    }

    @Override
    public void onPause() {
    }

    @Override
    public void onRestart() {
    }

    @Override
    public void onRestoreInstanceState(Bundle paramBundle) {
    }

    @Override
    public void onResume() {
    }

    @Override
    public void onSaveInstanceState(Bundle paramBundle) {
    }

    @Override
    public void onStart() {
    }

    @Override
    public void onStop() {
    }
}