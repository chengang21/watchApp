package com.duoker.watch.presenters;

import com.duoker.watch.presenters.base.BasePresenter;
import com.duoker.watch.ui.BaseView;

/**
 * Created by chengang on 4/25/2017.
 */

public interface MainPresenter extends BasePresenter {

    interface View extends BaseView {
        void displayWelcomeMessage(String msg);
    }
}