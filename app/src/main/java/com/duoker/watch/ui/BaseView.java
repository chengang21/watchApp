package com.duoker.watch.ui;

/**
 * Created by chengang on 4/25/2017.
 */

import android.support.annotation.StringRes;

public interface BaseView {

    /**
     * This is a general method used for showing some kind of progress during a background task. For example, this
     * method should show a progress bar and/or disable buttons before some background work starts.
     */
    void showProgress();

    /**
     * This is a general method used for hiding progress information after a background task finishes.
     */
    void hideProgress();

    /**
     * This method is used for showing error messages on the UI.
     *
     * @param message The error message to be displayed.
     */
    void showError(String message);

    void showToast(@StringRes int resourceID);

    void showToast(CharSequence charSequence);
}
