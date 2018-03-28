package com.duoker.watch.presenters;

/**
 * Created by chengang on 4/25/2017.
 */

import com.duoker.watch.db.model.CardNumberBean;
import com.duoker.watch.ui.BaseView;

import java.util.List;

public interface WatchContactsPresenter {
    public abstract void addItem(CardNumberBean bean, int pos);

    public abstract void delItem(CardNumberBean bean);

    public abstract void getCallNumbers();

    public abstract boolean isAdmin();

    public abstract boolean isNeedSave();

    public abstract void saveContacts(boolean paramBoolean);

    public abstract void setWhiteNameList(boolean paramBoolean);

    public static abstract interface View extends BaseView {
        public abstract void finishActivity();

        public abstract void inflateListView(List<CardNumberBean> paramList);

        public abstract void setAvatar(String paramString);

        public abstract void setMasterText(String paramString);

        public abstract void setPhoneText(String paramString);

        public abstract void setWhiteNameListChecked(boolean paramBoolean);
    }
}