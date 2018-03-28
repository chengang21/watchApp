package com.duoker.watch.presenters;

import com.duoker.watch.storage.WatchsStorage;
import com.duoker.watch.ui.BaseView;

/**
 * Created by chengang on 5/9/2017.
 */

public abstract interface WatchInfoPhonePresenter
{
    public abstract void bindSimCard(String paramString);

    public abstract String getOldPhone();

    public abstract void initData(WatchsStorage paramWatchsStorage);

    public static abstract interface View extends BaseView
    {
        public abstract void finishActivity();

        public abstract void setPhoneError(int paramInt);

        public abstract void setPhoneText(String paramString);
    }
}
