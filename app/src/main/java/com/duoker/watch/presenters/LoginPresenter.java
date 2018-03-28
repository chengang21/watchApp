package com.duoker.watch.presenters;

import com.duoker.watch.presenters.base.AbstractPresenter;
import com.duoker.watch.ui.BaseView;

/**
 * Created by cheng on 2017/8/31.
 */


public abstract class LoginPresenter extends AbstractPresenter
{
    public abstract void doLogin(String countrycode, String name, String pwd);

    public static interface View extends BaseView
    {
        void finishActivity();
        void gotoMain();
        void setPassword(String paramString);
        void setPhoneText(String paramString);
    }
}
