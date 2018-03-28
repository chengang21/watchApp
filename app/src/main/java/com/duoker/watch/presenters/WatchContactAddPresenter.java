package com.duoker.watch.presenters;

import com.duoker.watch.db.model.CardNumberBean;
import com.duoker.watch.ui.BaseView;

/**
 * Created by chengang on 4/30/2017.
 */


public abstract interface WatchContactAddPresenter {
    public abstract void addOrEditContacts(String name, String phone, String shortnum, int type);

    public abstract void initData(CardNumberBean bean, int pos);

    public static abstract interface View extends BaseView {
        public abstract void finishActivity();

        public abstract void setCheckListenLayout(boolean paramBoolean);

        public abstract void setCheckPhoneCallLayout(boolean paramBoolean);

        public abstract void setCheckSosLayout(boolean paramBoolean);

        public abstract void setNameText(String paramString);

        public abstract void setPhoneText(String paramString);

        public abstract void setShortNumText(String paramString);

        public abstract void setToolbarTitleText(int paramInt);
    }
}

