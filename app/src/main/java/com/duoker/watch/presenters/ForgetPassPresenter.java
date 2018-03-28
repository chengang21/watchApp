package com.duoker.watch.presenters;

import com.duoker.watch.presenters.base.AbstractPresenter;
import com.duoker.watch.ui.BaseView;

/**
 * Created by cheng on 2017/9/3.
 */
public abstract class ForgetPassPresenter extends AbstractPresenter
{
    public abstract void forgetPass(String paramString1, String paramString2, String paramString3, String paramString4);

    public abstract void getCode(String paramString1, String paramString2);

    public static abstract interface View extends BaseView
    {
        public abstract void enableCodeView(boolean paramBoolean);

        public abstract void finishActivity();

        public abstract void gotoMain();

        public abstract void setCodeTime(int paramInt);
    }
}