package com.duoker.watch.interactors.base;

/**
 * Created by chengang on 4/30/2017.
 */

import android.support.annotation.CallSuper;

public abstract class SimpleInteractor<T> extends AbstractInteractor {
    protected SimpleCallback<T> mDefaultCallback;

    @CallSuper
    public void execute(SimpleCallback<T> callback) {
        this.mDefaultCallback = callback;
        execute();
    }

    @CallSuper
    public void executeOnCurrentThread(SimpleCallback<T> callback) {
        this.mDefaultCallback = callback;
        executeOnCurrentThread();
    }

    public void postException2UiThread(final Exception exception) {
        if (this.mDefaultCallback != null)
            runOnUiThread(new Runnable() {

                @Override
                public void run() {
                    mDefaultCallback.onError( new SimpleErrorBundle( exception) );
                }
            });
    }

    public void postObject2UiThread(final T paramT) {
        if (this.mDefaultCallback != null)
            runOnUiThread(new Runnable() {

                @Override
                public void run() {
                    mDefaultCallback.onSuccess(paramT);
                }
            });
    }
}
