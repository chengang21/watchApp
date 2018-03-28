package com.duoker.watch.presenters;

import com.duoker.watch.presenters.base.AbstractPresenter;
import com.duoker.watch.ui.BaseView;

/**
 * Created by cheng on 2017/8/31.
 */


public abstract class RegisterPresenter extends AbstractPresenter
{
    public abstract void getCode(String paramString1, String paramString2);

    public abstract void register(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6);

    public static abstract interface View extends BaseView
    {
        public void enableCodeView(boolean paramBoolean);

        public void finishActivity();

        public void goToLogin();

        public void setCodeTime(int paramInt);
    }
}
